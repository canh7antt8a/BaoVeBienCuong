package com.gamenc2014.Loan12SuQuan.model.towerData;

import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;

public class TowerData {

	protected int dataID;
	protected int goldCost;
	protected int damage;
	//
	protected int attackRange;
	protected float attackTime;
	protected boolean isAirAttack;
	//
	protected int criticalChance;
	protected float criticalDamageRate;
	//
	protected int slowChance;
	protected float slowRate;
	//
	protected boolean ignoreDefense;
	protected boolean ignoreEvasion;
	//
	protected int aoeNumber;
	//
	protected float auraReceive;

	public TowerData(EnumTowerList tower, int level) {
		this.dataID = tower.getDataID();
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
		configData(level);
	}

	public TowerData(EnumShogunList tower, int level) {
		this.dataID = tower.getDataID();
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
		configData(level);
	}

	public int getDataID() {
		return dataID;
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

	public float getAuraReceive() {
		return auraReceive;
	}

	public void setAuraReceive(float aureReceive) {
		this.auraReceive = aureReceive;
	}

	public void configData(int level) {
		switch (dataID) {
		case ITowerConstants.BASIC_ARCHER_LV1:
		case ITowerConstants.BASIC_ARCHER_LV2:
		case ITowerConstants.BASIC_ARCHER_LV3:
			configRangeData(level);
			return;

		case ITowerConstants.ARCHER_1_LV1:
		case ITowerConstants.ARCHER_1_LV2:
		case ITowerConstants.ARCHER_1_LV3:
			configRangeData(level);
			if (level > 0) {
				this.damage = (int) (this.damage * 1.5f);
			}
			return;

		case ITowerConstants.ARCHER_2_LV1:
		case ITowerConstants.ARCHER_2_LV2:
		case ITowerConstants.ARCHER_2_LV3:
			configRangeData(level);
			if (level > 1) {
				this.ignoreDefense = true;
			}
			return;

		case ITowerConstants.BASIC_SWORD_LV1:
		case ITowerConstants.BASIC_SWORD_LV2:
		case ITowerConstants.BASIC_SWORD_LV3:
			configMeleeData(level);
			return;

		case ITowerConstants.SWORD_1_LV1:
		case ITowerConstants.SWORD_1_LV2:
		case ITowerConstants.SWORD_1_LV3:
			configMeleeData(level);
			if (level > 0) {
				this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			}
			return;

		case ITowerConstants.SWORD_2_LV1:
		case ITowerConstants.SWORD_2_LV2:
		case ITowerConstants.SWORD_2_LV3:
			configMeleeData(level);
			if (level > 1) {
				this.attackTime = this.attackTime / 1.5f;
			}
			return;

		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:
			configRangeData(level);
			return;

		case ITowerConstants.SHAMAN_1_LV1:
		case ITowerConstants.SHAMAN_1_LV2:
		case ITowerConstants.SHAMAN_1_LV3:
			configRangeData(level);
			if (level > 2) {
				this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			}
			return;

		case ITowerConstants.SHAMAN_2_LV1:
		case ITowerConstants.SHAMAN_2_LV2:
		case ITowerConstants.SHAMAN_2_LV3:
			configRangeData(level);
			if (level > 3) {
				this.slowChance = 25;
				this.slowRate = 0.25f;
			}
			return;

		case ITowerConstants.BASIC_KNIGHT_LV1:
		case ITowerConstants.BASIC_KNIGHT_LV2:
		case ITowerConstants.BASIC_KNIGHT_LV3:
			configMeleeData(level);
			return;

		case ITowerConstants.KNIGHT_1_LV1:
		case ITowerConstants.KNIGHT_1_LV2:
		case ITowerConstants.KNIGHT_1_LV3:
			configMeleeData(level);
			if (level > 2) {
				this.damage = (int) (this.damage * 1.5f);
			}
			return;

		case ITowerConstants.KNIGHT_2_LV1:
		case ITowerConstants.KNIGHT_2_LV2:
		case ITowerConstants.KNIGHT_2_LV3:
			configMeleeData(level);
			if (level > 3) {
				this.ignoreDefense = true;
			}
			return;
		}
	}

	public void configRangeData(int level) {
		switch (level) {
		case 1:
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			return;

		case 2:
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			this.damage = (int) (this.damage * 1.5f);
			return;

		case 3:
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			this.damage = (int) (this.damage * 1.5f);
			this.goldCost = (int) (this.goldCost * 0.75f);
			return;

		case 4:
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			this.damage = (int) (this.damage * 1.5f);
			this.goldCost = (int) (this.goldCost * 0.75f);
			this.auraReceive = 2;
			return;
		}
	}

	public void configMeleeData(int level) {
		switch (level) {
		case 1:
			this.damage = (int) (this.damage * 1.25f);
			return;

		case 2:
			this.damage = (int) (this.damage * 1.25f);
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			return;

		case 3:
			this.damage = (int) (this.damage * 1.25f);
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			this.goldCost = (int) (this.goldCost * 0.75f);
			return;

		case 4:
			this.damage = (int) (this.damage * 1.25f);
			this.attackRange += ICommonConstants.DEFAULT_TILE_SIZE;
			this.goldCost = (int) (this.goldCost * 0.75f);
			this.auraReceive = 2;
			return;
		}
	}
}
