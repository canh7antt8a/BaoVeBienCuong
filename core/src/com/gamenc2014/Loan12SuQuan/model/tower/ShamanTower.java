package com.gamenc2014.Loan12SuQuan.model.tower;

import java.util.ArrayList;



import com.badlogic.gdx.math.Vector2;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.SoundDataManager;
import com.gamenc2014.Loan12SuQuan.model.bullet.Bullet;
import com.gamenc2014.Loan12SuQuan.model.bullet.EnumBulletList;
import com.gamenc2014.Loan12SuQuan.model.bullet.IBulletConstants;
import com.gamenc2014.Loan12SuQuan.model.enemy.CurrentEnemyWave;
import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.gameObject.EnumDirectionList;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.map.Road;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;
import com.gamenc2014.Loan12SuQuan.model.towerData.TowerData;

public class ShamanTower extends Tower {

	public ShamanTower(int id, Coordinate position, TowerData towerData) {
		super(id, position, towerData);
	}

	@Override
	public void attack(Enemy enemy, Scenario scenario) {
		playSound();
		generateBullet(enemy, scenario);
		if (aoeNumber > 0) {
			int numberAoeEnemy = 0;
			Enemy specialEnemy = scenario.getSpecialEnemy();

			if (specialEnemy != null) {
				if (specialEnemy != enemy) {
					if (isInAttackRange(specialEnemy)) {
						generateBullet(specialEnemy, scenario);
						numberAoeEnemy++;
					}
				}
			}

			CurrentEnemyWave currentEnemyWave = scenario.getCurrentEnemyWave();

			if (currentEnemyWave != null) {
				ArrayList<Enemy> enemyList = currentEnemyWave.getEnemyList();

				for (Enemy tempEnemy : enemyList) {
					if (isInAttackRange(tempEnemy)) {
						if (tempEnemy != enemy) {
							generateBullet(tempEnemy, scenario);
							numberAoeEnemy++;
							if (numberAoeEnemy >= aoeNumber) {
								return;
							}
						}
					}
				}
			}
		}
	}

	public void generateBullet(Enemy enemy, Scenario scenario) {
		if (enemy != null) {
			Coordinate enemyPosition = enemy.getPosition();

			if (enemy.getSpeed() != 0) {
				double a = EnumBulletList.SHAMAN_BULLET.getSpeed()
						/ enemy.getSpeed();
				a = a * a - 1;
				EnumDirectionList direction = enemy.getDirection();

				double tempX = enemyPosition.getX() - position.getX();
				double tempY = enemyPosition.getY() - position.getY();
				double squared, distance;

				if (direction == EnumDirectionList.UP_DIRECTION
						|| direction == EnumDirectionList.DOWN_DIRECTION) {
					squared = tempY * tempY;
					distance = (tempY + Math.sqrt(squared + a
							* (squared + tempX * tempX)))
							/ a;
				} else {// (direction == EnumDirectionList.LEFT_DIRECTION
						// || direction == EnumDirectionList.RIGHT_DIRECTION)
					squared = tempX * tempX;
					distance = (tempX + Math.sqrt(squared + a
							* (squared + tempY * tempY)))
							/ a;
				}

				Road road = scenario.getBattleMap().getEnemyRoad(
						enemy.getRoadID());
				int remain = (int) Math.round(distance);

				Coordinate nextMileStone;
				Coordinate currentPosition = enemyPosition;
				int currentMileStone = enemy.getCurrentMileStone();
				int delta;

				while (true) {
					nextMileStone = road.getMileStone(currentMileStone);
					if (nextMileStone != null) {
						delta = Math.round(currentPosition.dst(nextMileStone));
						if (delta > remain) {
							EnumDirectionList tempDirection = road
									.getDirection(currentMileStone - 1,
											currentMileStone);
							Coordinate destination;

							switch (tempDirection) {
							case UP_DIRECTION:
								destination = new Coordinate(
										currentPosition.getX(),
										currentPosition.getY() - remain);
								break;

							case DOWN_DIRECTION:
								destination = new Coordinate(
										currentPosition.getX(),
										currentPosition.getY() + remain);
								break;

							case LEFT_DIRECTION:
								destination = new Coordinate(
										currentPosition.getX() - remain,
										currentPosition.getY());
								break;

							case RIGHT_DIRECTION:
								destination = new Coordinate(
										currentPosition.getX() + remain,
										currentPosition.getY());
								break;

							default:
								destination = new Coordinate(
										currentPosition.getX() - remain,
										currentPosition.getY());
								break;
							}

							Vector2 velocity = new Vector2(
									((float) (destination.getX() - this.position
											.getX())), ((float) (destination
											.getY() - this.position.getY())));

							float speed = EnumBulletList.SHAMAN_BULLET
									.getSpeed();
							float range = destination.dst(position.getX(),
									position.getY());

							if (velocity.y != 0) {
								float rate = Math.abs(velocity.x / velocity.y);
								float tempResult = (float) (speed / Math
										.sqrt(rate * rate + 1));
								int modX = 1, modY = 1;

								if (velocity.x < 0) {
									modX = -1;
								}

								if (velocity.y < 0) {
									modY = -1;
								}

								velocity.set(modX * rate * tempResult, modY
										* tempResult);
							} else {
								if (velocity.x > 0) {
									velocity.x = speed;
								} else {
									velocity.x = -speed;
								}
							}

							Bullet bullet = new Bullet(this.ID,
									generateBulletID(), new Coordinate(
											this.position.getX(),
											this.position.getY()), destination,
									velocity, this, enemy, range / speed);
							scenario.addBulletList(bullet);

							setDirection(enemy.getX(), enemy.getY());
							this.lastAttackTime = stateTime;
							return;
						} else {
							currentPosition = nextMileStone;
							currentMileStone++;
							remain -= delta;
						}
					} else {
						return;
					}
				}
			} else {
				Vector2 velocity = new Vector2(
						((float) (enemyPosition.getX() - this.position.getX())),
						((float) (enemyPosition.getY() - this.position.getY())));

				float speed = EnumBulletList.SHAMAN_BULLET.getSpeed();
				float range = enemyPosition.dst(position.getX(),
						position.getY());

				if (velocity.y != 0) {
					float rate = Math.abs(velocity.x / velocity.y);
					float tempResult = (float) (speed / Math.sqrt(rate * rate
							+ 1));
					int modX = 1, modY = 1;

					if (velocity.x < 0) {
						modX = -1;
					}

					if (velocity.y < 0) {
						modY = -1;
					}

					velocity.set(modX * rate * tempResult, modY * tempResult);
				} else {
					if (velocity.x > 0) {
						velocity.x = speed;
					} else {
						velocity.x = -speed;
					}
				}

				Bullet bullet = new Bullet(this.ID, generateBulletID(),
						new Coordinate(this.position.getX(), this.position
								.getY()), enemyPosition, velocity,
						this, enemy, range / speed);
				scenario.addBulletList(bullet);

				setDirection(enemy.getX(), enemy.getY());
				this.lastAttackTime = stateTime;
				return;

			}
		}
	}

	public void playSound() {
		switch (dataID) {
		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:
			soundManager.play(SoundDataManager.SHAMAN_0, false);
		case ITowerConstants.SHAMAN_1_LV1:
		case ITowerConstants.SHAMAN_1_LV2:
		case ITowerConstants.SHAMAN_1_LV3:
			soundManager.play(SoundDataManager.SHAMAN_1, false);
		case ITowerConstants.SHAMAN_2_LV1:
		case ITowerConstants.SHAMAN_2_LV2:
		case ITowerConstants.SHAMAN_2_LV3:
			soundManager.play(SoundDataManager.SHAMAN_2, false);
		}
	}

	public int generateBulletID() {
		switch (dataID) {
		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:
			return IBulletConstants.BASIC_SHAMAN_BULLET;
		case ITowerConstants.SHAMAN_1_LV1:
		case ITowerConstants.SHAMAN_1_LV2:
		case ITowerConstants.SHAMAN_1_LV3:
			return IBulletConstants.SHAMAN_BULLET_1;
		case ITowerConstants.SHAMAN_2_LV1:
		case ITowerConstants.SHAMAN_2_LV2:
		case ITowerConstants.SHAMAN_2_LV3:
			return IBulletConstants.SHAMAN_BULLET_2;
		default:
			return IBulletConstants.BASIC_SHAMAN_BULLET;
		}
	}

	public float getSlowRate() {
		return slowRate;
	}

	public void setSlowRate(float slowRate) {
		this.slowRate = slowRate;
	}

	@Override
	public void addDamageFromAura(int auraType, float auraRate) {
		switch (auraType) {
		case ITowerConstants.MELEE_TYPE:
			break;
		case ITowerConstants.RANGE_TYPE:
			this.damage = (int) (this.damage + (auraRate * this.damage)
					* this.auraReceive);
			break;
		case ITowerConstants.BOTH_TYPE:
			this.damage = (int) (this.damage + (auraRate * this.damage)
					* this.auraReceive);
			break;
		}
	}
}
