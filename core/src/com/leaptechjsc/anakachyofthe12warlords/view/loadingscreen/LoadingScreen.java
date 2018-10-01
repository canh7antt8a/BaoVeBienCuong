package com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen;

import java.util.ArrayList;





import com.badlogic.gdx.Gdx;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader.PlayScreenLoader;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader.PlayScreenLoader;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class LoadingScreen extends AbstractLoadingScreen {
	private PlayScreenLoader playScreenLoader;
	private int mapID;
	private int difficulty;

	public LoadingScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);
		parseInputData(inputData);
		initLoadPlayScreen();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0.796f, 0.871f, 0.412f, 1.0f);

		if (playScreenLoader.update()) {
			playScreenLoader.postProcess();
			ArrayList<Object> result = playScreenLoader.getResult();
			Object[] inputData = new Object[result.size() + 2];
			inputData[0] = mapID;
			inputData[1] = difficulty;

			for (int i = 2; i < inputData.length; i++) {
				inputData[i] = result.get(i - 2);
			}

			baoVeBienCuong.setScreen(new PlayGameScreen(inputData,
					baoVeBienCuong));
		}
		drawLoadingBar();
	}

	public void drawLoadingBar() {
		batch.begin();
		loadFull.setSize(fullWidth * playScreenLoader.getProgress(),
				loadFull.getHeight());
		loadFull.draw(batch);
		load.draw(batch);
		batch.end();
	}

	public void initLoadPlayScreen() {
		playScreenLoader = new PlayScreenLoader(mapID, baoVeBienCuong);
	}

	@Override
	public void parseInputData(Object[] inputData) {
		mapID = (Integer) inputData[0];
		difficulty = (Integer) inputData[1];
	}

	@Override
	public void dispose() {
		super.dispose();
		playScreenLoader.dispose();
	}
}
