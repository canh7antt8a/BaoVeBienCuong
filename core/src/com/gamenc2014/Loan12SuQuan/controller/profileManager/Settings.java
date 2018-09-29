package com.gamenc2014.Loan12SuQuan.controller.profileManager;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;

public class Settings {
	private static final String sound_tag = "SOUND";
	private static final String music_tag = "MUSIC";
	//
	private Preferences userSettings;

	public Settings() {
		this.loadSettings();
	}

	public void loadSettings() {
		this.userSettings = Gdx.app.getPreferences(BaoVeBienCuong.game_name);
	}

	public void saveSettings(int soundVolume, int musicVolume) {
		userSettings.putInteger(sound_tag, soundVolume);
		userSettings.putInteger(music_tag, musicVolume);

		userSettings.flush();
	}

	public void saveSoundSettings(int soundVolume) {
		userSettings.putInteger(sound_tag, soundVolume);
		userSettings.flush();
	}

	public void saveMusicSettings(int musicVolume) {
		userSettings.putInteger(music_tag, musicVolume);
		userSettings.flush();
	}

	public void saveDefaultSettings() {
		userSettings.putInteger(sound_tag, 50);
		userSettings.putInteger(music_tag, 50);

		userSettings.flush();
	}

	public int getSoundSetting() {
		return userSettings.getInteger(sound_tag, 50);
	}

	public int getMusicSetting() {
		return userSettings.getInteger(music_tag, 50);
	}
}
