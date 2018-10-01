package com.leaptechjsc.anakachyofthe12warlords.controller.soundManager;

import java.util.ArrayList;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.GameSound;

public class SoundDataManager extends AbstractSoundDataManager {
	public static final int GOLD = 0;
	public static final int ROF = 1;
	public static final int TRAP = 2;
	public static final int TRAP_EFFECT = 3;
	public static final int WIN = 4;
	public static final int LOSE = 5;
	//
	public static final int ARCHER = 6;
	public static final int ONE_HANDED = 7;
	public static final int TWO_HANDED = 8;
	public static final int SHAMAN_0 = 9;
	public static final int SHAMAN_1 = 10;
	public static final int SHAMAN_2 = 11;
	//
	private ArrayList<GameSound> soundList;
	private GameSound backgroundSound;

	private int mapID;

	public SoundDataManager(int mapID) {
		super();
		this.mapID = mapID;
	}

	public void addToLoad(AssetManager assetManager) {
		assetManager.load(resolveID(mapID), Music.class);

		for (int i = GOLD; i <= SHAMAN_2; i++) {
			assetManager.load(getSoundPath(i), Music.class);
		}
	}

	public void getData(AssetManager assetManager) {
		this.soundList = new ArrayList<GameSound>();

		for (int i = GOLD; i <= SHAMAN_2; i++) {
			this.soundList.add(new GameSound(i, assetManager.get(
					getSoundPath(i), Music.class)));
		}

		this.backgroundSound = new GameSound(mapID, assetManager.get(
				resolveID(mapID), Music.class));
	}

	public Music getSound(int id) {
		for (GameSound sound : soundList) {
			if (sound.getID() == id) {
				return sound.getSound();
			}
		}
		return null;
	}

	public void playScenarioSound() {
		backgroundSound.play(musicVolume, true);
	}

	@Override
	public void play(int soundID, boolean isLoop) {
		if (soundID > -1 && soundID < soundList.size()) {
			soundList.get(soundID).play(soundVolume, isLoop);
		}
	}

	@Override
	public void stop(int soundID) {
		if (soundID > -1 && soundID < soundList.size()) {
			soundList.get(soundID).stop();
		}
	}

	@Override
	public void stopAll() {
		for (GameSound sound : soundList) {
			sound.stop();
		}
		backgroundSound.stop();
	}

	public String resolveID(int id) {
		return ("data/sound/scenario/sound_" + (id % 5) + ".ogg");
	}

	public String getSoundPath(int id) {
		switch (id) {
		case GOLD:
			return "data/sound/Gold.ogg";

		case ROF:
			return "data/sound/magic/RainOfFire.ogg";

		case TRAP:
			return "data/sound/magic/Trap.ogg";

		case TRAP_EFFECT:
			return "data/sound/magic/TrapEffect.ogg";

		case WIN:
			return "data/sound/gameOver/Win.ogg";

		case LOSE:
			return "data/sound/gameOver/Lose.ogg";

		case ARCHER:
			return "data/sound/tower/archer.ogg";

		case ONE_HANDED:
			return "data/sound/tower/one_handed.ogg";

		case TWO_HANDED:
			return "data/sound/tower/two_handed.ogg";

		case SHAMAN_0:
			return "data/sound/tower/shaman_0.ogg";

		case SHAMAN_1:
			return "data/sound/tower/shaman_1.ogg";

		case SHAMAN_2:
			return "data/sound/tower/shaman_2.ogg";

		default:
			return null;
		}
	}

	@Override
	public void replay() {
		stopAll();
		backgroundSound.play(musicVolume, true);
	}

	public void dispose() {
		for (GameSound sound : soundList) {
			sound.getSound().dispose();
		}
		backgroundSound.getSound().dispose();
	}
}
