package com.gamenc2014.Loan12SuQuan.controller.dataManager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.Image;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;

public class BulletDataManager extends AbstractDataManager {

	private Image[] bulletList;

	public BulletDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load(resolve(), Texture.class);
	}

	@Override
	public void getData(AssetManager assetManager) {
		Texture bullet = assetManager.get(resolve(), Texture.class);
		bullet.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.bulletList = new Image[6];

		TextureRegion[][] temp = (new TextureRegion(bullet)).split(
				ICommonConstants.DEFAULT_TILE_SIZE,
				ICommonConstants.DEFAULT_TILE_SIZE);

		for (int i = 0; i < temp[0].length; i++) {
			this.bulletList[i] = new Image(i, temp[0][i]);
		}
	}

	public TextureRegion getResources(int id) {
		if (id > -1 && id < bulletList.length) {
			return bulletList[id].getImage();
		} else {
			return null;
		}
	}

	public String resolve() {
		return "data/bullet/bullet.png";
	}
}
