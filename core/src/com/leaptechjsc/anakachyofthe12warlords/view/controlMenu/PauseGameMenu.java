package com.leaptechjsc.anakachyofthe12warlords.view.controlMenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.FontDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Settings;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.LoadingScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.MapSelectionScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.FontDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Settings;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.LoadingScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.MapSelectionScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class PauseGameMenu {

	private Window boundWindow;
	private BaoVeBienCuong game;

	private Label soundText;
	private Label musicText;

	private Dialog set;

	private Slider sound;
	private Slider music;

	private ImageButton continuePlay;
	private ImageButton restart;
	private ImageButton mapButton;

	private Stage buttonStage;
	private PlayGameScreen playGameScreen;

	private float scale_x;
	private float scale_y;

	public PauseGameMenu(BaoVeBienCuong baoVeBienCuong, Stage stage,
			PlayGameScreen screen) {

		scale_x = PlayGameScreen.SCR_WIDTH / 1280f;
		scale_y = PlayGameScreen.SCR_HEIGHT / 720f;

		this.game = baoVeBienCuong;
		this.buttonStage = stage;
		this.playGameScreen = screen;

		initialize(buttonStage);
	}

	public void initialize(Stage buttonStage) {
		final Settings settings = new Settings();

		game.fontDataManager.setDefaultScale(FontDataManager.BASIC_FONT);

		boundWindow = new Window("Pause", new WindowStyle(
				game.fontDataManager.getMapFont(), Color.WHITE, null));
		boundWindow.setBackground(new TextureRegionDrawable(
				game.menuScreenDataManager.getInfoFrame()));
		boundWindow.setBounds((PlayGameScreen.SCR_WIDTH - 700 * scale_x) / 2,
				(PlayGameScreen.SCR_HEIGHT - 400 * scale_y) / 2, 700 * scale_x,
				400 * scale_y);

		set = new Dialog("", new WindowStyle(game.fontDataManager.getMapFont(),
				Color.WHITE, null));

		boundWindow.add(set);

		soundText = new Label("Sound", new LabelStyle(
				game.fontDataManager.getMapFont(), Color.WHITE));
		musicText = new Label("Music", new LabelStyle(
				game.fontDataManager.getMapFont(), Color.WHITE));

		final Label soundValue = new Label("0", new LabelStyle(
				game.fontDataManager.getBasicNumberFont(), Color.WHITE));
		final Label musicValue = new Label("0", new LabelStyle(
				game.fontDataManager.getBasicNumberFont(), Color.WHITE));



//		Slider temp = new Slider(0, 100, 5, false, game.skin);

		SliderStyle style = new SliderStyle( new TextureRegionDrawable(game.menuScreenDataManager.getBkg_slider()),//temp.getStyle().background,
				new TextureRegionDrawable(game.menuScreenDataManager.getKnob()));

		sound = new Slider(0, 100, 5, false, style);

		int value = settings.getSoundSetting();
		sound.setValue(value);
		soundValue.setText(String.format("%2d", value));

		music = new Slider(0, 100, 5, false, style);

		value = settings.getMusicSetting();
		music.setValue(value);
		musicValue.setText(String.format("%2d", value));

		sound.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);
				soundValue.setText(String.format("%2d", (int) sound.getValue()));
			}
		});

		music.addListener(new ChangeListener() {
			@Override
			public void changed(
					com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent event,
					Actor actor) {
				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);
				musicValue.setText(String.format("%2d", (int) music.getValue()));
			}
		});

		ButtonImage okImage = game.buttonDataManager
				.getMapButton(ButtonDataManager.CONTINUE);

		continuePlay = new ImageButton(new TextureRegionDrawable(
				okImage.getImageUp()), new TextureRegionDrawable(
				okImage.getImageDown()));

		continuePlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				boundWindow.remove();

				playGameScreen.changePauseState();

				settings.saveSettings((int) sound.getValue(),
						(int) music.getValue());
				game.screenSoundManager.update();
				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);

				playGameScreen.getSoundDataManager().update();
				playGameScreen.getSoundDataManager().replay();
			}
		});

		ButtonImage restartImage = game.buttonDataManager
				.getEndGameButton(ButtonDataManager.RESTART);

		restart = new ImageButton(new TextureRegionDrawable(
				restartImage.getImageUp()), new TextureRegionDrawable(
				restartImage.getImageDown()));

		restart.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				settings.saveSettings((int) sound.getValue(),
						(int) music.getValue());
				game.screenSoundManager.update();

				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);
				playGameScreen.stopAllSound();

				game.setScreen(new LoadingScreen(playGameScreen.getData(), game));
			}
		});

		ButtonImage mapImage = game.buttonDataManager
				.getEndGameButton(ButtonDataManager.RETURN_MAP);

		mapButton = new ImageButton(new TextureRegionDrawable(
				mapImage.getImageUp()), new TextureRegionDrawable(
				mapImage.getImageDown()));

		mapButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);
				settings.saveSettings((int) sound.getValue(),
						(int) music.getValue());
				game.screenSoundManager.update();
				playGameScreen.stopAllSound();
				game.setScreen(new MapSelectionScreen(null, game));
			}
		});

		set.defaults().center().fill();

		set.row();
		set.add(soundText);
		set.add(sound).expand();
		set.add(soundValue).width(40);

		set.row();
		set.add(musicText);
		set.add(music).expand();
		set.add(musicValue).width(40);

		set.row();
		set.add(new Label(" ", new LabelStyle(game.fontDataManager.getBasicFont(), Color.WHITE)));
		set.row();
		set.add(continuePlay).size(200 * scale_x, 50 * scale_y);
		set.add(mapButton).size(200 * scale_x, 50 * scale_y);
		set.add(restart).size(200 * scale_x, 50 * scale_y);
	}

	public void openPauseGameMenu() {
		buttonStage.addActor(boundWindow);
	}

	public void closePauseGameMenu() {
		boundWindow.remove();
	}

	public void removeFromStage() {
		boundWindow.remove();
	}
}
