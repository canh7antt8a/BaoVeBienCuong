package com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader;

import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.TowerDataManager;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.TowerDataManager;


public class PreMapScreenLoader extends AbstractLoader {
	private BaoVeBienCuong game;

	public PreMapScreenLoader(BaoVeBienCuong game) {
		super();
		this.game = game;

		game.towerDataManager = new TowerDataManager();
		game.towerDataManager.addToLoad(assetManager);
	}

	@Override
	public void postProcess() {
		game.towerDataManager.getData(assetManager);
		game.setLoaded(true);
	}
}
