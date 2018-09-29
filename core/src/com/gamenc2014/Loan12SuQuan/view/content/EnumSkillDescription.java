package com.gamenc2014.Loan12SuQuan.view.content;



public enum EnumSkillDescription {
	MELEE_1(0, "Melee 1", "+25%", " melee damage", "+1",
			" Blademan range", 2, 0),
	//
	MELEE_2(1, "Melee 2", "+1", " melee range", "+50%",
			" Paladin speed", 3, 6000),
	//
	MELEE_3(2, "Melee 3", "-25%", " melee cost", "+50%",
			" Knight damage", 3, 9000),
	//
	MELEE_4(3, "Melee 4", "+100%", " area damage", "",
			" Lancer pierce damage", 4, 12000),
	//
	RANGE_1(10, "Range 1", "+1", " range", "+50%",
			" Hunter damage", 2, 0),
	//
	RANGE_2(11, "Range 2", "+50%", " ranged damage", "",
			" Ranger pierce damage", 3, 6000),
	//
	RANGE_3(12, "Range 3", "-25%", " ranged cost", "+1",
			" Priest range", 3, 9000),
	//
	RANGE_4(13, "Range 4", "+100%", " area damage", "+75%",
			" Pyromancer slow skill", 4, 12000),
	//
	SUPPORT_1(20, "Support 1", "x1.5", " Meteor damage", "+2",
			" number traps", 1, 0),
	//
	SUPPORT_2(21, "Support 2", "+2s", " Meteor duration", "+5", " number traps",
			2, 0),
	//
	SUPPORT_3(22, "Support 3", "-10s", " Meteor coodown", "+5s",
			" paralyzed trap", 2, 0),
	//
	SUPPORT_4(23, "Support 4", "x5", " Meteor damager", "x5",
			" trap damage", 2, 0),
	//
	HERO_1(30, "Hero 1", "x2", " Hero damage", "", "", 1, 0),
	//
	HERO_2(31, "Hero 2", "x2", " Hero range", "", "", 2, 3000),
	//
	HERO_3(32, "Hero 3", "", "Hero pierce damage", "", "", 2, 6000),
	//
	HERO_4(33, "Hero 4", "x2", " Hero aura radius", "", "", 2, 9000);
	//
	private int dataID;
	private String name;

	private String desc_1;
	private String up_1;

	private String desc_2;
	private String up_2;

	private int star;
	private int gold;

	private EnumSkillDescription(int dataID, String name, String desc_1,
			String up_1, String desc_2, String up_2, int star, int gold) {
		this.name = name;
		this.dataID = dataID;

		this.desc_1 = desc_1;
		this.up_1 = up_1;

		this.desc_2 = desc_2;
		this.up_2 = up_2;

		this.star = star;
		this.gold = gold;
	}

	public int getDataID() {
		return dataID;
	}

	public static int getDataID(int row_ID, int col_ID) {
		return (row_ID * 10 + col_ID);
	}

	public String getName() {
		return name;
	}

	public String getDesc_1() {
		return desc_1;
	}

	public String getDesc_2() {
		return desc_2;
	}

	public String getUp_1() {
		return up_1;
	}

	public String getUp_2() {
		return up_2;
	}

	public int getStar() {
		return star;
	}

	public int getGold() {
		return gold;
	}

	public static boolean isEnoughStar(int star, EnumSkillDescription skill) {
		if (star >= skill.getStar()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEnoughMoney(int money, EnumSkillDescription skill) {
		if (money >= skill.getGold()) {
			return true;
		} else {
			return false;
		}
	}

	public static EnumSkillDescription getSkillDescription(int row_ID,
			int col_ID) {
		int dataID = getDataID(row_ID, col_ID);
		for (EnumSkillDescription data : EnumSkillDescription.values()) {
			if (data.getDataID() == dataID) {
				return data;
			}
		}
		return null;
	}
}
