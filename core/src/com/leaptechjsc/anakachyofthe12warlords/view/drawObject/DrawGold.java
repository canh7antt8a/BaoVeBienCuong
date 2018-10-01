package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.GoldData;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.GoldData;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

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
