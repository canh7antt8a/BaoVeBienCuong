package com.leaptechjsc.anakachyofthe12warlords.model.enemy;

public class EnemyDataUnit {
	private int dataID;
	private String name;
	private int damage;
	private int health;
	private float defense;
	private int speed;
	private int evasionRate;
	private int gold;
	private int type;
	private boolean isFlyUnit;
	private int goldDropRate;

	public EnemyDataUnit(EnumEnemyList data) {
		this.dataID = data.getDataID();
		this.name = data.getName();
		this.damage = data.getDamage();
		this.health = data.getHealth();
		this.defense = data.getDefense();
		this.speed = data.getSpeed();
		this.evasionRate = data.getEvasionRate();
		this.gold = data.getGold();
		this.type = data.getType();
		this.isFlyUnit = data.isFlyUnit();
		this.goldDropRate = data.getDropRate();
	}

	public int getDataID() {
		return dataID;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getEvasionRate() {
		return evasionRate;
	}

	public void setEvasionRate(int evasionRate) {
		this.evasionRate = evasionRate;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isFlyUnit() {
		return isFlyUnit;
	}

	public void setFlyUnit(boolean isFlyUnit) {
		this.isFlyUnit = isFlyUnit;
	}

	public int getGoldDropRate() {
		return goldDropRate;
	}

	public void setGoldDropRate(int goldDropRate) {
		this.goldDropRate = goldDropRate;
	}
}
