package com.gamenc2014.Loan12SuQuan.controller.data;

import com.badlogic.gdx.audio.Music;

public class GameSound {
	private int ID;
	private Music gameSound;

	public GameSound(int id, Music sound) {
		this.ID = id;
		this.gameSound = sound;
	}

	public GameSound(GameSound sound) {
		this.ID = sound.getID();
		this.gameSound = sound.getSound();
	}

	public int getID() {
		return ID;
	}

	public Music getSound() {
		return gameSound;
	}

	public void play(float volume, boolean isLoop) {
		gameSound.setVolume(volume / 100f);
		if (isLoop == true) {
			gameSound.setLooping(true);
		}
		gameSound.play();
	}

	public void stop() {
		gameSound.stop();
	}

	public boolean isLooping() {
		return gameSound.isLooping();
	}

	public boolean isPlaying() {
		return gameSound.isPlaying();
	}
}
