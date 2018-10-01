package com.leaptechjsc.anakachyofthe12warlords.controller.soundManager;

import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Settings;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.AbstractDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Settings;


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
