package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.BulletDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.bullet.Bullet;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.BulletDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.bullet.Bullet;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class DrawBullet extends DrawHelper {
	private ArrayList<Bullet> bulletList;
	private BulletDataManager bulletDataManager;

	public DrawBullet(BulletDataManager bulletDataManager,
			ArrayList<Bullet> bulletList, SpriteBatch batch, GameLogic gameLogic) {
		super(batch, gameLogic);
		this.bulletDataManager = bulletDataManager;
		this.bulletList = bulletList;
	}

	public void draw() {
		int x, y;
		TextureRegion temp;
		for (Bullet bullet : bulletList) {
			x = bullet.getX();
			y = PlayGameScreen.tempValue - bullet.getY();
			temp = bulletDataManager.getResources(bullet.getDataID());

			batch.draw(temp, x, y, temp.getRegionWidth() / 2,
					temp.getRegionWidth() / 2, temp.getRegionWidth(),
					temp.getRegionHeight(), 1, 1, bullet.getRotation());
		}
	}
}
