package com.gamenc2014.Loan12SuQuan.model.tower;

import java.util.ArrayList;
import java.util.Random;

import com.gamenc2014.Loan12SuQuan.controller.dataManager.AbstractDataManager;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.SoundDataManager;
import com.gamenc2014.Loan12SuQuan.model.effect.Critical_Effect;
import com.gamenc2014.Loan12SuQuan.model.effect.Miss_Effect;
import com.gamenc2014.Loan12SuQuan.model.effect.Slow_Effect;
import com.gamenc2014.Loan12SuQuan.model.enemy.CurrentEnemyWave;
import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.gameObject.GameObject;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;
import com.gamenc2014.Loan12SuQuan.model.towerData.EnumTowerList;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;
import com.gamenc2014.Loan12SuQuan.model.towerData.TowerData;


public abstract class Tower extends GameObject {
	protected int goldCost;
	protected int damage;

	protected int attackRange;
	protected float attackTime;
	protected boolean isAirAttack;

	protected int criticalChance;
	protected float criticalDamageRate;

	protected int slowChance;
	protected float slowRate;

	protected boolean ignoreDefense;
	protected boolean ignoreEvasion;

	protected int aoeNumber;

	protected int auraReceive;
	protected int auraRange;

	protected int state;
	protected float lastAttackTime;
	protected int direction;
	protected static Random random = new Random();

	protected static SoundDataManager soundManager;

	public Tower(int id, Coordinate position, TowerData tower) {
		super(id, tower.getDataID(), position);

		this.goldCost = tower.getGoldCost();
		this.damage = tower.getDamage();

		this.attackRange = tower.getAttackRange();
		this.attackTime = tower.getAttackTime();
		this.isAirAttack = tower.isAirAttack();

		this.criticalChance = tower.getCriticalChance();
		this.criticalDamageRate = tower.getCriticalDamageRate();

		this.slowChance = tower.getSlowChance();
		this.slowRate = tower.getSlowRate();

		this.ignoreDefense = tower.isIgnoreDefense();
		this.ignoreEvasion = tower.isIgnoreEvasion();

		this.aoeNumber = tower.getAoeNumber();

		this.auraReceive = 1;
		this.auraRange = 0;

		this.state = ITowerConstants.WAITING;
		this.lastAttackTime = 0;
	}

	public void upgradeTower(TowerData tower) {
		super.dataID = tower.getDataID();

		this.goldCost = this.getGoldCost() + tower.getGoldCost();
		this.damage = tower.getDamage();

		this.attackRange = tower.getAttackRange();
		this.attackTime = tower.getAttackTime();
		this.isAirAttack = tower.isAirAttack();

		this.criticalChance = tower.getCriticalChance();
		this.criticalDamageRate = tower.getCriticalDamageRate();

		this.slowChance = tower.getSlowChance();
		this.slowRate = tower.getSlowRate();

		this.ignoreDefense = tower.isIgnoreDefense();
		this.ignoreEvasion = tower.isIgnoreEvasion();

		this.aoeNumber = tower.getAoeNumber();

		this.auraReceive = 1;
		this.auraRange = 0;

		this.state = ITowerConstants.WAITING;
		this.lastAttackTime = 0;
	}

	public static void setSoundManager(SoundDataManager soundDataManager) {
		soundManager = soundDataManager;
	}

	public int getDrawID() {
		return (this.dataID - this.dataID % 3);
	}

	public String getName() {
		return EnumTowerList.getTowerData(dataID).getName();
	}

	public boolean isAbleLevelUp() {
		if (dataID % 3 < 2) {
			return true;
		} else {
			return false;
		}
	}

	public int getLevel() {
		return (dataID % 3 + 1);
	}

	public int getGoldCost() {
		return goldCost;
	}

	public void setGoldCost(int goldCost) {
		this.goldCost = goldCost;
	}

	public int getDamage() {
		return damage;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public float getAttackTime() {
		return attackTime;
	}

	public boolean isAirAttack() {
		return isAirAttack;
	}

	public int getCriticalChance() {
		return criticalChance;
	}

	public float getCriticalDamageRate() {
		return criticalDamageRate;
	}

	public int getSlowChance() {
		return slowChance;
	}

	public float getSlowRate() {
		return slowRate;
	}

	public boolean isIgnoreDefense() {
		return ignoreDefense;
	}

	public boolean isIgnoreEvasion() {
		return ignoreEvasion;
	}

	public int getAoeNumber() {
		return aoeNumber;
	}

	public int getAuraReceive() {
		return auraReceive;
	}

	public int getAuraRange() {
		return auraRange;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getLastAttackTime() {
		return lastAttackTime;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int x, int y) {
		this.direction = position.getDirection(x, y);
	}

	public boolean isEndOfReloadTime() {
		if ((stateTime - lastAttackTime) < attackTime) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isEndAttacking() {
		if ((stateTime - lastAttackTime) < (5 * AbstractDataManager
				.getFrameDuration())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAbleAttackEnemy(Enemy enemy) {
		boolean isFlyUnit = enemy.isFlyUnit();
		if (isFlyUnit == true) {
			if (this.isAirAttack == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public boolean isInAttackRange(Enemy enemy) {
		if (this.position.dst(enemy.getPosition()) > (attackRange * 1.2f)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isInAoeRange(Coordinate position, Enemy enemy) {
		if (position.dst(enemy.getPosition()) > ICommonConstants.DEFAULT_TILE_SIZE) {
			return false;
		} else {
			return true;
		}
	}

	public void attack(Enemy enemy, Scenario scenario) {
		this.lastAttackTime = stateTime;
		setDirection(enemy.getX(), enemy.getY());

		int tempRandom = random.nextInt(100);
		if (this.ignoreEvasion == false) {
			if (tempRandom < enemy.getEvasionRate()) {
				enemy.addEffect(new Miss_Effect());
				return;
			}
		}

		if (aoeNumber > 0) {
			int numberAoeEnemy = 0;
			Coordinate position = enemy.getPosition();
			Enemy specialEnemy = scenario.getSpecialEnemy();

			if (specialEnemy != null) {
				if (specialEnemy != enemy) {
					if (isInAoeRange(position, specialEnemy)) {
						specialEnemy
								.decreaseHealth((int) (this.damage * (1 - specialEnemy
										.getDefense())));
						numberAoeEnemy++;
					}
				}
			}

			CurrentEnemyWave currentEnemyWave = scenario.getCurrentEnemyWave();
			if (currentEnemyWave != null) {
				ArrayList<Enemy> enemyList = currentEnemyWave.getEnemyList();

				for (Enemy tempEnemy : enemyList) {
					if (isInAoeRange(position, tempEnemy)) {
						tempEnemy
								.decreaseHealth((int) (this.damage * (1 - tempEnemy
										.getDefense())));
						numberAoeEnemy++;
						if (numberAoeEnemy > aoeNumber) {
							break;
						}
					}
				}
			}
		}

		tempRandom = random.nextInt(100);
		if (this.ignoreDefense == true) {
			if (tempRandom < criticalChance) {
				enemy.decreaseHealth((int) (this.damage * this.criticalDamageRate));
				enemy.addEffect(new Critical_Effect(this));
			} else {
				enemy.decreaseHealth(this.damage);
			}
		} else {
			if (tempRandom < criticalChance) {
				enemy.decreaseHealth((int) ((this.damage * this.criticalDamageRate) * (1 - enemy
						.getDefense())));
				enemy.addEffect(new Critical_Effect(this));
			} else {
				enemy.decreaseHealth((int) (this.damage * (1 - enemy
						.getDefense())));
			}
		}

		tempRandom = random.nextInt(100);
		if (tempRandom < slowChance) {
			enemy.addEffect(new Slow_Effect(3, 0, this.getSlowRate()));
		}
	}

	public void damage(Enemy enemy, Scenario scenario) {
		setDirection(enemy.getX(), enemy.getY());

		int tempRandom = random.nextInt(100);
		if (this.ignoreEvasion == false) {
			if (tempRandom < enemy.getEvasionRate()) {
				enemy.addEffect(new Miss_Effect());
				return;
			}
		}

		tempRandom = random.nextInt(100);
		if (this.ignoreDefense == true) {
			if (tempRandom < criticalChance) {
				enemy.decreaseHealth((int) (this.damage * this.criticalDamageRate));
				enemy.addEffect(new Critical_Effect(this));
			} else {
				enemy.decreaseHealth(this.damage);
			}
		} else {
			if (tempRandom < criticalChance) {
				enemy.decreaseHealth((int) ((this.damage * this.criticalDamageRate) * (1 - enemy
						.getDefense())));
				enemy.addEffect(new Critical_Effect(this));
			} else {
				enemy.decreaseHealth((int) (this.damage * (1 - enemy
						.getDefense())));
			}
		}

		tempRandom = random.nextInt(100);
		if (tempRandom < slowChance) {
			enemy.addEffect(new Slow_Effect(3, 0, this.getSlowRate()));
		}
	}

	@Override
	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
	}

	public abstract void addDamageFromAura(int auraType, float auraRate);
}
