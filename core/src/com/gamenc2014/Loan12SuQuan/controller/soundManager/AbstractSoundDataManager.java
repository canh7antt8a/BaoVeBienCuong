package com.gamenc2014.Loan12SuQuan.controller.soundManager;

import com.gamenc2014.Loan12SuQuan.controller.dataManager.AbstractDataManager;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.Settings;


public abstract class AbstractSoundDataManager extends AbstractDataManager {
	protected int soundVolume;
	protected int musicVolume;

	public AbstractSoundDataManager() {
		Settings settings = new Settings();

		this.soundVolume = settings.getSoundSetting();
		this.musicVolume = settings.getMusicSetting();
	}

	public abstract void play(int soundID, boolean isLooping);

	public abstract void stop(int soundID);

	public abstract void stopAll();

	public abstract void replay();

	public void update() {
		Settings settings = new Settings();

		this.soundVolume = settings.getSoundSetting();
		this.musicVolume = settings.getMusicSetting();

		replay();
	}
}
