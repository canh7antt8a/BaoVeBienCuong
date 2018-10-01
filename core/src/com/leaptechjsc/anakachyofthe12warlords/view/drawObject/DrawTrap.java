package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.MagicObject;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.MagicObject;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class DrawTrap extends DrawHelper {
	private MagicDataManager magicDataManager;
	private ArrayList<MagicObject> trapList;

	public DrawTrap(MagicDataManager magicDataManager,
			ArrayList<MagicObject> trapList, SpriteBatch batch,
			GameLogic gameLogic) {
		super(batch, gameLogic);
		this.magicDataManager = magicDataManager;
		this.trapList = trapList;
	}

	public void draw() {
		TextureRegion temp;
		for (MagicObject magic : trapList) {
			if (magic.isEnd() == false) {
				temp = magicDataManager.getKeyFrame(magic.getDataID(),
						magic.getStateTime());

				batch.draw(
						temp,
						magic.getCastPosition().getX()
								- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1),
						PlayGameScreen.tempValue
								- magic.getCastPosition().getY()
								- ((temp.getRegionHeight() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1));
			}
		}
	}

	public ArrayList<MagicObject> getTrapList() {
		return trapList;
	}

	public void setTrapList(ArrayList<MagicObject> trapList) {
		this.trapList = trapList;
	}
}
