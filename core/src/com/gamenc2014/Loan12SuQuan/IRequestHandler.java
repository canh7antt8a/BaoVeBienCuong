package com.gamenc2014.Loan12SuQuan;

public interface IRequestHandler {

	public static final int SHOW_TOAST = 0;

	public static final int SHARE_GET_COIN = 1;
	public static final int SHARE_GET_STAR = 2;

	public static final int EXIT = 3;
	public static final int SEND_HIGH_SCORE = 4;

	public static final int ADVIEW_SHOW_SMALL = 5;
	public static final int ADVIEW_SHOW_LARGE = 6;
	public static final int ADVIEW_HIDE = 7;

	public static final String coin_tag = "game_coin";
	public static final String notice_tag = "notice";

	public void showToast(String text);

	public void sendHighScore(int score);

	public void shareGetCoin();

	public void shareGetStar();

	public void exit();

	public void setCoin(int coin);

	public int getCoin();

	public void setStar(int star);

	public int getStar();

	public void showSmallAd();

	public void showLargeAd();

	public void hideAd();
}
