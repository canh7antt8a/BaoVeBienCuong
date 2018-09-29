package com.gamenc2014.Loan12SuQuan.model.bullet;

public enum EnumBulletList {

	SHAMAN_BULLET(IBulletConstants.BASIC_SHAMAN_BULLET, "Shaman Bullet", 768),
	//
	ARCHER_BULLET(IBulletConstants.BASIC_ARCHER_BULLET, "Archer Bullet", 768);
	private int dataID;
	private String name;
	private int speed;

	private EnumBulletList(int dataID, String name, int speed) {
		this.dataID = dataID;
		this.name = name;
		this.speed = speed;
	}

	public int getDataID() {
		return dataID;
	}

	public String getName() {
		return name;
	}

	public int getSpeed() {
		return speed;
	}

	public static EnumBulletList getBulletData(int dataID) {
		for (EnumBulletList bullet : EnumBulletList.values()) {
			if (bullet.getDataID() == dataID) {
				return bullet;
			}
		}
		return null;
	}
}
