package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;

public abstract class DrawHelper {
	protected SpriteBatch batch;
	protected GameLogic gameLogic;

	public DrawHelper(SpriteBatch batch, GameLogic gameLogic) {
		this.batch = batch;
		this.gameLogic = gameLogic;
	}
}
