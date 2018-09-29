package com.gamenc2014.Loan12SuQuan.controller.dataManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AbstractDataManager {
	private static float frameDuration = 0.1f;

	public static Animation getAnimationFromTexture(Texture temp, int width,
			int height, float duration) {
		TextureRegion[][] tempFrame;
		TextureRegion[] tempRegion;
		int index = 0, m, n;

		tempFrame = TextureRegion.split(temp, width, height);
		tempRegion = new TextureRegion[tempFrame[0].length * tempFrame.length];

		for (m = 0; m < tempFrame.length; m++) {
			for (n = 0; n < tempFrame[0].length; n++) {
				tempRegion[index++] = tempFrame[m][n];
			}
		}

		return new Animation(duration, tempRegion);
	}

	public static Animation getAnimationFromTexture(Texture temp, int width,
			int height) {
		TextureRegion[][] tempFrame;
		TextureRegion[] tempRegion;
		int index = 0, m, n;

		tempFrame = TextureRegion.split(temp, width, height);
		tempRegion = new TextureRegion[tempFrame[0].length * tempFrame.length];

		for (m = 0; m < tempFrame.length; m++) {
			for (n = 0; n < tempFrame[0].length; n++) {
				tempRegion[index++] = tempFrame[m][n];
			}
		}

		return new Animation(frameDuration, tempRegion);
	}

	public static float getFrameDuration() {
		return frameDuration;
	}

	public static void setFrameDuration(float frameDuration) {
		AbstractDataManager.frameDuration = frameDuration;
	}

	public abstract void addToLoad(AssetManager assetManager);

	public abstract void getData(AssetManager assetManager);
}
