package com.leaptechjsc.anakachyofthe12warlords.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.AbstractProfile;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Settings;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.PreMapLoadingScreen;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.AbstractProfile;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Settings;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.PreMapLoadingScreen;

public class MainMenuScreen extends AbstractScreen {

	private ImageButton play;
	private ImageButton setting;
	private ImageButton achieve;
	private ImageButton[] standardButton;

	private Table table;
	private OrthographicCamera camera;

    private Viewport viewport;
	public MainMenuScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);

        System.out.printf("=================> update");
		Gdx.app.debug("LOGGAME", "=================>    MAIN MENU");
		baoVeBienCuong.requestHandler.showSmallAd();

		game.screenSoundManager.stopAll();
		game.screenSoundManager.play(ScreenSoundDataManager.MAP_SELECT, true);

		SCR_WIDTH = 1280;
		SCR_HEIGHT = 720;

		camera = new OrthographicCamera(SCR_WIDTH, SCR_HEIGHT);
		camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);

        viewport = new StretchViewport(SCR_WIDTH, SCR_HEIGHT, camera);

        stage.setViewport(viewport);
//		stage.setCamera(camera);

		ButtonImage playImage = game.buttonDataManager
				.getMenuButton(ButtonDataManager.START);
		float buttonWidth = playImage.getImageUp().getRegionWidth();
		float buttonHeight = playImage.getImageUp().getRegionHeight();

		play = new ImageButton(
				new TextureRegionDrawable(playImage.getImageUp()),
				new TextureRegionDrawable(playImage.getImageDown()));

		play.setBounds(SCR_WIDTH, SCR_HEIGHT - 3 * buttonHeight, buttonWidth,
				buttonHeight);

		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);

				baoVeBienCuong.requestHandler.hideAd();
				if (!baoVeBienCuong.isLoaded()) {
					Gdx.app.debug("LOGGAME", "Vao thang thu 1");
					baoVeBienCuong.setScreen(new PreMapLoadingScreen(null,
							baoVeBienCuong));
				} else {
					Gdx.app.debug("LOGGAME", "Vao thang thu 2");
					baoVeBienCuong.setScreen(new MapSelectionScreen(null,
							baoVeBienCuong));

				}
			}
		});

		MoveToAction action_1 = new MoveToAction();
		action_1.setPosition(SCR_WIDTH - buttonWidth - 70, SCR_HEIGHT - 3
				* buttonHeight);
		action_1.setDuration(0.5f);
		play.addAction(action_1);

		ButtonImage upgradeImage = game.buttonDataManager
				.getMenuButton(ButtonDataManager.SETTINGS);

		setting = new ImageButton(new TextureRegionDrawable(
				upgradeImage.getImageUp()), new TextureRegionDrawable(
				upgradeImage.getImageDown()));
		setting.setBounds(SCR_WIDTH, SCR_HEIGHT - 4 * buttonHeight - 20,
				buttonWidth, buttonHeight);

		setting.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				openDialogSetting();
			}
		});

		MoveToAction action_2 = new MoveToAction();
		action_2.setPosition(SCR_WIDTH - buttonWidth - 70, SCR_HEIGHT - 4
				* buttonHeight - 20);
		action_2.setDuration(1);
		setting.addAction(action_2);

		ButtonImage achieveImage = game.buttonDataManager
				.getMenuButton(ButtonDataManager.HIGHSCORE);

		achieve = new ImageButton(new TextureRegionDrawable(
				achieveImage.getImageUp()), new TextureRegionDrawable(
				achieveImage.getImageDown()));
//		achieve.setBounds(SCR_WIDTH, SCR_HEIGHT - 6 * buttonHeight - 40,
//				buttonWidth, buttonHeight);

		achieve.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				super.clicked(event, x, y);
				AbstractProfile profile = new AbstractProfile(baoVeBienCuong);

				baoVeBienCuong.requestHandler.sendHighScore(profile.getScore());
			}
		});

//		MoveToAction action_3 = new MoveToAction();
//		action_3.setPosition(SCR_WIDTH - buttonWidth - 70, SCR_HEIGHT - 6
//				* buttonHeight);
//		action_3.setDuration(1.5f);
//		achieve.addAction(action_3);

		standardButton = new ImageButton[1];
		ButtonImage tempImage;
		for (int i = 0; i < standardButton.length; i++) {
			tempImage = baoVeBienCuong.buttonDataManager.getStandardButton(i);
			standardButton[i] = new ImageButton(new TextureRegionDrawable(
					tempImage.getImageUp()), new TextureRegionDrawable(
					tempImage.getImageDown()));
			standardButton[i].setBounds(SCR_WIDTH - (i + 2) * 100, 50, 87, 87);
		}

		table = new Table();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(
				game.menuScreenDataManager.getMenuBackground())));
		table.setBounds(0, 0, SCR_WIDTH, SCR_HEIGHT);

		initStandardButton();

		addActor();
	}

	public void addActor() {
		stage.addActor(table);
		stage.addActor(play);
		stage.addActor(setting);
//		stage.addActor(achieve);
		for (int i = 0; i < standardButton.length; i++) {
			stage.addActor(standardButton[i]);
		}
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0.796f, 0.871f, 0.412f, 1.0f);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void parseInputData(Object[] inputData) {
	}

	public void openDialogSetting() {
		final Settings settings = new Settings();

		baoVeBienCuong.fontDataManager.getMapFont().getData().setScale(1);

		Window boundWindow = new Window("SETTINGS", new WindowStyle(
				baoVeBienCuong.fontDataManager.getMapFont(), Color.WHITE, null));
		boundWindow.setBackground(new TextureRegionDrawable(
				baoVeBienCuong.menuScreenDataManager.getInfoFrame()));
		boundWindow.setBounds((SCR_WIDTH - 700) / 2, (SCR_HEIGHT - 400) / 2,
				700, 400);

		Dialog set = new Dialog("", new WindowStyle(
				baoVeBienCuong.fontDataManager.getMapFont(), Color.WHITE, null));

		boundWindow.add(set);

		Label soundText = new Label("Sound", new LabelStyle(
				baoVeBienCuong.fontDataManager.getMapFont(), Color.WHITE));
		Label musicText = new Label("Music", new LabelStyle(
				baoVeBienCuong.fontDataManager.getMapFont(), Color.WHITE));

		final Label soundValue = new Label("0", new LabelStyle(
				baoVeBienCuong.fontDataManager.getBasicNumberFont(),
				Color.WHITE));
		final Label musicValue = new Label("0", new LabelStyle(
				baoVeBienCuong.fontDataManager.getBasicNumberFont(),
				Color.WHITE));

//		Slider temp = new Slider(0, 100, 5, false, baoVeBienCuong.skin);

		SliderStyle style = new SliderStyle( new TextureRegionDrawable(baoVeBienCuong.menuScreenDataManager.getBkg_slider()),//temp.getStyle().background,
				new TextureRegionDrawable(
						baoVeBienCuong.menuScreenDataManager.getKnob()));

		final Slider sound = new Slider(0, 100, 5, false, style);

		int value = settings.getSoundSetting();
		sound.setValue(value);
		soundValue.setText(String.format("%2d", value));

		final Slider music = new Slider(0, 100, 5, false, style);

		value = settings.getMusicSetting();
		music.setValue(value);
		musicValue.setText(String.format("%2d", value));

		ButtonImage okImage = baoVeBienCuong.buttonDataManager
				.getEndGameButton(ButtonDataManager.OK);

		ImageButton confirm = new ImageButton(new TextureRegionDrawable(
				okImage.getImageUp()), new TextureRegionDrawable(
				okImage.getImageDown()));

		sound.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				soundValue.setText(String.format("%2d", (int) sound.getValue()));
			}
		});

		music.addListener(new ChangeListener() {
			@Override
			public void changed(
					com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent event,
					Actor actor) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				musicValue.setText(String.format("%2d", (int) music.getValue()));
			}
		});

		confirm.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				stage.clear();
				addActor();
				settings.saveSettings((int) sound.getValue(),
						(int) music.getValue());
				baoVeBienCuong.screenSoundManager.update();
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
			}
		});

		set.defaults().expand().left().pad(10);

		set.row();
		set.add(soundText);
		set.add(sound).width(SCR_WIDTH / 4);
		set.add(soundValue).right().width(40);

		set.row();
		set.add(musicText);
		set.add(music).width(SCR_WIDTH / 4);
		set.add(musicValue).right().width(40);

		set.row();
		set.add(new Label(" ", new LabelStyle(baoVeBienCuong.fontDataManager.getBasicFont(), Color.WHITE)));
		set.row();
		set.add(confirm).colspan(3).center().bottom();

		stage.addActor(boundWindow);
	}

	public void initStandardButton() {
		standardButton[0].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.stopAll();
				baoVeBienCuong.requestHandler.exit();
			}
		});

//		standardButton[1].addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				baoVeBienCuong.requestHandler.shareGetCoin();
//			}
//		});

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
        camera.update();
        viewport.setWorldSize(SCR_WIDTH, SCR_HEIGHT);
        viewport.update(width, height, true);
//		stage.setViewport(SCR_WIDTH, SCR_HEIGHT, false);
	}

	@Override
	public void processBackKey() {
		baoVeBienCuong.screenSoundManager.stopAll();
		baoVeBienCuong.requestHandler.exit();
	}

	@Override
	public int getScreenID() {
		return MAIN_MENU_SCREEN;
	}

	@Override
	public void processHomeKey() {
	}
}
