package com.leaptechjsc.anakachyofthe12warlords.controller.logicManager;

import java.util.ArrayList;

import com.leaptechjsc.anakachyofthe12warlords.model.towerData.EnumShogunList;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.EnumTowerList;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ShogunData;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.TowerData;


public class TowerManager {
	private int rangeLevel;
	private int meleeLevel;
	private int heroLevel;
	private ArrayList<Integer> heroList;
	//
	private TowerData[] towerDataList;
	private ArrayList<ShogunData> shogunTowerDataList;

	public TowerManager(int rangeLevel, int meleeLevel, int heroLevel,
			ArrayList<Integer> heroList) {
		this.rangeLevel = rangeLevel;
		this.meleeLevel = meleeLevel;
		this.heroLevel = heroLevel;
		this.heroList = heroList;

		this.towerDataList = new TowerData[36];
		this.shogunTowerDataList = new ArrayList<ShogunData>();

		initialize();
	}

	public void initialize() {
		int i = 0;
		for (EnumTowerList tower : EnumTowerList.values()) {
			towerDataList[i] = new TowerData(tower, getLevel(tower.getDataID()));
			i++;
		}

		for (i = 0; i < heroList.size(); i++) {
			if (heroList.get(i) > -1) {
				shogunTowerDataList.add(new ShogunData(EnumShogunList
						.getShogunData(getShogunID(i)), heroLevel));
			}
		}
	}

	public int getShogunID(int id) {
		switch (id) {
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

	public TowerData getTower(int dataID) {
		if (dataID > -1 && dataID < towerDataList.length) {
			return towerDataList[dataID];
		} else {
			return null;
		}
	}

	public ShogunData getShogunTower(int dataID) {
		for (ShogunData temp : shogunTowerDataList) {
			if (temp.getDataID() == dataID) {
				return temp;
			}
		}
		return null;
	}

	public int getLevel(int dataID) {
		switch (dataID) {
		case ITowerConstants.BASIC_ARCHER_LV1:
		case ITowerConstants.BASIC_ARCHER_LV2:
		case ITowerConstants.BASIC_ARCHER_LV3:

		case ITowerConstants.ARCHER_1_LV1:
		case ITowerConstants.ARCHER_1_LV2:
		case ITowerConstants.ARCHER_1_LV3:

		case ITowerConstants.ARCHER_2_LV1:
		case ITowerConstants.ARCHER_2_LV2:
		case ITowerConstants.ARCHER_2_LV3:

		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:

		case ITowerConstants.SHAMAN_1_LV1:
		case ITowerConstants.SHAMAN_1_LV2:
		case ITowerConstants.SHAMAN_1_LV3:

		case ITowerConstants.SHAMAN_2_LV1:
		case ITowerConstants.SHAMAN_2_LV2:
		case ITowerConstants.SHAMAN_2_LV3:
			return rangeLevel;

		case ITowerConstants.BASIC_SWORD_LV1:
		case ITowerConstants.BASIC_SWORD_LV2:
		case ITowerConstants.BASIC_SWORD_LV3:

		case ITowerConstants.SWORD_1_LV1:
		case ITowerConstants.SWORD_1_LV2:
		case ITowerConstants.SWORD_1_LV3:

		case ITowerConstants.SWORD_2_LV1:
		case ITowerConstants.SWORD_2_LV2:
		case ITowerConstants.SWORD_2_LV3:

		case ITowerConstants.BASIC_KNIGHT_LV1:
		case ITowerConstants.BASIC_KNIGHT_LV2:
		case ITowerConstants.BASIC_KNIGHT_LV3:

		case ITowerConstants.KNIGHT_1_LV1:
		case ITowerConstants.KNIGHT_1_LV2:
		case ITowerConstants.KNIGHT_1_LV3:

		case ITowerConstants.KNIGHT_2_LV1:
		case ITowerConstants.KNIGHT_2_LV2:
		case ITowerConstants.KNIGHT_2_LV3:
			return meleeLevel;
		default:
			return 0;
		}
	}

	public boolean isUpgradable(int dataID) {
		if (dataID % 3 < 2) {
			return true;
		} else {
			return false;
		}
	}

	public TowerData getTowerLevelUp(int dataID) {
		if (dataID > -1 && dataID < towerDataList.length) {
			if (isUpgradable(dataID)) {
				return towerDataList[dataID + 1];
			}
		}
		return null;
	}

	public TowerData[] getTowerUpgrade(int dataID) {
		TowerData[] result = null;
		switch (dataID) {
		case ITowerConstants.BASIC_ARCHER_LV1:
		case ITowerConstants.BASIC_ARCHER_LV2:
		case ITowerConstants.BASIC_ARCHER_LV3:
			result = new TowerData[2];
			result[0] = towerDataList[ITowerConstants.ARCHER_1_LV1];
			result[1] = towerDataList[ITowerConstants.ARCHER_2_LV1];
			return result;

		case ITowerConstants.BASIC_SWORD_LV1:
		case ITowerConstants.BASIC_SWORD_LV2:
		case ITowerConstants.BASIC_SWORD_LV3:
			result = new TowerData[2];
			result[0] = towerDataList[ITowerConstants.SWORD_1_LV1];
			result[1] = towerDataList[ITowerConstants.SWORD_2_LV1];
			return result;

		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:
			result = new TowerData[2];
			result[0] = towerDataList[ITowerConstants.SHAMAN_1_LV1];
			result[1] = towerDataList[ITowerConstants.SHAMAN_2_LV1];
			return result;

		case ITowerConstants.BASIC_KNIGHT_LV1:
		case ITowerConstants.BASIC_KNIGHT_LV2:
		case ITowerConstants.BASIC_KNIGHT_LV3:
			result = new TowerData[2];
			result[0] = towerDataList[ITowerConstants.KNIGHT_1_LV1];
			result[1] = towerDataList[ITowerConstants.KNIGHT_2_LV1];
			return result;

		default:
			return result;
		}
	}

	public boolean[] isNotEnoughGold(int gold) {
		boolean[] result = new boolean[4];

		if (towerDataList[ITowerConstants.BASIC_ARCHER_LV1].getGoldCost() <= gold) {
			result[0] = false;
		} else {
			result[0] = true;
		}

		if (towerDataList[ITowerConstants.BASIC_SWORD_LV1].getGoldCost() <= gold) {
			result[1] = false;
		} else {
			result[1] = true;
		}

		if (towerDataList[ITowerConstants.BASIC_SHAMAN_LV1].getGoldCost() <= gold) {
			result[2] = false;
		} else {
			result[2] = true;
		}

		if (towerDataList[ITowerConstants.BASIC_KNIGHT_LV1].getGoldCost() <= gold) {
			result[3] = false;
		} else {
			result[3] = true;
		}

		return result;
	}

	public boolean[] isNotEnoughGoldShogun(int gold) {
		boolean[] result = new boolean[shogunTowerDataList.size()];

		ShogunData temp = getShogunTower(ITowerConstants.SHOGUN_0);

		if (temp.getGoldCost() <= gold) {
			result[0] = false;
		} else {
			result[0] = true;
		}

		temp = getShogunTower(ITowerConstants.SHOGUN_1);
		if (temp != null) {
			if (temp.getGoldCost() <= gold) {
				result[1] = false;
			} else {
				result[1] = true;
			}
		}

		temp = getShogunTower(ITowerConstants.SHOGUN_2);
		if (temp != null) {
			if (temp.getGoldCost() <= gold) {
				result[2] = false;
			} else {
				result[2] = true;
			}
		}

		temp = getShogunTower(ITowerConstants.SHOGUN_3);
		if (temp != null) {
			if (temp.getGoldCost() <= gold) {
				result[3] = false;
			} else {
				result[3] = true;
			}
		}

		return result;
	}

	public static boolean isShogun(int dataID) {
		switch (dataID) {
		case ITowerConstants.SHOGUN_0:
		case ITowerConstants.SHOGUN_1:
		case ITowerConstants.SHOGUN_2:
		case ITowerConstants.SHOGUN_3:
			return true;
		default:
			return false;
		}
	}
}
