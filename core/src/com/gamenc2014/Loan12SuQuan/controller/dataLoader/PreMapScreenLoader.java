package com.gamenc2014.Loan12SuQuan.controller.dataLoader;

import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.TowerDataManager;


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
