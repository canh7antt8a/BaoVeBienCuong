package com.leaptechjsc.anakachyofthe12warlords.model.towerData;

public enum EnumShogunList {
	SHOGUN_0(ITowerConstants.SHOGUN_0, "Shogun 0", 500, 200, 256, 1.5f,
			ITowerConstants.ATTACK_AIR, 0, 0f, 0, 0f, false, false, 0, 5, 128,
			ITowerConstants.MELEE_TYPE, 1f, ITowerConstants.MELEE_TYPE),
	//
	SHOGUN_1(ITowerConstants.SHOGUN_1, "Shogun 1", 50, 10, 128, 1.5f,
			ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f, false, false, 0,
			5, 128, ITowerConstants.MELEE_TYPE, 1f, ITowerConstants.MELEE_TYPE),
	//
	SHOGUN_2(ITowerConstants.SHOGUN_2, "Shogun 2", 50, 10, 128, 1.5f,
			ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f, false, false, 0,
			5, 128, ITowerConstants.MELEE_TYPE, 1f, ITowerConstants.MELEE_TYPE),
	//
	SHOGUN_3(ITowerConstants.SHOGUN_3, "Shogun 3", 50, 10, 128, 1.5f,
			ITowerConstants.UNABLE_ATTACK_AIR, 0, 0f, 0, 0f, false, false, 0,
			5, 128, ITowerConstants.MELEE_TYPE, 1f, ITowerConstants.MELEE_TYPE);
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
	private int attackFrame;
	//
	private int auraRange;
	private int auraType;
	private float auraRate;
	private int type;

	private EnumShogunList(int dataID, String name, int goldCost, int damage,
			int attackRange, float attackTime, boolean isAirAttack,
			int criticalChance, float criticalDamageRate, int slowChance,
			float slowRate, boolean ignoreDefense, boolean ignoreEvasion,
			int aoeNumber, int attackFrame, int auraRange, int auraType,
			float auraRate, int type) {
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
		this.attackFrame = attackFrame;

		this.auraRange = auraRange;
		this.auraType = auraType;
		this.auraRate = auraRate;
		this.type = type;
	}

	public int getDataID() {
		return dataID;
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

	public int getAttackFrame() {
		return attackFrame;
	}

	public int getAuraRange() {
		return auraRange;
	}

	public int getAuraType() {
		return auraType;
	}

	public float getAuraRate() {
		return auraRate;
	}

	public int getType() {
		return type;
	}

	public static EnumShogunList getShogunData(int dataID) {
		for (EnumShogunList tower : EnumShogunList.values()) {
			if (tower.getDataID() == dataID) {
				return tower;
			}
		}
		return null;
	}
}