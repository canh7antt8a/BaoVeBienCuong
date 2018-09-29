package com.gamenc2014.loan12suquan;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.IRequestHandler;

public class AndroidLauncher extends AndroidApplication{
	public BaoVeBienCuong baoVeBienCuong;
	public RequestHandler requestHandler;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case IRequestHandler.SEND_HIGH_SCORE:
//					score = msg.arg1;
//					openHighScoreDialog();
					break;

				case IRequestHandler.SHARE_GET_COIN:
//					openShareDialogGetCoin();
					break;

				case IRequestHandler.SHARE_GET_STAR:
//					openShareDialogGetStar();
					break;

				case IRequestHandler.EXIT:
					openExitDialog();
					break;

				case IRequestHandler.ADVIEW_SHOW_SMALL:
//					if (adView != null) {
//						layout.addView(adView);
//						adView.loadAd(new AdRequest());
//						adView.setVisibility(View.VISIBLE);
//					}
					break;

				case IRequestHandler.ADVIEW_SHOW_LARGE:
//					if (interstitial != null) {
//						// Create ad request
//						AdRequest adRequest = new AdRequest();
//
//						// Begin loading your interstitial
//						interstitial.loadAd(adRequest);
//						interstitial.setAdListener(MainActivity.this);
//
//					}

					break;

				case IRequestHandler.ADVIEW_HIDE:
//					if (adView != null) {
//						adView.setVisibility(View.GONE);
//						layout.removeView(adView);
//					}
					break;

				case IRequestHandler.SHOW_TOAST:
					showNotice(msg.getData().getString(IRequestHandler.notice_tag));
					break;
			}
		}
	};
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		requestHandler = new RequestHandler(handler);

		baoVeBienCuong = new BaoVeBienCuong(requestHandler);
		requestHandler.setGame(baoVeBienCuong);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(baoVeBienCuong, config);
	}
	public void openExitDialog() {
		AlertDialog.Builder alertboxDowload = new AlertDialog.Builder(this);
		alertboxDowload.setTitle(R.string.confirm_dialog_title);
		alertboxDowload.setMessage(R.string.exit_app_dialog_message);
		alertboxDowload.setCancelable(false);
		alertboxDowload.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Gdx.app.exit();
					}
				}).setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		alertboxDowload.show();
	}

	public void showNotice(String notice) {
		Toast.makeText(this, notice, Toast.LENGTH_SHORT).show();
	}
}
