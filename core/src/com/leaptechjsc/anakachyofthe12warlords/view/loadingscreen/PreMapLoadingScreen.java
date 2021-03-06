package com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen;


import com.badlogic.gdx.Gdx;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader.PreMapScreenLoader;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.MapSelectionScreen;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader.PreMapScreenLoader;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.MapSelectionScreen;

public class PreMapLoadingScreen extends AbstractLoadingScreen {
	private PreMapScreenLoader preMapScreenLoader;

	public PreMapLoadingScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);
		preMapScreenLoader = new PreMapScreenLoader(baoVeBienCuong);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0.796f, 0.871f, 0.412f, 1.0f);

		if (preMapScreenLoader.update()) {
			preMapScreenLoader.postProcess();
			baoVeBienCuong.setScreen(new MapSelectionScreen(null,
					baoVeBienCuong));
		}
		drawLoadingBar();
	}

	public void drawLoadingBar() {
		batch.begin();

		loadFull.setSize(fullWidth * preMapScreenLoader.getProgress(),
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
		preMapScreenLoader.dispose();
	}
}
