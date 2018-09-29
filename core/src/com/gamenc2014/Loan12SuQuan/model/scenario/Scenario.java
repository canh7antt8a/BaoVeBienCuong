package com.gamenc2014.Loan12SuQuan.model.scenario;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.controller.data.GoldData;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.EnemyManager;
import com.gamenc2014.Loan12SuQuan.model.bullet.Bullet;
import com.gamenc2014.Loan12SuQuan.model.enemy.CurrentEnemyWave;
import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.enemy.EnemyWave;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.model.map.BattleMap;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.tower.ArcherTower;
import com.gamenc2014.Loan12SuQuan.model.tower.KnightTower;
import com.gamenc2014.Loan12SuQuan.model.tower.ShamanTower;
import com.gamenc2014.Loan12SuQuan.model.tower.ShogunTower;
import com.gamenc2014.Loan12SuQuan.model.tower.SwordTower;
import com.gamenc2014.Loan12SuQuan.model.tower.Tower;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;
import com.gamenc2014.Loan12SuQuan.model.towerData.ShogunData;
import com.gamenc2014.Loan12SuQuan.model.towerData.TowerData;


public class Scenario {

	public static int numberDefeatedEnemy;
	private static int numberTower = 0;

	private BattleMap battleMap;
	private ArrayList<Tower> towerList;
	private ArrayList<EnemyWave> enemyWaves;
	private CurrentEnemyWave currentEnemyWave;
	private int state;
	private float stateTime;
	private int numberWave;
	private int currentWaveID;
	private int gold;
	private int playerHP;
	private Enemy specialEnemy; // need to be defeated by tower immediately
								// player must tap to select it
	private ArrayList<Bullet> bulletList;
	private ArrayList<ShogunTower> shogunTowerList;
	private ArrayList<GoldData> goldList;
	private int diff;

	private EnemyManager enemyManager;

	public Scenario(BattleMap battleMap, ArrayList<Tower> towerList,
			ArrayList<EnemyWave> enemyWaves, int gold, int playerHP) {
		this.battleMap = battleMap;
		this.towerList = towerList;
		this.enemyWaves = enemyWaves;
		this.state = IScenarioConstants.STARTING;
		this.stateTime = 0;
		this.numberWave = enemyWaves.size();
		this.currentWaveID = -1;
		this.gold = gold;
		this.playerHP = playerHP;
		this.specialEnemy = null;
		this.bulletList = new ArrayList<Bullet>();
		this.currentEnemyWave = null;
		this.shogunTowerList = new ArrayList<ShogunTower>();
		this.goldList = new ArrayList<GoldData>();

		Scenario.numberTower = towerList.size();
		Scenario.numberDefeatedEnemy = 0;
	}

	public void init(int diff) {
		this.diff = diff;
		this.enemyManager = new EnemyManager(diff);
	}

	public CurrentEnemyWave getCurrentEnemyWave() {
		return this.currentEnemyWave;
	}

	public void setCurrentEnemyWaveList(EnemyWave enemyWave) {
		this.currentEnemyWave = new CurrentEnemyWave(enemyWave);
	}

	public BattleMap getBattleMap() {
		return battleMap;
	}

	public void setBattleMap(BattleMap battleMap) {
		this.battleMap = battleMap;
	}

	public ArrayList<Tower> getTowerList() {
		return towerList;
	}

	public void setTowerList(ArrayList<Tower> towerList) {
		this.towerList = towerList;
	}

	public ArrayList<EnemyWave> getEnemyWaves() {
		return enemyWaves;
	}

	public void setEnemyWaves(ArrayList<EnemyWave> enemyWaves) {
		this.enemyWaves = enemyWaves;
	}

	public int getNumberWave() {
		return numberWave;
	}

	public void setNumberWave(int numberWave) {
		this.numberWave = numberWave;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		if (gold > -1) {
			this.gold = gold;
		} else {
			this.gold = 0;
		}
	}

	public int getPlayerHP() {
		return playerHP;
	}

	public void setPlayerHP(int playerHP) {
		if (playerHP > -1) {
			this.playerHP = playerHP;
		} else {
			this.playerHP = 0;
		}
	}

	public Enemy getSpecialEnemy() {
		return specialEnemy;
	}

	public void setSpecialEnemy(Enemy specialEnemy) {
		this.specialEnemy = specialEnemy;
	}

	public void setSpecialEnemy(int x, int y) {
		if (currentEnemyWave != null) {
			x = (x << 7);
			y = (y << 7);
			Float distance = Float.MAX_VALUE, tempDistance;

			ArrayList<Enemy> enemyList = currentEnemyWave.getEnemyList();
			for (Enemy enemy : enemyList) {
				tempDistance = enemy.getPosition().dst(x, y);

				if (tempDistance <= ICommonConstants.DEFAULT_TILE_SIZE) {
					if (Float.compare(tempDistance, distance) < 0) {
						specialEnemy = enemy;
						distance = tempDistance;
					}
				}
			}

			if (distance == Float.MAX_VALUE) {
				this.specialEnemy = null;
			}
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public int getCurrentWaveID() {
		return currentWaveID + 1;
	}

	public void setCurrentWaveID(int currentWaveID) {
		this.currentWaveID = currentWaveID;
	}

	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}

	public void setBulletList(ArrayList<Bullet> bulletList) {
		this.bulletList = bulletList;
	}

	public void addBulletList(Bullet bullet) {
		if (this.bulletList != null) {
			this.bulletList.add(bullet);
		}
	}

	public ArrayList<ShogunTower> getShogunTowerList() {
		return shogunTowerList;
	}

	public void setShogunTower(ArrayList<ShogunTower> shogunTowerList) {
		this.shogunTowerList = shogunTowerList;
	}

	public Enemy findEnemyToAttack(Tower tower) {
		if (currentEnemyWave != null) {
			if (specialEnemy != null) {
				if (tower.isInAttackRange(specialEnemy)) {
					if (tower.isAbleAttackEnemy(specialEnemy)) {
						if (specialEnemy.getHealth() > 0) {
							return specialEnemy;
						}
					}
				}
			}

			ArrayList<Enemy> enemyList;
			enemyList = currentEnemyWave.getEnemyList();
			int distance = Integer.MAX_VALUE;
			int temp;
			Enemy tempEnemy = null;

			for (Enemy enemy : enemyList) {
				if (tower.isInAttackRange(enemy)) {
					if (tower.isAbleAttackEnemy(enemy)) {
						if (enemy.getHealth() > 0) {
							temp = enemy.getDistanceToDestination();
							if (temp < distance) {
								tempEnemy = enemy;
								distance = temp;
							}
						}
					}
				}
			}

			return tempEnemy;
		}
		return null;
	}

	public boolean isEnoughGold(int goldCost) {
		if (goldCost <= gold) {
			return true;
		} else {
			return false;
		}
	}

	public Tower isOccupied(int x, int y) {
		for (Tower tower : towerList) {
			if ((tower.getX() >> 7) == x && (tower.getY() >> 7) == y) {
				return tower;
			}
		}
		return null;
	}

	public Tower isOccupied(Coordinate position) {
		for (Tower tower : towerList) {
			if ((tower.getX() >> 7) == position.getX()
					&& (tower.getY() >> 7) == position.getY()) {
				return tower;
			}
		}
		return null;
	}

	public boolean isHasHero(int heroID) {
		for (ShogunTower shogunTower : shogunTowerList) {
			if (shogunTower.getDataID() == heroID) {
				return true;
			}
		}
		return false;
	}

	public Tower getTower(int id) {
		if (id > -1 && id < towerList.size()) {
			for (Tower tower : towerList) {
				if (tower.getID() == id) {
					return tower;
				}
			}
			return null;
		} else {
			return null;
		}
	}

	public int getDiff() {
		return diff;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public ArrayList<GoldData> getGoldList() {
		return goldList;
	}

	public void setGoldList(ArrayList<GoldData> goldList) {
		this.goldList = goldList;
	}

	public void addGold(GoldData gold) {
		if (gold != null) {
			this.goldList.add(gold);
		}
	}

	public GoldData processGoldSelect(int x, int y) {
		x = (x << 7);
		y = (y << 7);

		Float tempDistance;
		GoldData tempGold;

		for (int i = goldList.size() - 1; i > -1; i--) {
			tempGold = goldList.get(i);
			tempDistance = tempGold.getPosition().dst(x, y);

			if (tempDistance <= ICommonConstants.DEFAULT_TILE_SIZE) {
				this.gold += tempGold.getGoldNumber();
				goldList.remove(i);
				return tempGold;
			}
		}
		return null;
	}

	public boolean isBuildable(Coordinate buildPosition) {
		if (battleMap.isStrategyPoint(buildPosition)) {
			if (isOccupied(buildPosition) == null) {
				return true;
			}
		}
		return false;
	}

	public boolean isBuildable(int x, int y) {
		if (battleMap.isStrategyPoint(x, y)) {
			if (isOccupied(x, y) == null) {
				return true;
			}
		}
		return false;
	}

	public void addTower(TowerData towerData, int dataID,
			Coordinate buildPosition) {
		buildPosition = battleMap.convertToCell(buildPosition);
		if (isBuildable(buildPosition)) {
			Tower tempTower = null;

			switch (dataID) {
			case ITowerConstants.BASIC_ARCHER_LV1:
				tempTower = new ArcherTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura();
				break;
			case ITowerConstants.BASIC_SWORD_LV1:
				tempTower = new SwordTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura();
				break;
			case ITowerConstants.BASIC_KNIGHT_LV1:
				tempTower = new KnightTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura();
				break;
			case ITowerConstants.BASIC_SHAMAN_LV1:
				tempTower = new ShamanTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura();
				break;
			}

			if (tempTower != null) {
				towerList.add(tempTower);
				numberTower++;
				this.gold -= towerData.getGoldCost();
			}
		}
	}

	public void addShogunTower(ShogunData towerData, int dataID,
			Coordinate buildPosition) {
		buildPosition = battleMap.convertToCell(buildPosition);
		if (isBuildable(buildPosition)) {
			ShogunTower tempTower = null;

			switch (dataID) {
			case ITowerConstants.SHOGUN_0:
				tempTower = new ShogunTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura(tempTower);
				break;
			case ITowerConstants.SHOGUN_1:
				tempTower = new ShogunTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura(tempTower);
				break;
			case ITowerConstants.SHOGUN_2:
				tempTower = new ShogunTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura(tempTower);
				break;
			case ITowerConstants.SHOGUN_3:
				tempTower = new ShogunTower(numberTower,
						battleMap.convertToPixel(buildPosition), towerData);
				addDamageFromAura(tempTower);
				break;
			}

			if (tempTower != null) {
				shogunTowerList.add(tempTower);
				towerList.add(tempTower);
				numberTower++;
				this.gold -= towerData.getGoldCost();
			}
		}
	}

	public void removeTower(Tower tower) {
		this.gold += tower.getGoldCost();
		switch (tower.getDataID()) {
		case ITowerConstants.SHOGUN_0:
		case ITowerConstants.SHOGUN_1:
		case ITowerConstants.SHOGUN_2:
		case ITowerConstants.SHOGUN_3:
			decreaseDamageFromAura((ShogunTower) tower);
			shogunTowerList.remove(tower);
			towerList.remove(tower);
			return;
		}
		towerList.remove(tower);
	}

	public int getDistance(Enemy enemy) {
		return battleMap.getDistance(enemy);
	}

	public void addDamageFromAura(ShogunTower shogunTower) {
		if (shogunTowerList.size() > 0) {
			int temp = ICommonConstants.DEFAULT_TILE_SIZE
					* shogunTower.getAuraRange();
			long value = (temp * temp) << 1;

			int auraType = shogunTower.getAuraType();

			int x = shogunTower.getX();
			int y = shogunTower.getY();

			int tempX, tempY;
			long tempDeltaX, tempDeltaY, deltaX, deltaY;
			long distance;

			for (Tower tower : towerList) {
				tempX = tower.getX();
				tempY = tower.getY();
				tempDeltaX = Math.abs(x - tempX);
				tempDeltaY = Math.abs(y - tempY);

				deltaX = tempDeltaX * tempDeltaX;
				deltaY = tempDeltaY * tempDeltaY;
				distance = deltaX + deltaY;
				if (distance <= value) {
					tower.addDamageFromAura(auraType, 1f);
				}
			}
		}
	}

	public void addDamageFromAura() {
		if (shogunTowerList.size() > 0) {
			for (ShogunTower shogunTower : shogunTowerList) {
				int temp = shogunTower.getAuraRange();
				long value = (temp * temp) << 1;

				int auraType = shogunTower.getAuraType();

				int x = shogunTower.getX();
				int y = shogunTower.getY();

				int tempX, tempY;
				long tempDeltaX, tempDeltaY, deltaX, deltaY;
				long distance;

				for (Tower tower : towerList) {
					tempX = tower.getX();
					tempY = tower.getY();
					tempDeltaX = Math.abs(x - tempX);
					tempDeltaY = Math.abs(y - tempY);

					deltaX = tempDeltaX * tempDeltaX;
					deltaY = tempDeltaY * tempDeltaY;
					distance = deltaX + deltaY;
					if (distance <= value) {
						tower.addDamageFromAura(auraType, 1f);
					}
				}
			}
		}
	}

	public void decreaseDamageFromAura(ShogunTower shogunTower) {
		int temp = shogunTower.getAuraRange();
		long value = (temp * temp) << 1;

		int auraType = shogunTower.getAuraType();

		int x = shogunTower.getX();
		int y = shogunTower.getY();

		int tempX, tempY;
		long tempDeltaX, tempDeltaY, deltaX, deltaY;
		long distance;

		for (Tower tower : towerList) {
			tempX = tower.getX();
			tempY = tower.getY();
			tempDeltaX = x - tempX;
			tempDeltaY = y - tempY;

			deltaX = tempDeltaX * tempDeltaX;
			deltaY = tempDeltaY * tempDeltaY;
			distance = deltaX * deltaX + deltaY * deltaY;
			if (distance <= value) {
				tower.addDamageFromAura(auraType, -1f);
			}
		}
		shogunTowerList.remove(shogunTower);
	}

	public void callEnemyWave() {
		if (currentEnemyWave == null) {
			if (this.state != IScenarioConstants.ENDING) {
				if (currentWaveID == -1) {
					this.stateTime += enemyWaves.get(0).getWaveAppearTime() + 0.1f;
				} else {
					this.stateTime += enemyWaves.get(currentWaveID)
							.getWaveAppearTime() + 0.1f;
				}
			}
		}
	}

	public void update(float delta) {
		this.stateTime += delta;
		if (state == IScenarioConstants.PLAYING) {

			int i;
			GoldData tempGold;
			for (i = goldList.size() - 1; i > -1; i--) {
				tempGold = goldList.get(i);
				if (tempGold.isEnd() == true) {
					goldList.remove(i);
				} else {
					tempGold.update(delta, this);
				}
			}

			Bullet bullet;
			for (i = bulletList.size() - 1; i > -1; i--) {
				bullet = bulletList.get(i);
				if (bullet.isEnd() == true) {
					bulletList.remove(i);
				} else {
					bullet.update(delta, this);
				}
			}

			Enemy tempEnemy;
			for (Tower tower : towerList) {
				tower.update(delta, this);
				if (tower.isEndOfReloadTime() == true) {
					tempEnemy = findEnemyToAttack(tower);

					if (tempEnemy != null) {
						tower.attack(tempEnemy, this);
						tower.setState(ITowerConstants.ATTACKING);
					} else {
						tower.setState(ITowerConstants.WAITING);
					}
				} else if (tower.isEndAttacking() == true) {
					tower.setState(ITowerConstants.ATTACKED);
				}
			}

			if (currentEnemyWave.getCurrentEnemyID() >= enemyWaves.get(
					currentWaveID).getSize()
					&& currentEnemyWave.getSize() <= 0) {
				this.currentEnemyWave.getEnemyList().clear();
				this.bulletList.clear();

				if (currentWaveID >= (enemyWaves.size() - 1)) {
					this.state = IScenarioConstants.ENDING;
					for (Tower tower : towerList) {
						tower.setState(ITowerConstants.WAITING);
					}
				} else {
					this.state = IScenarioConstants.WAITING;
					enemyWaves.get(currentWaveID).addWaveAppearTime(stateTime);
					for (Tower tower : towerList) {
						tower.setState(ITowerConstants.WAITING);
					}
				}
			} else {
				currentEnemyWave.update(delta, this);
			}

			if (this.playerHP <= 0) {
				this.state = IScenarioConstants.ENDING;
			}

		} else if (state == IScenarioConstants.STARTING) {
			if (enemyWaves.get(0).getWaveAppearTime() < stateTime) {
				this.state = IScenarioConstants.PLAYING;
				this.currentWaveID++;
				this.setCurrentEnemyWaveList(enemyWaves.get(0));
			}

			for (Tower tower : towerList) {
				tower.setState(ITowerConstants.WAITING);
				tower.update(delta, this);
			}

		} else if (state == IScenarioConstants.WAITING) {
			for (Tower tower : towerList) {
				tower.setState(ITowerConstants.WAITING);
				tower.update(delta, this);
			}

			int i;
			GoldData tempGold;
			for (i = goldList.size() - 1; i > -1; i--) {
				tempGold = goldList.get(i);
				if (tempGold.isEnd() == true) {
					goldList.remove(i);
				} else {
					tempGold.update(delta, this);
				}
			}

			if (enemyWaves.get(currentWaveID).getWaveAppearTime() < stateTime) {
				this.state = IScenarioConstants.PLAYING;
				this.currentWaveID++;
				this.setCurrentEnemyWaveList(enemyWaves.get(currentWaveID));
			}

		} else if (state == IScenarioConstants.ENDING) {
		}
	}
}
