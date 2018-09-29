package com.gamenc2014.Loan12SuQuan.model.towerData;

public class ShogunData extends TowerData {

	private int auraRange;
	private int auraType;
	private float auraRate;
	private int type;

	public ShogunData(EnumShogunList tower, int level) {
		super(tower, level);
		this.auraRange = tower.getAuraRange();
		this.auraRange = tower.getAuraType();
		this.auraRate = tower.getAuraRate();
		this.type = tower.getType();
	}

	@Override
	public void configData(int level) {
		configHeroData(level);
	}

	public int getAuraRange() {
		return auraRange;
	}

	public void setAuraRange(int auraRange) {
		this.auraRange = auraRange;
	}

	public int getAuraType() {
		return auraType;
	}

	public void setAuraType(int auraType) {
		this.auraType = auraType;
	}

	public float getAuraRate() {
		return auraRate;
	}

	public void setAuraRate(float auraRate) {
		this.auraRate = auraRate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void configHeroData(int level) {
		switch (level) {
		case 1:
			this.damage = this.damage << 1;
			return;

		case 2:
			this.damage = this.damage << 1;
			this.attackRange = this.attackRange << 1;
			return;

		case 3:
			this.damage = this.damage << 1;
			this.attackRange = this.attackRange << 1;
			this.ignoreDefense = true;
			return;

		case 4:
			this.damage = this.damage << 1;
			this.attackRange = this.attackRange << 1;
			this.ignoreDefense = true;
			this.auraRange = auraRange << 1;
			return;
		}
	}
}
