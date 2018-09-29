package com.gamenc2014.Loan12SuQuan.controller.dataLoader;

import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.ProfileScreenDataManager;


public class PreProfileScreenLoader extends AbstractLoader {
	private BaoVeBienCuong game;

	public PreProfileScreenLoader(BaoVeBienCuong game) {
		super();
		this.game = game;
		game.profileScreenDataManager = new ProfileScreenDataManager();
		game.profileScreenDataManager.addToLoad(assetManager);
	}

	@Override
	public void postProcess() {
		game.profileScreenDataManager.getData(assetManager);
		game.setProfileScreenLoaded(true);
	}
}
