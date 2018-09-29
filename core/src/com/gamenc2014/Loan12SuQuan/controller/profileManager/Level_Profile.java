package com.gamenc2014.Loan12SuQuan.controller.profileManager;

import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;

public class Level_Profile extends AbstractProfile {

	private int[] level;

	public Level_Profile(BaoVeBienCuong baoVeBienCuong) {
		super(baoVeBienCuong);
		load();
	}

	public void load() {
		super.load();
		level = new int[12];
		level[0] = userData.getInteger(IProfileConstants.LEVEL_TAG + 0, 0);
		for (int i = 1; i < 12; i++) {
			level[i] = userData.getInteger(IProfileConstants.LEVEL_TAG + i, -1);
		}
	}

	public void saveDefaultProfile() {
		super.preSaveDefaultProfile();
		userData.putInteger(IProfileConstants.LEVEL_TAG + 0, 0);
		for (int i = 1; i < 12; i++) {
			userData.putInteger(IProfileConstants.LEVEL_TAG + i, -1);
		}
	}

	public int getLevel(int id) {
		if (id > -1 && id < 12) {
			return this.level[id];
		} else {
			return -1;
		}
	}

	public int[] getLevel() {
		return level;
	}
}
