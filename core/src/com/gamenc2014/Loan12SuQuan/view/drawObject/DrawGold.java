package com.gamenc2014.Loan12SuQuan.view.drawObject;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.GoldData;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.GameLogic;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.view.screen.PlayGameScreen;

public class DrawGold extends DrawHelper {
	private ArrayList<GoldData> goldList;
	private Animation goldAnimation;

	private int tempSizeX;
	private int tempSizeY;

	public DrawGold(ArrayList<GoldData> goldList, Animation goldAnimation,
			SpriteBatch batch, GameLogic gameLogic) {
		super(batch, gameLogic);
		this.goldList = goldList;
		this.goldAnimation = goldAnimation;

		this.tempSizeX = ((64 - ICommonConstants.DEFAULT_TILE_SIZE) >> 1);
		this.tempSizeY = PlayGameScreen.tempValue - tempSizeX;
	}

	public void draw() {
		TextureRegion temp;
		for (GoldData gold : goldList) {
			temp = (TextureRegion)goldAnimation.getKeyFrame(gold.getStateTime());
			batch.draw(temp, gold.getX() - tempSizeX, tempSizeY - gold.getY());
		}
	}
}
