package com.leaptechjsc.anakachyofthe12warlords;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.SplashScreen;

import java.util.Random;

public class AndroidLauncher extends AndroidApplication implements RewardedVideoAdListener {
	public BaoVeBienCuong baoVeBienCuong;
	public RequestHandler requestHandler;

	private static final String AD_BANNER_ID = "/93656639/96785321";
	private static final String AD_VIDEO_ID = "/93656639/57386192";


	private PublisherAdView adView = null;
	private RewardedVideoAd mRewardedVideoAd = null;

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

					showAdview();
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
					hideAdview();
					break;

				case IRequestHandler.ADVIDEO_SHOW:
					loadRewardedVideoAd();
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

		initADView();

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

	public void initADView(){
		// Create the layout
		RelativeLayout layout = new RelativeLayout(this);
		// Create the libgdx View
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(baoVeBienCuong, config);


		// Create and setup the AdMob view
		adView = new PublisherAdView(this);
		adView.setAdSizes(AdSize.BANNER);
		adView.setAdUnitId(AD_BANNER_ID);

		// Create an ad request.
		PublisherAdRequest adRequestBuilder = new PublisherAdRequest.Builder().addTestDevice("65A1B8F5E230F0557D9EB2871C2492E5").build();
//		layout.addView(gameView);

		// Add the AdMob view
		RelativeLayout.LayoutParams adParams =
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		layout.addView(adView, adParams);
		adView.loadAd(adRequestBuilder);

		setContentView(layout);

//        adView.setBackgroundColor(Color.BLACK);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// Code to be executed when an ad finishes loading.

				Log.d("LIBGDX", "================> onAdLoaded");
//                showAdview();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				// Code to be executed when an ad request fails.

				Log.d("LIBGDX", "================> onAdFailedToLoad");
				hideAdview();
			}

			@Override
			public void onAdOpened() {
				// Code to be executed when an ad opens an overlay that
				// covers the screen.

				Log.d("LIBGDX", "================> onAdOpened");
			}

			@Override
			public void onAdLeftApplication() {
				// Code to be executed when the user has left the app.

				Log.d("LIBGDX", "================> onAdLeftApplication");
			}

			@Override
			public void onAdClosed() {
				// Code to be executed when when the user is about to return
				// to the app after tapping on an ad.

				Log.d("LIBGDX", "================> onAdClosed");
			}
		});


		mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
		mRewardedVideoAd.setRewardedVideoAdListener(this);
	}

	public void hideAdview() {
		if(adView == null) return;
		Log.d("LIBGDX", "================> hideAdview 1");
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (adView.isEnabled())
					adView.setEnabled(false);
				if (adView.getVisibility() == View.VISIBLE)
					adView.setVisibility(View.INVISIBLE);

				Log.d("LIBGDX", "================> hideAdview 2");
			}
		});
	}

	public void showAdview() {
		if(adView == null) return;
		Log.d("LIBGDX", "================> showAdview 1");
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!adView.isEnabled())
					adView.setEnabled(true);
				if (adView.getVisibility() == View.INVISIBLE)
					adView.setVisibility(View.VISIBLE);

				Log.d("LIBGDX", "================> showAdview 2");
			}
		});
	}


	int indexLoadVideo = 0;
	boolean isLoadingVideo = false;

	public void loadRewardedVideoAd() {//onclick
		if(isLoadingVideo) return;
		isLoadingVideo = true;
		try {
			Log.d("LIBGDX", "================> loadRewardedVideoAd");
			this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mRewardedVideoAd.loadAd(AD_VIDEO_ID,
							new PublisherAdRequest.Builder().addTestDevice("65A1B8F5E230F0557D9EB2871C2492E5").build());
					mRewardedVideoAd.show();
				}
			});
		}catch (Exception e){
			Log.d("LIBGDX", "================>Exception loadRewardedVideoAd");
			e.printStackTrace();
		}
	}


	@Override
	public void onRewarded(RewardItem reward) {
//        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
//                reward.getAmount(), Toast.LENGTH_SHORT).show();
		// Reward the user.

//		Log.d("LIBGDX", "================> onRewarded1   " + F.money);
		if(reward != null){
			int cc = new Random().nextInt(3) + 1;

//			Toast.makeText(this, String.format(Language.General.ADD_MONEY_VIEW_AD.getStr(), cc), Toast.LENGTH_SHORT).show();
//			F.money += cc;
		}

//		Log.d("LIBGDX", "================> onRewarded2   " + F.money);
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {
//        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
//                Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int errorCode) {
//        Toast.makeText(this, "Video Failed To Load...", Toast.LENGTH_SHORT).show();
		if(indexLoadVideo >= 3){
			isLoadingVideo = false;
			return;
		}
		loadRewardedVideoAd();
		indexLoadVideo++;
	}

	@Override
	public void onRewardedVideoAdLoaded() {
//        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
		mRewardedVideoAd.show();

		indexLoadVideo = 0;
		isLoadingVideo = false;
	}

	@Override
	public void onRewardedVideoAdOpened() {
//        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoStarted() {
//        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoCompleted() {
//        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();

		indexLoadVideo = 0;
		isLoadingVideo = false;
	}
}
