package com.gamenc2014.Loan12SuQuan.model.enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.gamenc2014.Loan12SuQuan.controller.data.GoldData;
import com.gamenc2014.Loan12SuQuan.model.gameObject.EnumDirectionList;
import com.gamenc2014.Loan12SuQuan.model.map.BattleMap;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.map.Road;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;


public class CurrentEnemyWave {
	private int waveID;
	private ArrayList<Enemy> enemyList;
	private int currentEnemyID;
	private float stateTime;

	public CurrentEnemyWave(EnemyWave enemyWave) {
		this.waveID = enemyWave.getID();
		this.stateTime = 0;
		this.currentEnemyID = 0;
		this.enemyList = new ArrayList<Enemy>();
	}

	public int getID() {
		return waveID;
	}

	public void setID(int waveID) {
		this.waveID = waveID;
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public int getSize() {
		return enemyList.size();
	}

	public Enemy getEnemy(int enemyID) {
		for (Enemy enemy : enemyList) {
			if (enemy.getID() == enemyID) {
				return enemy;
			}
		}
		return null;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public int getCurrentEnemyID() {
		return currentEnemyID;
	}

	public void setCurrentEnemyID(int currentEnemyID) {
		this.currentEnemyID = currentEnemyID;
	}

	public void update(float delta, Scenario scenario) {
		this.stateTime += delta;

		Enemy enemy;
		BattleMap battleMap = scenario.getBattleMap();

		EnemyWave enemyWave = scenario.getEnemyWaves().get(waveID);
		ArrayList<EnemyData> enemyDataList = enemyWave.getEnemyDataList();
		EnemyData temp;
		Road road;
		int dataID, roadID, current;

		for (int i = currentEnemyID; i < enemyDataList.size(); i++) {
			temp = enemyDataList.get(i);
			if (temp.getAppearTime() < stateTime) {
				dataID = temp.getDataID();

				if (dataID < 0) {
					Random rand = new Random();
					int tempID = rand.nextInt(6);
					dataID = tempID * 10 - (dataID + 1);
				}

				roadID = temp.getRoadID();
				road = battleMap.getEnemyRoad(roadID);
				EnumDirectionList direction = road.getDirection(0, 1);

				Coordinate coor = road.getFirstMileStone();

				Enemy tempNewEnemy = new Enemy(i, new Coordinate(coor.getX(),
						coor.getY()), scenario.getEnemyManager().getEnemyData(
						dataID), direction, roadID, 1, scenario);
				enemyList.add(tempNewEnemy);
				currentEnemyID++;
			}
		}

		for (int i = enemyList.size() - 1; i > -1; i--) {
			enemy = enemyList.get(i);

			roadID = enemy.getRoadID();
			road = battleMap.getEnemyRoad(roadID);

			if (enemy.getHealth() < 1) {
				scenario.setGold(scenario.getGold() + enemy.getGold());
				if (enemy.isDropTreasure() == false) {
					dataID = enemy.getDataID();
					int tempX = enemy.getX();
					int tempY = enemy.getY();
					EnumDirectionList tempDiretion = enemy.getDirection();
					int startMileStone = enemy.getCurrentMileStone();

					switch (tempDiretion) {
					case UP_DIRECTION: {
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY - 20), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY - 40), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						break;
					}
					case DOWN_DIRECTION: {
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY + 20), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY + 40), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						break;
					}
					case LEFT_DIRECTION: {
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX - 20, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX - 40, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						break;
					}
					case RIGHT_DIRECTION: {
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX + 20, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						enemyList.add(new Enemy(enemyList.size(),
								new Coordinate(tempX + 40, tempY), scenario
										.getEnemyManager().getEnemyData(
												dataID - 10), tempDiretion,
								roadID, startMileStone, scenario));
						break;
					}
					}
				} else {
					int rate = (new Random()).nextInt(100);
					if (rate < enemy.getDropRate()) {
						scenario.addGold(new GoldData(enemy.getID(), enemy
								.getDataID(), enemy.getPosition(), 50));
					}
				}

				enemyList.remove(i);
				Scenario.numberDefeatedEnemy++;
			} else {
				enemy.update(delta, scenario);
				if (battleMap.isReachMileStone(roadID, enemy) == true) {
					current = enemy.getCurrentMileStone();
					if (current < (road.getRoadLength() - 1)) {
						enemy.setDirection(road.getDirection(current,
								current + 1));
						Coordinate tempCoor = road.getMileStone(current);
						enemy.setPosition(new Coordinate(tempCoor.getX(),
								tempCoor.getY()));
						enemy.setCurrentMileStone(current + 1);
					} else {
						scenario.setPlayerHP(scenario.getPlayerHP()
								- enemy.getDamage());
						enemyList.remove(i);
					}
				}
			}
		}
		Collections.sort(enemyList);
	}
}
