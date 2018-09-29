package com.gamenc2014.Loan12SuQuan.controller.dataManager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;

public class MapScreenDataManager extends AbstractDataManager {
	private TextureRegion worldMap;

	private TextureRegion finishedCastle;
	private TextureRegion unfinishedCastle;
	private TextureRegion selectedCastle;

	private TextureRegion levelStar;
	private TextureRegion unfinishedStar;

	private ButtonImage[] difficulty;

	public MapScreenDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load("data/mapSelectionScreen/castle.png", Texture.class);
		assetManager.load("data/mapSelectionScreen/map.jpg", Texture.class);
		assetManager.load("data/mapSelectionScreen/level_star.png",
				Texture.class);
		assetManager.load("data/mapSelectionScreen/difficulty.png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		Texture worldMap = assetManager.get("data/mapSelectionScreen/map.jpg",
				Texture.class);
		worldMap.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture castle_icon = assetManager.get(
				"data/mapSelectionScreen/castle.png", Texture.class);
		castle_icon.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture levelStar = assetManager.get(
				"data/mapSelectionScreen/level_star.png", Texture.class);
		levelStar.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture sword = assetManager.get("data/mapSelectionScreen/difficulty.png",
				Texture.class);
		sword.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.worldMap = new TextureRegion(worldMap);

		TextureRegion[][] temp = (new TextureRegion(castle_icon)).split(121,
				110);

		this.finishedCastle = temp[0][0];
		this.selectedCastle = temp[0][1];
		this.unfinishedCastle = temp[0][2];

		temp = (new TextureRegion(levelStar)).split(32, 32);
		this.levelStar = new TextureRegion(temp[0][0]);
		this.unfinishedStar = new TextureRegion(temp[0][1]);

		temp = (new TextureRegion(sword)).split(147, 184);
		this.difficulty = new ButtonImage[4];
		for (int i = 0; i < this.difficulty.length; i++) {
			this.difficulty[i] = new ButtonImage(i, temp[0][i], temp[1][i]);
		}
	}

	public TextureRegion getWorldMap() {
		return worldMap;
	}

	public TextureRegion getFinishedCastle() {
		return finishedCastle;
	}

	public TextureRegion getUnfinishedCastle() {
		return unfinishedCastle;
	}

	public TextureRegion getSelectedCastle() {
		return selectedCastle;
	}

	public TextureRegion getLevelStar() {
		return levelStar;
	}

	public TextureRegion getUnfinishedStar() {
		return unfinishedStar;
	}

	public ButtonImage getSword(int id) {
		if (id > -1 && id < difficulty.length) {
			return difficulty[id];
		} else {
			return null;
		}
	}
}
