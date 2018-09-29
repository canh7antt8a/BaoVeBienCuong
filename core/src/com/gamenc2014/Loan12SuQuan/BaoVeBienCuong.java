package com.gamenc2014.Loan12SuQuan;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.BulletDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.ButtonDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.EnemyDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.FontDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.MagicDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.MapScreenDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.MenuScreenDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.ProfileScreenDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.TowerDataManager;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.Level_Profile;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.Settings;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.Skill_Profile;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.UnlockArmy_Profile;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.ScreenSoundDataManager;
import com.gamenc2014.Loan12SuQuan.view.loadingscreen.SplashScreen;

public class BaoVeBienCuong extends Game {

	public static String game_name = "vn_sunnet_game_Loan12SuQuan";
	protected static int speed;

	public ButtonDataManager buttonDataManager;
	public MagicDataManager magicDataManager;
	public MapScreenDataManager mapScreenDataManager;
	public MenuScreenDataManager menuScreenDataManager;
	public ProfileScreenDataManager profileScreenDataManager;
	public FontDataManager fontDataManager;
	public EnemyDataManager enemyDataManager;

	public TowerDataManager towerDataManager;
	public BulletDataManager bulletDataManager;

	public ScreenSoundDataManager screenSoundManager;

	public boolean isMapScreenLoaded;
	public boolean isProfileScreenLoaded;
	public boolean isEnemyLoaded;

	public Texture loading;
	public Texture loadingFull;
	public BitmapFont font;
//	public Skin skin;

	public String noticeText;

	public IRequestHandler requestHandler;

	public BaoVeBienCuong(IRequestHandler requestHandler) {
		speed = 1;

		this.isMapScreenLoaded = false;
		this.isProfileScreenLoaded = false;
		this.isEnemyLoaded = false;
		this.noticeText = "";

		this.requestHandler = requestHandler;
	}

	public void checkFirstTimeRun() {
		Preferences data = Gdx.app.getPreferences(game_name);
		boolean firstTime = data.getBoolean("First_Time", true);
		if (firstTime == true) {
			Skill_Profile skill_Profile = new Skill_Profile(this);
			skill_Profile.saveDefaultProfile();
			skill_Profile.save();

			UnlockArmy_Profile unlockArmy_Profile = new UnlockArmy_Profile(this);
			unlockArmy_Profile.saveDefaultProfile();
			unlockArmy_Profile.save();

			Level_Profile levelProfile = new Level_Profile(this);
			levelProfile.saveDefaultProfile();
			levelProfile.save();

			Settings settings = new Settings();
			settings.saveDefaultSettings();

			data.putBoolean("First_Time", false);
			data.flush();
		}
	}

	@Override
	public void create() {
		this.loading = new Texture(
				Gdx.files.internal("data/loading/loadingframe.png"));
		this.loading.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.loadingFull = new Texture(
				Gdx.files.internal("data/loading/fullloading.png"));
		this.loadingFull.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		this.skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.setLogLevel(Application.LOG_ERROR);
		Gdx.app.setLogLevel(Application.LOG_INFO);
		setScreen(new SplashScreen(null, this));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
		if (font != null) {
			font.dispose();
		}
//		skin.dispose();
		screenSoundManager.dispose();
	}

	public static int getSpeed() {
		return speed;
	}

	public static void resetSpeed() {
		speed = 1;
	}

	public static void changeSpeed() {
		speed = (speed++) % 3 + 1;
	}

	public boolean isLoaded() {
		return isMapScreenLoaded;
	}

	public void setLoaded(boolean loaded) {
		isMapScreenLoaded = loaded;
	}

	public boolean isProfileScreenLoaded() {
		return isProfileScreenLoaded;
	}

	public void setProfileScreenLoaded(boolean isProfileScreenLoaded) {
		this.isProfileScreenLoaded = isProfileScreenLoaded;
	}

	public Texture getLoading() {
		return loading;
	}

	public Texture getLoadingFull() {
		return loadingFull;
	}

	// @Override
	// public void CloseViewScore() {
	// setScreen(new MapSelectionScreen(null, this));
	// }
	//
	// @Override
	// public void paymentSuccess() {
	// if (((AbstractScreen) this.getScreen()).getScreenID() ==
	// AbstractScreen.PROFILE_SCREEN) {
	// ((ProfileScreen) this.getScreen()).updateText();
	// }
	// }
	//
	// @Override
	// public void sendHighScoreSuccess() {
	// if (((AbstractScreen) this.getScreen()).getScreenID() ==
	// AbstractScreen.PROFILE_SCREEN) {
	// ((ProfileScreen) this.getScreen()).updateText();
	// }
	// }
	//
	// public UrlFetch getFetch() {
	// return fetch;
	// }
	//
	// @Override
	// public void closeStandardDialog() {
	// setScreen(new MainMenuScreen(null, this));
	// }
}
