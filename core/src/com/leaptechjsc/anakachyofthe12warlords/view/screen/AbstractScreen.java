package com.leaptechjsc.anakachyofthe12warlords.view.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.NewStage;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.NewStage;

public abstract class AbstractScreen implements Screen {
	public static final int PROFILE_SCREEN = 0;
	public static final int MAIN_MENU_SCREEN = 1;
	public static final int MAP_SCREEN = 2;
	public static final int PLAY_SCREEN = 3;
	public static final int LOADING_SCREEN = 4;

	protected NewStage stage;
	protected Object[] data;
	protected BaoVeBienCuong baoVeBienCuong;
	protected SpriteBatch batch;
	protected InputMultiplexer multiListener;

	public static int BG_WIDTH;
	public static int BG_HEIGHT;
	public static int SCR_WIDTH;
	public static int SCR_HEIGHT;

	public AbstractScreen(Object[] inputData, BaoVeBienCuong game) {
		SCR_WIDTH = Gdx.graphics.getWidth();
		SCR_HEIGHT = Gdx.graphics.getHeight();

		this.stage = new NewStage(this);
		this.data = inputData;
		this.batch = new SpriteBatch();
		this.baoVeBienCuong = game;

		this.multiListener = new InputMultiplexer();
		this.multiListener.addProcessor(stage);
	}

	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(multiListener);
	}

	@Override
	public void resize(int width, int height) {
//		stage.setViewport(width, height, true);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
	}

	public BaoVeBienCuong getMyGame() {
		return baoVeBienCuong;
	}

	public Stage getStage() {
		return stage;
	}

	public Object[] getData() {
		return data;
	}

	public abstract int getScreenID();

	public abstract void parseInputData(Object[] inputData);

	public abstract void processBackKey();

	public void processHomeKey() {

	}

	public abstract void update(float delta);
}
