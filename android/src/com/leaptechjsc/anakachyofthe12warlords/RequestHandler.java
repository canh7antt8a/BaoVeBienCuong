package com.leaptechjsc.anakachyofthe12warlords;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RequestHandler implements IRequestHandler {

	private Handler handler;
	private BaoVeBienCuong baoVeBienCuong;

	public RequestHandler(Handler handler) {
		this.handler = handler;
	}

	public void setGame(BaoVeBienCuong baoVeBienCuong) {
		this.baoVeBienCuong = baoVeBienCuong;
	}

	public Handler getHandler() {
		return this.handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void sendHighScore(int score) {
		Message message = new Message();
		message.arg1 = score;
		message.what = SEND_HIGH_SCORE;

		handler.sendMessage(message);
	}

	@Override
	public void setCoin(int coin) {
		Preferences data = Gdx.app.getPreferences(BaoVeBienCuong.game_name);
		data.putInteger("Coin", coin);
		data.flush();
	}

	@Override
	public int getCoin() {
		return Gdx.app.getPreferences(BaoVeBienCuong.game_name).getInteger(
				"Coin", 5000);
	}

	@Override
	public void setStar(int star) {
		Preferences data = Gdx.app.getPreferences(BaoVeBienCuong.game_name);
		data.putInteger("Star", star);
		data.flush();
	}

	@Override
	public int getStar() {
		return Gdx.app.getPreferences(BaoVeBienCuong.game_name).getInteger(
				"Star", 10);
	}

	@Override
	public void showToast(String text) {
		Message message = new Message();
		Bundle bundle = new Bundle();
		bundle.putString(notice_tag, text);

		message.setData(bundle);
		message.what = SHOW_TOAST;

		handler.sendMessage(message);
	}

	@Override
	public void shareGetCoin() {
		handler.sendEmptyMessage(SHARE_GET_COIN);
	}

	@Override
	public void shareGetStar() {
		handler.sendEmptyMessage(SHARE_GET_STAR);

	}

	@Override
	public void exit() {
		handler.sendEmptyMessage(EXIT);
	}

	@Override
	public void showSmallAd() {
		handler.sendEmptyMessage(ADVIEW_SHOW_SMALL);
	}

	@Override
	public void showLargeAd() {
		handler.sendEmptyMessage(ADVIEW_SHOW_LARGE);
	}

	@Override
	public void hideAd() {
		handler.sendEmptyMessage(ADVIEW_HIDE);
	}

	@Override
	public void showADVideo(){
		handler.sendEmptyMessage(ADVIDEO_SHOW);
	}
}
