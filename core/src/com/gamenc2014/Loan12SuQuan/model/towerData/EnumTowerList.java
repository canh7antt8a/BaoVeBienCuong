package com.gamenc2014.Loan12SuQuan.model.towerData;

public enum EnumTowerList {
	BASIC_SWORD_LV1(ITowerConstants.BASIC_SWORD_LV1, "Swordman", 50,
			10, 128, 1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f,
			false, false, 0, 0),
	//
	BASIC_SWORD_LV2(ITowerConstants.BASIC_SWORD_LV2, "Swordman", 30,
			15, 128, 1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f,
			false, false, 0, 0),
	//
	BASIC_SWORD_LV3(ITowerConstants.BASIC_SWORD_LV3, "Swordman", 30,
			30, 128, 1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f,
			false, false, 0, 0),
	//
	SWORD_1_LV1(ITowerConstants.SWORD_1_LV1, "Blademan", 70, 50, 256,
			1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 1.5f, 0, 0f, false,
			false, 2, 5000),
	//
	SWORD_1_LV2(ITowerConstants.SWORD_1_LV2, "Blademan", 70, 75, 256,
			1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 1.5f, 0, 0f, false,
			false, 2, 5000),
	//
	SWORD_1_LV3(ITowerConstants.SWORD_1_LV3, "Blademan", 70, 100, 256,
			1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 1.5f, 0, 0f, false,
			false, 2, 5000),
	//
	SWORD_2_LV1(ITowerConstants.SWORD_2_LV1, "Paladin", 40, 40, 128,
			1f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f, true, true, 0,
			6000),
	//
	SWORD_2_LV2(ITowerConstants.SWORD_2_LV2, "Paladin", 40, 50, 128,
			1f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f, true, true, 0,
			6000),
	//
	SWORD_2_LV3(ITowerConstants.SWORD_2_LV3, "Paladin", 40, 60, 128,
			1f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f, true, true, 0,
			6000),
	//
	BASIC_ARCHER_LV1(ITowerConstants.BASIC_ARCHER_LV1, "Archer",
			80, 5, 384, 1.5f, ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false,
			false, 0, 0),
	//
	BASIC_ARCHER_LV2(ITowerConstants.BASIC_ARCHER_LV2, "Archer",
			40, 10, 384, 1.5f, ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false,
			false, 0, 0),
	//
	BASIC_ARCHER_LV3(ITowerConstants.BASIC_ARCHER_LV3, "Archer",
			40, 15, 384, 1.5f, ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false,
			false, 0, 0),
	//
	ARCHER_1_LV1(ITowerConstants.ARCHER_1_LV1, "Hunter", 70, 20, 384,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 10000),
	//
	ARCHER_1_LV2(ITowerConstants.ARCHER_1_LV2, "Hunter", 70, 30, 384,
			1f, ITowerConstants.ATTACK_AIR, 25, 3f, 0, 0f, true, true, 0, 10000),
	//
	ARCHER_1_LV3(ITowerConstants.ARCHER_1_LV3, "Hunter", 70, 40, 384,
			1f, ITowerConstants.ATTACK_AIR, 25, 4f, 0, 0f, true, true, 0, 10000),
	//
	ARCHER_2_LV1(ITowerConstants.ARCHER_2_LV1, "Ranger", 70, 30, 384,
			1.5f, ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false, false, 2,
			11000),
	//
	ARCHER_2_LV2(ITowerConstants.ARCHER_2_LV2, "Ranger", 70, 40, 384,
			1.5f, ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false, false, 2,
			11000),
	//
	ARCHER_2_LV3(ITowerConstants.ARCHER_2_LV3, "Ranger", 70, 50, 384,
			1.5f, ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false, false, 2,
			11000),
	//
	BASIC_KNIGHT_LV1(ITowerConstants.BASIC_KNIGHT_LV1, "Babarian",
			100, 20, 128, 1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 2f, 0,
			0f, false, false, 0, 0),
	//
	BASIC_KNIGHT_LV2(ITowerConstants.BASIC_KNIGHT_LV2, "Babarian",
			50, 30, 128, 1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 3f, 0,
			0f, false, false, 0, 0),
	//
	BASIC_KNIGHT_LV3(ITowerConstants.BASIC_KNIGHT_LV3, "Babarian",
			50, 50, 128, 1.5f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 4f, 0,
			0f, false, false, 0, 0),
	//
	KNIGHT_1_LV1(ITowerConstants.KNIGHT_1_LV1, "Knight", 100, 100,
			128, 2f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 25, 0f, false,
			true, 0, 15000),
	//
	KNIGHT_1_LV2(ITowerConstants.KNIGHT_1_LV2, "Knight", 100, 150,
			128, 2f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 25, 0f, false,
			true, 0, 15000),
	//
	KNIGHT_1_LV3(ITowerConstants.KNIGHT_1_LV3, "Knight", 100, 300,
			128, 2f, ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 25, 0f, false,
			true, 0, 15000),
	//
	KNIGHT_2_LV1(ITowerConstants.KNIGHT_2_LV1, "Lancer", 100, 60,
			128, 0.6f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 6f, 0, 0f, false,
			false, 0, 16000),
	//
	KNIGHT_2_LV2(ITowerConstants.KNIGHT_2_LV2, "Lancer", 100, 80,
			128, 0.6f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 6f, 0, 0f, false,
			false, 0, 16000),
	//
	KNIGHT_2_LV3(ITowerConstants.KNIGHT_2_LV3, "Lancer", 100, 100,
			128, 0.6f, ITowerConstants.UNABLE_ATTACK_AIR, 25, 6f, 0, 0f, false,
			false, 0, 16000),
	//
	BASIC_SHAMAN_LV1(ITowerConstants.BASIC_SHAMAN_LV1, "Mage",
			100, 15, 512, 1.5f, ITowerConstants.ATTACK_AIR, 25, 1.5f, 25, 0.7f,
			true, false, 0, 0),
	//
	BASIC_SHAMAN_LV2(ITowerConstants.BASIC_SHAMAN_LV2, "Mage",
			50, 30, 512, 1.5f, ITowerConstants.ATTACK_AIR, 25, 1.5f, 25, 0.5f,
			true, false, 0, 0),
	//
	BASIC_SHAMAN_LV3(ITowerConstants.BASIC_SHAMAN_LV3, "Mage",
			50, 100, 512, 1.5f, ITowerConstants.ATTACK_AIR, 25, 1.5f, 25, 0.3f,
			true, false, 0, 0),
	//
	SHAMAN_1_LV1(ITowerConstants.SHAMAN_1_LV1, "Priest", 70, 20, 512,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 20000),
	//
	SHAMAN_1_LV2(ITowerConstants.SHAMAN_1_LV2, "Priest", 70, 20, 512,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 20000),
	//
	SHAMAN_1_LV3(ITowerConstants.SHAMAN_1_LV3, "Priest", 70, 20, 512,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 20000),
	//
	SHAMAN_2_LV1(ITowerConstants.SHAMAN_2_LV1, "Pyromancer", 70, 20, 512,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 21000),
	//
	SHAMAN_2_LV2(ITowerConstants.SHAMAN_2_LV2, "Pyromancer", 70, 20, 512,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 21000),
	//
	SHAMAN_2_LV3(ITowerConstants.SHAMAN_2_LV3, "Pyromancer", 70, 20, 512,
			1f, ITowerConstants.ATTACK_AIR, 25, 2f, 0, 0f, true, true, 0, 21000);
	//

	private int dataID;
	private String name;
	private int goldCost;
	private int damage;
	//
	private int attackRange;
	private float attackTime;
	private boolean isAirAttack;
	//
	private int criticalChance;
	private float criticalDamageRate;
	//
	private int slowChance;
	private float slowRate;
	//
	private boolean ignoreDefense;
	private boolean ignoreEvasion;
	//
	private int aoeNumber;
	//
	private int unlockGold;

	private EnumTowerList(int dataID, String name, int goldCost, int damage,
			int attackRange, float attackTime, boolean isAirAttack,
			int criticalChance, float criticalDamageRate, int slowChance,
			float slowRate, boolean ignoreDefense, boolean ignoreEvasion,
			int aoeNumber, int unlockGold) {
		this.dataID = dataID;
		this.name = name;
		this.goldCost = goldCost;
		this.damage = damage;

		this.attackRange = attackRange;
		this.attackTime = attackTime;
		this.isAirAttack = isAirAttack;

		this.criticalChance = criticalChance;
		this.criticalDamageRate = criticalDamageRate;

		this.slowChance = slowChance;
		this.slowRate = slowRate;

		this.ignoreDefense = ignoreDefense;
		this.ignoreEvasion = ignoreEvasion;

		this.aoeNumber = aoeNumber;

		this.unlockGold = unlockGold;
	}

	public int getDataID() {
		return dataID;
	}

	public int getLevel() {
		return (dataID % 3 + 1);
	}

	public String getName() {
		return name;
	}

	public int getGoldCost() {
		return goldCost;
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

	public int getUnlockGold() {
		return unlockGold;
	}

	public static boolean isEnoughGold(int gold, EnumTowerList tower) {
		if (gold >= tower.getUnlockGold()) {
			return true;
		} else {
			return false;
		}
	}

	public static EnumTowerList getTowerData(int dataID) {
		for (EnumTowerList tower : EnumTowerList.values()) {
			if (tower.getDataID() == dataID) {
				return tower;
			}
		}
		return null;
	}

	public static int getGoldCost(int dataID) {
		EnumTowerList tower = EnumTowerList.getTowerData(dataID);
		switch (tower.getDataID()) {
		case ITowerConstants.BASIC_ARCHER_LV1:
			return EnumTowerList.BASIC_ARCHER_LV1.getGoldCost();
		case ITowerConstants.BASIC_ARCHER_LV2:
			return (EnumTowerList.BASIC_ARCHER_LV1.getGoldCost() + EnumTowerList.BASIC_ARCHER_LV2
					.getGoldCost());
		case ITowerConstants.BASIC_ARCHER_LV3:
			return (EnumTowerList.BASIC_ARCHER_LV1.getGoldCost()
					+ EnumTowerList.BASIC_ARCHER_LV2.getGoldCost() + EnumTowerList.BASIC_ARCHER_LV3
						.getGoldCost());

		case ITowerConstants.ARCHER_1_LV1:
			return EnumTowerList.ARCHER_1_LV1.getGoldCost();
		case ITowerConstants.ARCHER_1_LV2:
			return (EnumTowerList.ARCHER_1_LV1.getGoldCost() + EnumTowerList.ARCHER_1_LV2
					.getGoldCost());
		case ITowerConstants.ARCHER_1_LV3:
			return (EnumTowerList.ARCHER_1_LV1.getGoldCost()
					+ EnumTowerList.ARCHER_1_LV2.getGoldCost() + EnumTowerList.ARCHER_1_LV3
						.getGoldCost());

		case ITowerConstants.ARCHER_2_LV1:
			return EnumTowerList.ARCHER_2_LV1.getGoldCost();
		case ITowerConstants.ARCHER_2_LV2:
			return (EnumTowerList.ARCHER_2_LV1.getGoldCost() + EnumTowerList.ARCHER_2_LV2
					.getGoldCost());
		case ITowerConstants.ARCHER_2_LV3:
			return (EnumTowerList.ARCHER_2_LV1.getGoldCost()
					+ EnumTowerList.ARCHER_2_LV2.getGoldCost() + EnumTowerList.ARCHER_2_LV3
						.getGoldCost());

		case ITowerConstants.BASIC_SWORD_LV1:
			return EnumTowerList.BASIC_SWORD_LV1.getGoldCost();
		case ITowerConstants.BASIC_SWORD_LV2:
			return (EnumTowerList.BASIC_SWORD_LV1.getGoldCost() + EnumTowerList.BASIC_SWORD_LV2
					.getGoldCost());
		case ITowerConstants.BASIC_SWORD_LV3:
			return (EnumTowerList.BASIC_SWORD_LV1.getGoldCost()
					+ EnumTowerList.BASIC_SWORD_LV2.getGoldCost() + EnumTowerList.BASIC_SWORD_LV3
						.getGoldCost());

		case ITowerConstants.SWORD_1_LV1:
			return EnumTowerList.SWORD_1_LV1.getGoldCost();
		case ITowerConstants.SWORD_1_LV2:
			return (EnumTowerList.SWORD_1_LV1.getGoldCost() + EnumTowerList.SWORD_1_LV2
					.getGoldCost());
		case ITowerConstants.SWORD_1_LV3:
			return (EnumTowerList.SWORD_1_LV1.getGoldCost()
					+ EnumTowerList.SWORD_1_LV2.getGoldCost() + EnumTowerList.SWORD_1_LV3
						.getGoldCost());

		case ITowerConstants.SWORD_2_LV1:
			return EnumTowerList.SWORD_2_LV1.getGoldCost();
		case ITowerConstants.SWORD_2_LV2:
			return (EnumTowerList.SWORD_2_LV1.getGoldCost() + EnumTowerList.SWORD_2_LV2
					.getGoldCost());
		case ITowerConstants.SWORD_2_LV3:
			return (EnumTowerList.SWORD_2_LV1.getGoldCost()
					+ EnumTowerList.SWORD_2_LV2.getGoldCost() + EnumTowerList.SWORD_2_LV3
						.getGoldCost());

		case ITowerConstants.BASIC_SHAMAN_LV1:
			return EnumTowerList.BASIC_SHAMAN_LV1.getGoldCost();
		case ITowerConstants.BASIC_SHAMAN_LV2:
			return (EnumTowerList.BASIC_SHAMAN_LV1.getGoldCost() + EnumTowerList.BASIC_SHAMAN_LV2
					.getGoldCost());
		case ITowerConstants.BASIC_SHAMAN_LV3:
			return (EnumTowerList.BASIC_SHAMAN_LV1.getGoldCost()
					+ EnumTowerList.BASIC_SHAMAN_LV2.getGoldCost() + EnumTowerList.BASIC_SHAMAN_LV3
						.getGoldCost());

		case ITowerConstants.SHAMAN_1_LV1:
			return EnumTowerList.SHAMAN_1_LV1.getGoldCost();
		case ITowerConstants.SHAMAN_1_LV2:
			return (EnumTowerList.SHAMAN_1_LV1.getGoldCost() + EnumTowerList.SHAMAN_1_LV2
					.getGoldCost());
		case ITowerConstants.SHAMAN_1_LV3:
			return (EnumTowerList.SHAMAN_1_LV1.getGoldCost()
					+ EnumTowerList.SHAMAN_1_LV2.getGoldCost() + EnumTowerList.SHAMAN_1_LV3
						.getGoldCost());

		case ITowerConstants.SHAMAN_2_LV1:
			return EnumTowerList.SHAMAN_2_LV1.getGoldCost();
		case ITowerConstants.SHAMAN_2_LV2:
			return (EnumTowerList.SHAMAN_2_LV1.getGoldCost() + EnumTowerList.SHAMAN_2_LV2
					.getGoldCost());
		case ITowerConstants.SHAMAN_2_LV3:
			return (EnumTowerList.SHAMAN_2_LV1.getGoldCost()
					+ EnumTowerList.SHAMAN_2_LV2.getGoldCost() + EnumTowerList.SHAMAN_2_LV3
						.getGoldCost());

		case ITowerConstants.BASIC_KNIGHT_LV1:
			return EnumTowerList.BASIC_KNIGHT_LV1.getGoldCost();
		case ITowerConstants.BASIC_KNIGHT_LV2:
			return (EnumTowerList.BASIC_KNIGHT_LV1.getGoldCost() + EnumTowerList.BASIC_KNIGHT_LV2
					.getGoldCost());
		case ITowerConstants.BASIC_KNIGHT_LV3:
			return (EnumTowerList.BASIC_KNIGHT_LV1.getGoldCost()
					+ EnumTowerList.BASIC_KNIGHT_LV2.getGoldCost() + EnumTowerList.BASIC_KNIGHT_LV3
						.getGoldCost());

		case ITowerConstants.KNIGHT_1_LV1:
			return EnumTowerList.KNIGHT_1_LV1.getGoldCost();
		case ITowerConstants.KNIGHT_1_LV2:
			return (EnumTowerList.KNIGHT_1_LV1.getGoldCost() + EnumTowerList.KNIGHT_1_LV2
					.getGoldCost());
		case ITowerConstants.KNIGHT_1_LV3:
			return (EnumTowerList.KNIGHT_1_LV1.getGoldCost()
					+ EnumTowerList.KNIGHT_1_LV2.getGoldCost() + EnumTowerList.KNIGHT_1_LV3
						.getGoldCost());

		case ITowerConstants.KNIGHT_2_LV1:
			return EnumTowerList.KNIGHT_2_LV1.getGoldCost();
		case ITowerConstants.KNIGHT_2_LV2:
			return (EnumTowerList.KNIGHT_2_LV1.getGoldCost() + EnumTowerList.KNIGHT_2_LV2
					.getGoldCost());
		case ITowerConstants.KNIGHT_2_LV3:
			return (EnumTowerList.KNIGHT_2_LV1.getGoldCost()
					+ EnumTowerList.KNIGHT_2_LV2.getGoldCost() + EnumTowerList.KNIGHT_2_LV3
						.getGoldCost());

		default:
			return 0;
		}
	}
}
