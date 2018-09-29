package com.gamenc2014.Loan12SuQuan.view.loadingscreen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.view.screen.AbstractScreen;

public class AbstractLoadingScreen extends AbstractScreen {
	protected Texture loading;
	protected Texture loadingFull;

	protected OrthographicCamera camera;
	private Viewport viewport;
	protected Sprite loadFull;
	protected Sprite load;
	protected float fullWidth;

	public AbstractLoadingScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);
		SCR_WIDTH = Gdx.graphics.getWidth();
		SCR_HEIGHT = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(SCR_WIDTH, SCR_HEIGHT);
		camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);

		viewport = new StretchViewport(SCR_WIDTH, SCR_HEIGHT, camera);

		stage.setViewport(viewport);
//		stage.setCamera(camera);
		loading = game.getLoading();
		loadingFull = game.getLoadingFull();

		loadFull = new Sprite(loadingFull);
		loadFull.setSize(loadingFull.getWidth() / 800f * SCR_WIDTH * 0.85f,
				loadingFull.getHeight() / 480f * SCR_HEIGHT);
		loadFull.setPosition((SCR_WIDTH - loadFull.getWidth()) / 2,
				(SCR_HEIGHT - loadFull.getHeight()) / 2);
		fullWidth = loadFull.getWidth();

		load = new Sprite(loading);
		load.setSize(loading.getWidth() / 800f * SCR_WIDTH, loading.getHeight()
				/ 480f * SCR_HEIGHT);
		load.setPosition((SCR_WIDTH - load.getWidth()) / 2,
				(SCR_HEIGHT - load.getHeight()) / 2);
	}

	@Override
	public void parseInputData(Object[] inputData) {
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void processBackKey() {
	}

    @Override
    public void resize(int width, int height) {
        camera.update();
        viewport.setWorldSize(SCR_WIDTH, SCR_HEIGHT);
        viewport.update(width, height, true);
    }

	@Override
	public int getScreenID() {
		return LOADING_SCREEN;
	}

	@Override
	public void processHomeKey() {
	}
}
