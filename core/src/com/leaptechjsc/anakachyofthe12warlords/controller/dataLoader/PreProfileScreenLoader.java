package com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader;

import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ProfileScreenDataManager;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ProfileScreenDataManager;


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
