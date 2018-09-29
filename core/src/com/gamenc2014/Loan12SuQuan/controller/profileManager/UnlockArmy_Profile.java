package com.gamenc2014.Loan12SuQuan.controller.profileManager;

import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;

public class UnlockArmy_Profile extends AbstractProfile {
	private boolean archer_1;
	private boolean archer_2;

	private boolean sword_1;
	private boolean sword_2;

	private boolean knight_1;
	private boolean knight_2;

	private boolean shaman_1;
	private boolean shaman_2;

	public UnlockArmy_Profile(BaoVeBienCuong baoVeBienCuong) {
		super(baoVeBienCuong);
		load();
	}

	@Override
	public void load() {
		super.load();

		this.archer_1 = userData.getBoolean(IProfileConstants.ARCHER_1_TAG,
				false);
		this.archer_2 = userData.getBoolean(IProfileConstants.ARCHER_2_TAG,
				false);

		this.knight_1 = userData.getBoolean(IProfileConstants.KNIGHT_1_TAG,
				false);
		this.knight_2 = userData.getBoolean(IProfileConstants.KNIGHT_2_TAG,
				false);

		this.sword_1 = userData
				.getBoolean(IProfileConstants.SWORD_1_TAG, false);
		this.sword_2 = userData
				.getBoolean(IProfileConstants.SWORD_2_TAG, false);

		this.shaman_1 = userData.getBoolean(IProfileConstants.SHAMAN_1_TAG,
				false);
		this.shaman_2 = userData.getBoolean(IProfileConstants.SHAMAN_2_TAG,
				false);
	}

	public void saveDefaultProfile() {
		super.preSaveDefaultProfile();

		userData.putInteger(IProfileConstants.HERO_TAG + "_0", 0);
		for (int i = 1; i < 4; i++) {
			userData.putInteger(IProfileConstants.HERO_TAG + "_" + i, -1);
		}

		userData.putBoolean(IProfileConstants.ARCHER_1_TAG, false);
		userData.putBoolean(IProfileConstants.ARCHER_2_TAG, true);

		userData.putBoolean(IProfileConstants.KNIGHT_1_TAG, false);
		userData.putBoolean(IProfileConstants.KNIGHT_2_TAG, true);

		userData.putBoolean(IProfileConstants.SWORD_1_TAG, false);
		userData.putBoolean(IProfileConstants.SWORD_2_TAG, true);

		userData.putBoolean(IProfileConstants.SHAMAN_1_TAG, false);
		userData.putBoolean(IProfileConstants.SHAMAN_2_TAG, false);
	}

	public boolean isArcher_1() {
		return archer_1;
	}

	public boolean isArcher_2() {
		return archer_2;
	}

	public boolean isSword_1() {
		return sword_1;
	}

	public boolean isSword_2() {
		return sword_2;
	}

	public boolean isKnight_1() {
		return knight_1;
	}

	public boolean isKnight_2() {
		return knight_2;
	}

	public boolean isShaman_1() {
		return shaman_1;
	}

	public boolean isShaman_2() {
		return shaman_2;
	}
}
