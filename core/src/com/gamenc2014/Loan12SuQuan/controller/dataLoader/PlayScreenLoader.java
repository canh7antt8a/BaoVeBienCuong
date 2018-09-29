package com.gamenc2014.Loan12SuQuan.controller.dataLoader;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.EnemyDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.MapDataManager;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.SoundDataManager;


public class PlayScreenLoader extends AbstractLoader {
	private ArrayList<Object> result;

	private MapDataManager mapDataManager;
	private SoundDataManager soundDataManager;
	private BaoVeBienCuong game;

	public PlayScreenLoader(int mapID, BaoVeBienCuong game) {
		super();

		this.mapDataManager = new MapDataManager(mapID);
		this.mapDataManager.addToLoad(assetManager);

		this.game = game;

		if (game.isEnemyLoaded == false) {
			game.enemyDataManager = new EnemyDataManager();
			game.enemyDataManager.addToLoad(assetManager);
		}

		this.soundDataManager = new SoundDataManager(mapID);
		this.soundDataManager.addToLoad(assetManager);
	}

	@Override
	public void postProcess() {
		result = new ArrayList<Object>();

		this.mapDataManager.getData(assetManager);

		if (game.isEnemyLoaded == false) {
			game.enemyDataManager.getData(assetManager);
			game.isEnemyLoaded = true;
		}

		this.soundDataManager.getData(assetManager);

		result.add(mapDataManager);
		result.add(soundDataManager);
	}

	public ArrayList<Object> getResult() {
		return result;
	}
}
