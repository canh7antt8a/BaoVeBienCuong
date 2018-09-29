package com.gamenc2014.Loan12SuQuan.controller.logicManager;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.data.GoldData;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.ButtonDataManager;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.Skill_Profile;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.UnlockArmy_Profile;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.SoundDataManager;
import com.gamenc2014.Loan12SuQuan.model.enemy.CurrentEnemyWave;
import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.magic.MagicObject;
import com.gamenc2014.Loan12SuQuan.model.map.BattleMap;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;
import com.gamenc2014.Loan12SuQuan.model.scenario.ScenarioInitializer;
import com.gamenc2014.Loan12SuQuan.model.tower.Tower;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;
import com.gamenc2014.Loan12SuQuan.model.towerData.ShogunData;
import com.gamenc2014.Loan12SuQuan.model.towerData.TowerData;


public class GameLogic {
	private Scenario scenario;
	private Tower selectedTower;
	private MagicManager magicManager;
	private TowerManager towerManager;

	private Skill_Profile skill_Profile;
	private UnlockArmy_Profile unlockProfile;

	private ArrayList<Integer> heroList;
	private TowerData[] upgradeTowerData;
	private TowerData levelUpTower;

	private int mapID;
	private int userID;
	private int difficulty;

	private SoundDataManager soundManager;

	public GameLogic(int mapID, int userID, int difficulty,
			SoundDataManager soundManager, BaoVeBienCuong baoVeBienCuong) {
		this.skill_Profile = new Skill_Profile(baoVeBienCuong);
		this.unlockProfile = new UnlockArmy_Profile(baoVeBienCuong);

		this.magicManager = new MagicManager(skill_Profile.getSupportLevel(),
				soundManager);

		heroList = new ArrayList<Integer>();
		heroList.add(new Integer(0));
		for (int i = 1; i < 4; i++) {
			heroList.add(new Integer(-1));
		}

		this.towerManager = new TowerManager(skill_Profile.getRangeLevel(),
				skill_Profile.getMeleeLevel(), skill_Profile.getHeroLevel(),
				heroList);
		this.scenario = (new ScenarioInitializer(towerManager))
				.initialize(mapID);

		this.selectedTower = null;
		BaoVeBienCuong.resetSpeed();

		this.mapID = mapID;
		this.userID = userID;
		this.difficulty = difficulty;
		this.soundManager = soundManager;

		Tower.setSoundManager(soundManager);
		this.scenario.init(difficulty);
	}

	public void update(float delta) {
		scenario.update(delta);
		magicManager.update(delta, scenario);
	}

	public int getMapID() {
		return mapID;
	}

	public int getUserID() {
		return userID;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public CurrentEnemyWave getCurrentEnemyWave() {
		return scenario.getCurrentEnemyWave();
	}

	public Scenario getScenario() {
		return scenario;
	}

	public int getState() {
		return scenario.getState();
	}

	public BattleMap getMap() {
		return this.scenario.getBattleMap();
	}

	public int getNumCols() {
		return scenario.getBattleMap().getNumberColumns();
	}

	public int getNumRows() {
		return scenario.getBattleMap().getNumberRows();
	}

	public Tower getSelectedTower() {
		return selectedTower;
	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}

	public boolean isHasShogunTower(int heroID) {
		return scenario.isHasHero(heroID);
	}

	public Enemy getSelectedEnemy() {
		return scenario.getSpecialEnemy();
	}

	public Skill_Profile getSkill_Profile() {
		return skill_Profile;
	}

	public UnlockArmy_Profile getUnlockProfile() {
		return unlockProfile;
	}

	public int getGold() {
		return scenario.getGold();
	}

	public boolean isEnoughGold(int goldCost) {
		return scenario.isEnoughGold(goldCost);
	}

	public boolean isEnoughGoldLevelUp() {
		return scenario.isEnoughGold(levelUpTower.getGoldCost());
	}

	public boolean isEnoughGoldUpgrade(int i) {
		return scenario.isEnoughGold(upgradeTowerData[i].getGoldCost());
	}

	public boolean[] isNotEnoughGold() {
		return towerManager.isNotEnoughGold(scenario.getGold());
	}

	public boolean[] isNotEnoughGoldShogun() {
		return towerManager.isNotEnoughGoldShogun(scenario.getGold());
	}

	public int getCurrentWaveNumber() {
		return scenario.getCurrentWaveID();
	}

	public int getNumberWave() {
		return scenario.getNumberWave();
	}

	public int getPlayerHP() {
		return scenario.getPlayerHP();
	}

	public MagicManager getMagicManager() {
		return magicManager;
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public TowerData getUpgradeTowerData(int type) {
		if (upgradeTowerData != null) {
			if (type < upgradeTowerData.length && type > -1) {
				return upgradeTowerData[type];
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public ArrayList<MagicObject> getROFList() {
		return magicManager.getROFList();
	}

	public float getROFCooldownPercent() {
		return magicManager.getROFCooldownPercent();
	}

	public float getROFCooldown() {
		return magicManager.getROFCooldown();
	}

	public ArrayList<MagicObject> getTrapList() {
		return magicManager.getTrapList();
	}

	public float getTrapCooldownPercent() {
		return magicManager.getTrapCooldownPercent();
	}

	public int getNumberTrap() {
		return magicManager.getNumberTrap();
	}

	public void useMagic(int magicID, Coordinate castPosition) {
		BattleMap battleMap = scenario.getBattleMap();
		Coordinate temp = battleMap.convertToCell(castPosition);
		magicManager.useMagic(scenario, temp, magicID);
	}

	public void callEnemyWave() {
		scenario.callEnemyWave();
	}

	public void processSelectedCell(int[] selectedCell) {
		selectedTower = scenario.isOccupied(selectedCell[0], selectedCell[1]);
		if (selectedTower == null) {
			scenario.setSpecialEnemy(selectedCell[0], selectedCell[1]);
		}

		if (scenario.processGoldSelect(selectedCell[0], selectedCell[1]) != null) {
			soundManager.play(SoundDataManager.GOLD, false);
		}
	}

	public ArrayList<GoldData> getGoldList() {
		return scenario.getGoldList();
	}

	public void buildNewTower(int id, Coordinate buildPosition) {
		int dataID = getTowerID(id);
		scenario.addTower(towerManager.getTower(dataID), dataID, buildPosition);
	}

	public void buildNewShogun(int id, Coordinate buildPosition) {
		int dataID = getShogunID(id);
		scenario.addShogunTower(towerManager.getShogunTower(dataID), dataID,
				buildPosition);
	}

	public void upgradeLevel() {
		if (levelUpTower != null) {
			if (levelUpTower.getGoldCost() <= scenario.getGold()) {
				scenario.setGold(scenario.getGold()
						- levelUpTower.getGoldCost());
				selectedTower.upgradeTower(levelUpTower);
			}
		}
	}

	public void upgradeTowerType(int type) {
		if (upgradeTowerData[type] != null) {
			if (upgradeTowerData[type].getGoldCost() <= scenario.getGold()) {
				if (isUpgradableInProfile(selectedTower.getDataID(), type)) {
					scenario.setGold(scenario.getGold()
							- upgradeTowerData[type].getGoldCost());
					selectedTower.upgradeTower(upgradeTowerData[type]);
				}
			}
		}
	}

	public boolean isUpgradableInProfile(int dataID, int type) {
		switch (dataID) {
		case ITowerConstants.BASIC_ARCHER_LV3:
			if (type == 0) {
				if (unlockProfile.isArcher_1() == true) {
					return true;
				} else {
					return false;
				}
			} else {
				if (unlockProfile.isArcher_2() == true) {
					return true;
				} else {
					return false;
				}
			}

		case ITowerConstants.BASIC_SWORD_LV3:
			if (type == 0) {
				if (unlockProfile.isSword_1() == true) {
					return true;
				} else {
					return false;
				}
			} else {
				if (unlockProfile.isSword_2() == true) {
					return true;
				} else {
					return false;
				}
			}

		case ITowerConstants.BASIC_KNIGHT_LV3:
			if (type == 0) {
				if (unlockProfile.isKnight_1() == true) {
					return true;
				} else {
					return false;
				}
			} else {
				if (unlockProfile.isKnight_2() == true) {
					return true;
				} else {
					return false;
				}
			}

		case ITowerConstants.BASIC_SHAMAN_LV3:
			if (type == 0) {
				if (unlockProfile.isShaman_1() == true) {
					return true;
				} else {
					return false;
				}
			} else {
				if (unlockProfile.isShaman_2() == true) {
					return true;
				} else {
					return false;
				}
			}
		default:
			return false;
		}
	}

	public TowerData getTowerLevelUp() {
		return this.levelUpTower;
	}

	public TowerData[] getTowerUpgrade() {
		int dataID = selectedTower.getDataID();

		this.upgradeTowerData = towerManager.getTowerUpgrade(dataID);
		this.levelUpTower = towerManager.getTowerLevelUp(dataID);

		return this.upgradeTowerData;
	}

	public boolean isTowerUpgraded(int dataID, int type) {
		switch (dataID) {
		case ITowerConstants.BASIC_ARCHER_LV1:
		case ITowerConstants.BASIC_ARCHER_LV2:
		case ITowerConstants.BASIC_ARCHER_LV3:
			if (type == 0) {
				if (unlockProfile.isArcher_1()) {
					return true;
				}
			} else {
				if (unlockProfile.isArcher_2()) {
					return true;
				}
			}
			return false;

		case ITowerConstants.BASIC_SWORD_LV1:
		case ITowerConstants.BASIC_SWORD_LV2:
		case ITowerConstants.BASIC_SWORD_LV3:
			if (type == 0) {
				if (unlockProfile.isSword_1()) {
					return true;
				}
			} else {
				if (unlockProfile.isSword_2()) {
					return true;
				}
			}
			return false;

		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:
			if (type == 0) {
				if (unlockProfile.isShaman_1()) {
					return true;
				}
			} else {
				if (unlockProfile.isShaman_2()) {
					return true;
				}
			}
			return false;

		case ITowerConstants.BASIC_KNIGHT_LV1:
		case ITowerConstants.BASIC_KNIGHT_LV2:
		case ITowerConstants.BASIC_KNIGHT_LV3:
			if (type == 0) {
				if (unlockProfile.isKnight_1()) {
					return true;
				}
			} else {
				if (unlockProfile.isKnight_2()) {
					return true;
				}
			}
			return false;

		default:
			return false;
		}
	}

	public void removeTower() {
		scenario.removeTower(selectedTower);
	}

	public int getTowerID(int dataID) {
		switch (dataID) {
		case 0:
			return ITowerConstants.BASIC_ARCHER_LV1;
		case 1:
			return ITowerConstants.BASIC_SWORD_LV1;
		case 2:
			return ITowerConstants.BASIC_SHAMAN_LV1;
		case 3:
			return ITowerConstants.BASIC_KNIGHT_LV1;
		default:
			return ITowerConstants.BASIC_ARCHER_LV1;
		}
	}

	public int getTowerCost(int dataID) {
		switch (dataID) {
		case 0:
			return towerManager.getTower(ITowerConstants.BASIC_ARCHER_LV1)
					.getGoldCost();
		case 1:
			return towerManager.getTower(ITowerConstants.BASIC_SWORD_LV1)
					.getGoldCost();
		case 2:
			return towerManager.getTower(ITowerConstants.BASIC_SHAMAN_LV1)
					.getGoldCost();
		case 3:
			return towerManager.getTower(ITowerConstants.BASIC_KNIGHT_LV1)
					.getGoldCost();
		default:
			return towerManager.getTower(ITowerConstants.BASIC_ARCHER_LV1)
					.getGoldCost();
		}
	}

	public int getTowerImageID(int dataID) {
		switch (dataID) {
		case 0:
			return ButtonDataManager.ARCHER;
		case 1:
			return ButtonDataManager.SWORD;
		case 2:
			return ButtonDataManager.SHAMAN;
		case 3:
			return ButtonDataManager.KNIGHT;
		default:
			return ButtonDataManager.ARCHER;
		}
	}

	public int getShogunID(int dataID) {
		switch (dataID) {
		case 0:
			return ITowerConstants.SHOGUN_0;
		case 1:
			return ITowerConstants.SHOGUN_1;
		case 2:
			return ITowerConstants.SHOGUN_2;
		case 3:
			return ITowerConstants.SHOGUN_3;
		default:
			return ITowerConstants.SHOGUN_0;
		}
	}

	public int getShogunCost(int dataID) {
		switch (dataID) {
		case 0: {
			ShogunData data = towerManager
					.getShogunTower(ITowerConstants.SHOGUN_0);
			if (data != null) {
				return data.getGoldCost();
			} else {
				return 0;
			}
		}
		case 1: {
			ShogunData data = towerManager
					.getShogunTower(ITowerConstants.SHOGUN_1);
			if (data != null) {
				return data.getGoldCost();
			} else {
				return 0;
			}
		}
		case 2: {
			ShogunData data = towerManager
					.getShogunTower(ITowerConstants.SHOGUN_2);
			if (data != null) {
				return data.getGoldCost();
			} else {
				return 0;
			}
		}
		case 3: {
			ShogunData data = towerManager
					.getShogunTower(ITowerConstants.SHOGUN_3);
			if (data != null) {
				return data.getGoldCost();
			} else {
				return 0;
			}
		}
		default: {
			ShogunData data = towerManager
					.getShogunTower(ITowerConstants.SHOGUN_0);
			if (data != null) {
				return data.getGoldCost();
			} else {
				return 0;
			}
		}
		}
	}

	public int getShogunDataID(int dataID) {
		switch (dataID) {
		case 0:
			return ButtonDataManager.SHOGUN_0;
		case 1:
			return ButtonDataManager.SHOGUN_1;
		case 2:
			return ButtonDataManager.SHOGUN_2;
		case 3:
			return ButtonDataManager.SHOGUN_3;
		default:
			return ButtonDataManager.SHOGUN_0;
		}
	}

	public int getHeroLevel(int id) {
		if (id > -1 && id < heroList.size()) {
			return heroList.get(id);
		} else {
			return -1;
		}
	}
}