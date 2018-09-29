package com.gamenc2014.Loan12SuQuan.view.drawObject;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.GameLogic;

public abstract class DrawHelper {
	protected SpriteBatch batch;
	protected GameLogic gameLogic;

	public DrawHelper(SpriteBatch batch, GameLogic gameLogic) {
		this.batch = batch;
		this.gameLogic = gameLogic;
	}
}
