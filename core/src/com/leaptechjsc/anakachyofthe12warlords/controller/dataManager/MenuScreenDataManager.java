package com.leaptechjsc.anakachyofthe12warlords.controller.dataManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreenDataManager extends AbstractDataManager {
	private TextureRegion menuBackground;
	private TextureRegion infoFrame;
	private TextureRegion knob;
	private TextureRegion bkg_slider;

	public MenuScreenDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load("data/menuScreen/menu.jpg", Texture.class);
		assetManager.load("data/menuScreen/infoFrame.png", Texture.class);
		assetManager.load("data/menuScreen/knob.png", Texture.class);
		assetManager.load("data/menuScreen/infoFrame.png", Texture.class);
		assetManager.load("data/menuScreen/slider_bar.png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		Texture menuBackground = assetManager.get("data/menuScreen/menu.jpg",
				Texture.class);
		menuBackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture infoFrame = assetManager.get("data/menuScreen/infoFrame.png",
				Texture.class);
		infoFrame.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture knob = assetManager.get("data/menuScreen/knob.png",
				Texture.class);
		knob.setFilter(TextureFilter.Linear, TextureFilter.Linear);


		Texture bkg_slider = assetManager.get("data/menuScreen/slider_bar.png",
				Texture.class);
		bkg_slider.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.menuBackground = new TextureRegion(menuBackground);
		this.infoFrame = new TextureRegion(infoFrame);
		this.knob = new TextureRegion(knob);
		this.bkg_slider = new TextureRegion(bkg_slider);
	}

	public TextureRegion getMenuBackground() {
		return menuBackground;
	}

	public TextureRegion getInfoFrame() {
		return infoFrame;
	}

	public TextureRegion getKnob() {
		return knob;
	}

	public TextureRegion getBkg_slider() {
		return bkg_slider;
	}
}
