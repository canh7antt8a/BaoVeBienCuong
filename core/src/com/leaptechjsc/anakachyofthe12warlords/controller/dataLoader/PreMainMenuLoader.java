package com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader;

import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.BulletDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.FontDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MapScreenDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MenuScreenDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.BulletDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.FontDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MagicDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MapScreenDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MenuScreenDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;


public class PreMainMenuLoader extends AbstractLoader {
	private BaoVeBienCuong game;

	public PreMainMenuLoader(BaoVeBienCuong game) {
		super();
		this.game = game;

		game.magicDataManager = new MagicDataManager();
		game.magicDataManager.addToLoad(assetManager);

		game.mapScreenDataManager = new MapScreenDataManager();
		game.mapScreenDataManager.addToLoad(assetManager);

		game.menuScreenDataManager = new MenuScreenDataManager();
		game.menuScreenDataManager.addToLoad(assetManager);

		game.buttonDataManager = new ButtonDataManager();
		game.buttonDataManager.addToLoad(assetManager);

		game.fontDataManager = new FontDataManager();
		game.fontDataManager.addToLoad(assetManager);

		game.bulletDataManager = new BulletDataManager();
		game.bulletDataManager.addToLoad(assetManager);

		game.screenSoundManager = new ScreenSoundDataManager();
		game.screenSoundManager.addToLoad(assetManager);
	}

	@Override
	public void postProcess() {
		game.magicDataManager.getData(assetManager);

		game.mapScreenDataManager.getData(assetManager);

		game.menuScreenDataManager.getData(assetManager);

		game.buttonDataManager.getData(assetManager);

		game.fontDataManager.getData(assetManager);

		game.bulletDataManager.getData(assetManager);

		game.screenSoundManager.getData(assetManager);
	}
}
