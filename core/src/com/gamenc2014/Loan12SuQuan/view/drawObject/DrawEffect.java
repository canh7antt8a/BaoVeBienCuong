package com.gamenc2014.Loan12SuQuan.view.drawObject;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.EffectData;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.MagicDataManager;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.GameLogic;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.model.magic.MagicData;
import com.gamenc2014.Loan12SuQuan.view.screen.PlayGameScreen;

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
