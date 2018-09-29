package com.gamenc2014.Loan12SuQuan.view.loadingscreen;


import com.badlogic.gdx.Gdx;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.dataLoader.PreProfileScreenLoader;
import com.gamenc2014.Loan12SuQuan.view.screen.ProfileScreen;

public class PreProfileLoadingScreen extends AbstractLoadingScreen {
	private PreProfileScreenLoader preProfileScreenLoader;

	public PreProfileLoadingScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);
		preProfileScreenLoader = new PreProfileScreenLoader(baoVeBienCuong);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0.796f, 0.871f, 0.412f, 1.0f);

		if (preProfileScreenLoader.update()) {
			preProfileScreenLoader.postProcess();
			baoVeBienCuong.setScreen(new ProfileScreen(null, baoVeBienCuong));
		}
		drawLoadingBar();
	}

	public void drawLoadingBar() {
		batch.begin();

		loadFull.setSize(fullWidth * preProfileScreenLoader.getProgress(),
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
		preProfileScreenLoader.dispose();
	}
}
