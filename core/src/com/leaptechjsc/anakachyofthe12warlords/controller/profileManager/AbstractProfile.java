package com.leaptechjsc.anakachyofthe12warlords.controller.profileManager;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;

public class AbstractProfile {

	protected Preferences userData;

	protected int money;
	protected int star;
	protected int score;

	protected BaoVeBienCuong baoVeBienCuong;

	public AbstractProfile(BaoVeBienCuong baoVeBienCuong) {
		this.baoVeBienCuong = baoVeBienCuong;
		load();
	}

	public void load() {
		userData = Gdx.app.getPreferences(BaoVeBienCuong.game_name);
		this.money = getCoins();
		this.star = getStars();
		this.score = userData.getInteger(IProfileConstants.SCORE_TAG, 0);
	}

	public void preSaveDefaultProfile() {
		userData.putInteger(IProfileConstants.SCORE_TAG, 0);

		baoVeBienCuong.requestHandler.setCoin(3000);
		baoVeBienCuong.requestHandler.setStar(0);
	}

	public void save() {
		userData.flush();
		load();
	}

	public void putInteger(String tag, int value) {
		userData.putInteger(tag, value);
	}

	public void putBoolean(String tag, boolean value) {
		userData.putBoolean(tag, value);
	}

	public void putString(String tag, String value) {
		userData.putString(tag, value);
	}

	public void setCoins(int coin) {
		baoVeBienCuong.requestHandler.setCoin(coin);
	}

	public int getCoins() {
		return baoVeBienCuong.requestHandler.getCoin();
	}

	public void setStars(int star) {
		baoVeBienCuong.requestHandler.setStar(star);
	}

	public int getStars() {
		return baoVeBienCuong.requestHandler.getStar();
	}

	public Preferences getUserData() {
		return userData;
	}

	public int getMoney() {
		return money;
	}

	public int getStar() {
		return star;
	}

	public int getScore() {
		return score;
	}
}
