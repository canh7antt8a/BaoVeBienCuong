package com.leaptechjsc.anakachyofthe12warlords.controller.dataManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FontDataManager extends AbstractDataManager {
	public static final int MAP_FONT = 0;
	public static final int PLAY_NUMBER_FONT = 1;
	public static final int BASIC_FONT = 2;
	public static final int BASIC_NUMBER_FONT = 3;

	private BitmapFont mapFont;
	private BitmapFont playNumberFont;
	private BitmapFont basicFont;
	private BitmapFont basicNumberFont;

	public FontDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load(FontDataManager.resolveID(FontDataManager.BASIC_FONT)
				+ ".png", Texture.class);
		assetManager.load(FontDataManager.resolveID(FontDataManager.MAP_FONT)
				+ ".png", Texture.class);
		assetManager.load(
				FontDataManager.resolveID(FontDataManager.BASIC_NUMBER_FONT)
						+ ".png", Texture.class);
		assetManager.load(
				FontDataManager.resolveID(FontDataManager.PLAY_NUMBER_FONT)
						+ ".png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		Texture mapFont = assetManager.get(
				FontDataManager.resolveID(FontDataManager.MAP_FONT) + ".png",
				Texture.class);
		mapFont.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture playNumberFont = assetManager.get(
				FontDataManager.resolveID(FontDataManager.PLAY_NUMBER_FONT)
						+ ".png", Texture.class);
		playNumberFont.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture basicFont = assetManager.get(
				FontDataManager.resolveID(FontDataManager.BASIC_FONT) + ".png",
				Texture.class);
		basicFont.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture basicNumberFont = assetManager.get(
				FontDataManager.resolveID(FontDataManager.BASIC_NUMBER_FONT)
						+ ".png", Texture.class);
		basicNumberFont.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.mapFont = new BitmapFont(Gdx.files.internal(FontDataManager
				.resolveID(FontDataManager.MAP_FONT) + ".fnt"),
				new TextureRegion(mapFont), false);

		this.playNumberFont = new BitmapFont(Gdx.files.internal(FontDataManager
				.resolveID(FontDataManager.PLAY_NUMBER_FONT) + ".fnt"),
				new TextureRegion(playNumberFont), false);

		this.basicFont = new BitmapFont(Gdx.files.internal(FontDataManager
				.resolveID(FontDataManager.BASIC_FONT) + ".fnt"),
				new TextureRegion(basicFont), false);

		this.basicNumberFont = new BitmapFont(
				Gdx.files.internal(FontDataManager
						.resolveID(FontDataManager.BASIC_NUMBER_FONT) + ".fnt"),
				new TextureRegion(basicNumberFont), false);
		setScale();

	}

	public static String resolveID(int dataID) {
		switch (dataID) {
		case MAP_FONT:
			return ("data/font/mapFont");
		case PLAY_NUMBER_FONT:
			return ("data/font/playNumberFont");
		case BASIC_FONT:
			return ("data/font/basicFont");
		case BASIC_NUMBER_FONT:
			return ("data/font/basicNumber");
		default:
			return null;
		}
	}

	public BitmapFont getMapFont() {
		return mapFont;
	}

	public BitmapFont getPlayNumberFont() {
		return playNumberFont;
	}

	public BitmapFont getBasicFont() {
		return basicFont;
	}

	public BitmapFont getBasicNumberFont() {
		return basicNumberFont;
	}

	public void setScale() {
		float scale_x = Gdx.graphics.getWidth() / 1280f;
		float scale_y = Gdx.graphics.getHeight() / 720f;

		this.basicFont.getData().setScale(scale_x, scale_y);
		this.mapFont.getData().setScale(scale_x, scale_y);
		this.basicNumberFont.getData().setScale(scale_x, scale_y);
		this.playNumberFont.getData().setScale(scale_x, scale_y);
	}

	public float getDefaultScaleX() {
		return Gdx.graphics.getWidth() / 1280f;
	}

	public float getDefaultScaleY() {
		return Gdx.graphics.getHeight() / 720f;
	}

	public void setDefaultScale(int fontID) {
		float scale_x = Gdx.graphics.getWidth() / 1280f;
		float scale_y = Gdx.graphics.getHeight() / 720f;

		switch (fontID) {
		case MAP_FONT:
			this.mapFont.getData().setScale(scale_x, scale_y);
			break;
		case BASIC_FONT:
			this.basicFont.getData().setScale(scale_x, scale_y);
			break;
		case BASIC_NUMBER_FONT:
			this.basicNumberFont.getData().setScale(scale_x, scale_y);
			break;
		case PLAY_NUMBER_FONT:
			this.playNumberFont.getData().setScale(scale_x, scale_y);
			break;
		}
	}
}
