package com.leaptechjsc.anakachyofthe12warlords.controller.soundManager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.GameSound;

public class ScreenSoundDataManager extends AbstractSoundDataManager {

	public static final int MAP_SELECT = 0;
	public static final int PROFILE = 1;
	public static final int CLICK = 2;
	public static final int UPGRADE = 3;

	private GameSound mapSelectionSound;
	private GameSound profileSound;

	private GameSound clickSound;
	private GameSound upgradeSound;

	private int currentSoundID;

	public ScreenSoundDataManager() {
		super();
		this.currentSoundID = MAP_SELECT;
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load("data/sound/screen/MapSelect.ogg", Music.class);
		assetManager.load("data/sound/screen/Profile.ogg", Music.class);

		assetManager.load("data/sound/Click.ogg", Music.class);
		assetManager.load("data/sound/Upgrade.ogg", Music.class);
	}

	@Override
	public void getData(AssetManager assetManager) {

		mapSelectionSound = new GameSound(MAP_SELECT, assetManager.get(
				"data/sound/screen/MapSelect.ogg", Music.class));

		profileSound = new GameSound(PROFILE, assetManager.get(
				"data/sound/screen/Profile.ogg", Music.class));

		clickSound = new GameSound(CLICK, assetManager.get(
				"data/sound/Click.ogg", Music.class));

		upgradeSound = new GameSound(UPGRADE, assetManager.get(
				"data/sound/Upgrade.ogg", Music.class));
	}

	@Override
	public void play(int soundID, boolean isLooping) {
		switch (soundID) {
		case MAP_SELECT:
			this.currentSoundID = soundID;
			mapSelectionSound.play(musicVolume, isLooping);
			break;
		case PROFILE:
			this.currentSoundID = soundID;
			profileSound.play(musicVolume, isLooping);
			break;
		case CLICK:
			clickSound.play(soundVolume, isLooping);
			break;
		case UPGRADE:
			upgradeSound.play(soundVolume, isLooping);
			break;
		default:
			break;
		}
	}

	@Override
	public void stop(int soundID) {
		switch (soundID) {
		case MAP_SELECT:
			mapSelectionSound.stop();
			break;
		case PROFILE:
			profileSound.stop();
			break;
		case CLICK:
			clickSound.stop();
			break;
		case UPGRADE:
			upgradeSound.stop();
			break;
		}
	}

	@Override
	public void stopAll() {
		mapSelectionSound.stop();
		profileSound.stop();
		clickSound.stop();
		upgradeSound.stop();
	}

	@Override
	public void replay() {
		stopAll();
		play(currentSoundID, true);
	}

	public boolean isSoundPlaying(int id) {
		if (currentSoundID == id) {
			return true;
		} else {
			return false;
		}
	}

	public void resetSoundID() {
		this.currentSoundID = -1;
	}

	public void dispose() {
		mapSelectionSound.getSound().dispose();
		profileSound.getSound().dispose();
		clickSound.getSound().dispose();
		upgradeSound.getSound().dispose();
	}
}
