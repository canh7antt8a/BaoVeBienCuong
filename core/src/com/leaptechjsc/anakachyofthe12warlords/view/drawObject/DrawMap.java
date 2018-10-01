package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;

public class DrawMap extends DrawHelper {
	private Texture[] map;
	private int nCol;
	private int nRow;

	public DrawMap(Texture[] map, int nCol, int nRow, SpriteBatch batch,
			GameLogic gameLogic) {
		super(batch, gameLogic);
		this.map = map;
		this.nCol = nCol;
		this.nRow = nRow;
	}

	public void draw() {
		for (int i = 0; i < nCol; i++) {
			for (int j = 0; j < nRow; j++) {
				batch.draw(map[j * nCol + i], i * map[j * nCol + i].getWidth(),
						(nRow - 1 - j) * map[j * nCol + i].getHeight());
			}
		}
	}
}
