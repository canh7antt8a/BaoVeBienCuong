package com.gamenc2014.Loan12SuQuan.view.loadingscreen;


import com.badlogic.gdx.Gdx;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.dataLoader.PreMainMenuLoader;
import com.gamenc2014.Loan12SuQuan.view.screen.MainMenuScreen;

public class SplashScreen extends AbstractLoadingScreen {
	private PreMainMenuLoader preMainMenuLoader;

	public SplashScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);
		preMainMenuLoader = new PreMainMenuLoader(baoVeBienCuong);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0.796f, 0.871f, 0.412f, 1.0f);

		if (preMainMenuLoader.update()) {
			baoVeBienCuong.checkFirstTimeRun();

			preMainMenuLoader.postProcess();
			baoVeBienCuong.setScreen(new MainMenuScreen(null, baoVeBienCuong));
		}
		drawLoadingBar();
	}

	public void drawLoadingBar() {
		batch.begin();
		loadFull.setSize(fullWidth * preMainMenuLoader.getProgress(),
				loadFull.getHeight());
		loadFull.draw(batch);
		load.draw(batch);
		batch.end();
	}

	@Override
	public void parseInputData(Object[] inputData) {
	}

	@Override
	public void dispose() {
		super.dispose();
		preMainMenuLoader.dispose();
	}
}
