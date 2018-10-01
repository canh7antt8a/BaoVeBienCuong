package com.leaptechjsc.anakachyofthe12warlords.model.magic;

public enum EnumMagicList {
	RAIN_OF_FIRE(IMagicContants.RAIN_OF_FIRE, "Rain Of Fire", 100, 128, 2f, 3f,
			30f, 1f),
	//
	TRAP(IMagicContants.TRAP, "Trap", 200, 128, 0f, Float.MAX_VALUE, 0, 1f);
	private int dataID;
	private String name;
	private int damage;
	private int areaOfEffect;
	private float castTime;
	private float duration;
	private float cooldownTime;
	private float damageSpeed;

	private EnumMagicList(int dataID, String name, int damage,
			int areaOfEffect, float castTime, float duration,
			float cooldownTime, float damageSpeed) {
		this.dataID = dataID;
		this.name = name;
		this.damage = damage;
		this.areaOfEffect = areaOfEffect;
		this.castTime = castTime;
		this.duration = duration;
		this.cooldownTime = cooldownTime;
		this.damageSpeed = damageSpeed;
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

	public int getAreaOfEffect() {
		return areaOfEffect;
	}

	public float getCastTime() {
		return castTime;
	}

	public float getDuration() {
		return duration;
	}

	public float getCooldownTime() {
		return cooldownTime;
	}

	public float getDamageSpeed() {
		return damageSpeed;
	}

	public static EnumMagicList getMagicData(int dataID) {
		for (EnumMagicList magic : EnumMagicList.values()) {
			if (magic.getDataID() == dataID) {
				return magic;
			}
		}
		return null;
	}
}
