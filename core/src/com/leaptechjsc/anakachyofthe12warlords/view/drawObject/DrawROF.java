package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.MagicObject;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.MagicObject;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class DrawROF extends DrawHelper {
	private MagicDataManager magicDataManager;
	private ArrayList<MagicObject> rofList;

	public DrawROF(MagicDataManager magicDataManager,
			ArrayList<MagicObject> rofList, SpriteBatch batch,
			GameLogic gameLogic) {
		super(batch, gameLogic);
		this.magicDataManager = magicDataManager;
		this.rofList = rofList;
	}

	public void draw() {
		Coordinate tempPos;
		int x, y;
		TextureRegion temp;

		for (MagicObject magic : rofList) {
			if (magic.isEnd() == false) {
				tempPos = magic.getCastPosition();
				x = tempPos.getX();
				y = PlayGameScreen.tempValue - tempPos.getY();

				temp = magicDataManager.getKeyFrame(magic.getDataID(),
						magic.getStateTime());

				batch.draw(
						temp,
						x
								- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1),
						y);

				temp = magicDataManager.getKeyFrame(magic.getDataID(),
						magic.getStateTime() + 0.4f);

				batch.draw(
						temp,
						x
								- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1)
								+ 50, y);

				temp = magicDataManager.getKeyFrame(magic.getDataID(),
						magic.getStateTime() + 0.2f);

				batch.draw(
						temp,
						x
								- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1)
								+ 25, y - 25);
			}
		}
	}

	public ArrayList<MagicObject> getRofList() {
		return rofList;
	}

	public void setRofList(ArrayList<MagicObject> rofList) {
		this.rofList = rofList;
	}
}
