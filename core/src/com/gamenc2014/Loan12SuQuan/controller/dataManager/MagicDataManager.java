package com.gamenc2014.Loan12SuQuan.controller.dataManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.model.magic.IMagicContants;


public class MagicDataManager extends AbstractDataManager {

	private Animation rainOfFire;
	private Animation trap;
	private Animation trapEffect;

	public MagicDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load("data/magic/rain_of_fire.png", Texture.class);
		assetManager.load("data/magic/trap.png", Texture.class);
		assetManager.load("data/magic/trap_effect.png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		Texture rof = assetManager.get("data/magic/rain_of_fire.png",
				Texture.class);
		rof.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture trap = assetManager.get("data/magic/trap.png", Texture.class);
		trap.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture trapEffect = assetManager.get("data/magic/trap_effect.png",
				Texture.class);
		trapEffect.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.rainOfFire = getAnimationFromTexture(rof, 210, 452);
		this.rainOfFire.setPlayMode(Animation.PlayMode.LOOP);

		this.trap = getAnimationFromTexture(trap, 229, 192);
		this.trap.setPlayMode(Animation.PlayMode.LOOP);

		this.trapEffect = getAnimationFromTexture(trapEffect, 281, 262);
		this.trapEffect.setPlayMode(Animation.PlayMode.NORMAL);
	}

	public Animation getMagicAnimation(int id) {
		switch (id) {
		case IMagicContants.TRAP:
			return trap;
		case IMagicContants.RAIN_OF_FIRE:
			return rainOfFire;
		case IMagicContants.TRAP_EFFECT:
			return trapEffect;
		default:
			return trap;
		}
	}

	public TextureRegion getKeyFrame(int id, float stateTime) {
		switch (id) {
		case IMagicContants.TRAP:
			return (TextureRegion)trap.getKeyFrame(stateTime);
		case IMagicContants.RAIN_OF_FIRE:
			return (TextureRegion)rainOfFire.getKeyFrame(stateTime);
		case IMagicContants.TRAP_EFFECT:
			return (TextureRegion)trapEffect.getKeyFrame(stateTime);
		default:
			return (TextureRegion)trap.getKeyFrame(stateTime);
		}
	}

	public static String resolveID(int id) {
		switch (id) {
		case IMagicContants.TRAP:
			return "data/magic/trap.png";
		case IMagicContants.RAIN_OF_FIRE:
			return "data/magic/rain_of_fire";
		case IMagicContants.TRAP_EFFECT:
			return "data/magic/trap_effect";
		default:
			return "data/magic/rain_of_fire";
		}
	}

	public Animation getRainOfFire() {
		return rainOfFire;
	}

	public Animation getTrap() {
		return trap;
	}
}
