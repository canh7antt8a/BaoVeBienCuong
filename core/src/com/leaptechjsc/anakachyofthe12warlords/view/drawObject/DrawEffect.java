package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.EffectData;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.MagicData;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.EffectData;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.MagicData;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class DrawEffect extends DrawHelper {
	private MagicDataManager magicDataManager;

	public DrawEffect(MagicDataManager magicDataManager, SpriteBatch batch,
			GameLogic gameLogic) {
		super(batch, gameLogic);
		this.magicDataManager = magicDataManager;
	}

	public void draw() {
		TextureRegion temp;
		for (EffectData effectData : MagicData.effectList) {
			temp = magicDataManager.getKeyFrame(effectData.getDataID(),
					effectData.getStateTime());
			batch.draw(
					temp,
					effectData.getX()
							- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1),
					PlayGameScreen.tempValue - effectData.getY());
		}
	}
}
