package com.leaptechjsc.anakachyofthe12warlords.controller.profileManager;

import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;

public class Skill_Profile extends AbstractProfile {

	private int heroLevel;
	private int supportLevel;
	private int rangeLevel;
	private int meleeLevel;

	public Skill_Profile(BaoVeBienCuong baoVeBienCuong) {
		super(baoVeBienCuong);
		load();
	}

	@Override
	public void load() {
		super.load();

		this.heroLevel = userData.getInteger(IProfileConstants.HERO_LEVEL_TAG,
				0);
		this.supportLevel = userData.getInteger(IProfileConstants.SUPPORT_TAG,
				0);
		this.rangeLevel = userData.getInteger(IProfileConstants.RANGE_TAG, 0);
		this.meleeLevel = userData.getInteger(IProfileConstants.MELEE_TAG, 0);
	}

	public void saveDefaultProfile() {
		super.preSaveDefaultProfile();

		userData.putInteger(IProfileConstants.HERO_LEVEL_TAG, 0);
		userData.putInteger(IProfileConstants.SUPPORT_TAG, 0);
		userData.putInteger(IProfileConstants.RANGE_TAG, 0);
		userData.putInteger(IProfileConstants.MELEE_TAG, 0);
	}

	public int getHeroLevel() {
		return heroLevel;
	}

	public int getSupportLevel() {
		return supportLevel;
	}

	public int getRangeLevel() {
		return rangeLevel;
	}

	public int getMeleeLevel() {
		return meleeLevel;
	}
}
