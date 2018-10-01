package com.leaptechjsc.anakachyofthe12warlords.view.controlMenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.AbstractProfile;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.IProfileConstants;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Level_Profile;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.LoadingScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.MapSelectionScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.AbstractProfile;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.IProfileConstants;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Level_Profile;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.LoadingScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.MapSelectionScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class EndGameMenu {
	private Window endGameWindow;
	private Window boundWindow;

	private Label[] info;
	private ImageButton cancelButton;
	private ImageButton restartButton;
	private BaoVeBienCuong game;

	protected float scale_x;
	protected float scale_y;

	private int mapID;
	private int difficulty;
	int totalScore;
	boolean winState;

	private PlayGameScreen playGameScreen;

	public EndGameMenu(PlayGameScreen screen, boolean isWin,
			int numberDefeatedEnemy, int numberWave, float time, int mapID,
			int difficulty) {

		this.winState = isWin;
		scale_x = PlayGameScreen.SCR_WIDTH / 1280f;
		scale_y = PlayGameScreen.SCR_HEIGHT / 720f;

		this.game = screen.getMyGame();
		this.playGameScreen = screen;

		this.endGameWindow = new Window("", new WindowStyle(
				game.fontDataManager.getMapFont(), Color.WHITE, null));

		float width = PlayGameScreen.SCR_WIDTH * 0.7f;
		float height = PlayGameScreen.SCR_HEIGHT * 0.8f;

		this.boundWindow = new Window("", new WindowStyle(
				game.fontDataManager.getMapFont(), Color.WHITE, null));
		this.boundWindow.setBackground(new TextureRegionDrawable(
				game.menuScreenDataManager.getInfoFrame()));
		this.boundWindow.setBounds((PlayGameScreen.SCR_WIDTH - width) / 2,
				(PlayGameScreen.SCR_HEIGHT - height) / 2, width, height);

		screen.stopAllSound();
		if (isWin == true) {
			screen.playSound(SoundDataManager.WIN, true);
			boundWindow.getTitleLabel().setText("WIN");
		} else {
			screen.playSound(SoundDataManager.LOSE, true);
			boundWindow.getTitleLabel().setText("LOSE");
		}

		initializeLabel(game.fontDataManager.getMapFont(), numberDefeatedEnemy,
				numberWave, time);
		initializeButton();

		this.mapID = mapID;
		this.difficulty = difficulty;
	}

	public void addAllActor(Stage stage) {
		boundWindow.add(endGameWindow);
		stage.addActor(boundWindow);
	}

	public float getX() {
		return endGameWindow.getX();
	}

	public float getY() {
		return endGameWindow.getY();
	}

	public float getWidth() {
		return endGameWindow.getWidth();
	}

	public float getHeight() {
		return endGameWindow.getHeight();
	}

	public void initializeButton() {
		ButtonImage cancelImage = game.buttonDataManager
				.getEndGameButton(ButtonDataManager.RETURN_MAP);
		cancelButton = new ImageButton(new TextureRegionDrawable(
				cancelImage.getImageUp()), new TextureRegionDrawable(
				cancelImage.getImageDown()));

		cancelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);

				if (winState == true) {
					AbstractProfile profile = new AbstractProfile(game);

					profile.setStars(profile.getStars() + difficulty + 1);

					profile.putInteger(IProfileConstants.SCORE_TAG,
							profile.getScore() + totalScore);

					profile.save();

					Level_Profile level_Profile = new Level_Profile(game);

					level_Profile.putInteger(IProfileConstants.LEVEL_TAG
							+ mapID, difficulty + 1);

					if (mapID < 12 && level_Profile.getLevel(mapID + 1) < 0) {
						level_Profile.putInteger(IProfileConstants.LEVEL_TAG
								+ (mapID + 1), 0);
					}

					level_Profile.save();
					level_Profile.load();
				}

				playGameScreen.stopAllSound();
				game.setScreen(new MapSelectionScreen(null, game));
				game.requestHandler.hideAd();
			}
		});

		ButtonImage restartImage = game.buttonDataManager
				.getEndGameButton(ButtonDataManager.RESTART);
		restartButton = new ImageButton(new TextureRegionDrawable(
				restartImage.getImageUp()), new TextureRegionDrawable(
				restartImage.getImageDown()));

		restartButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.screenSoundManager.play(ScreenSoundDataManager.CLICK,
						false);

				if (winState == true) {
					AbstractProfile profile = new AbstractProfile(game);

					profile.setStars(profile.getStars() + difficulty + 1);

					profile.putInteger(IProfileConstants.SCORE_TAG,
							profile.getScore() + totalScore);

					profile.save();

					Level_Profile level_Profile = new Level_Profile(game);

					level_Profile.putInteger(IProfileConstants.LEVEL_TAG
							+ mapID, difficulty + 1);

					if (mapID < 12 && level_Profile.getLevel(mapID + 1) < 0) {
						level_Profile.putInteger(IProfileConstants.LEVEL_TAG
								+ (mapID + 1), 0);
					}

					level_Profile.save();
					level_Profile.load();
				}

				Object[] inputData = { mapID, difficulty };
				playGameScreen.stopAllSound();
				game.setScreen(new LoadingScreen(inputData, game));
				game.requestHandler.hideAd();
			}
		});

		endGameWindow.row();
		endGameWindow.add(cancelButton).center()
				.size(251 * scale_x, 70 * scale_y).bottom();
		endGameWindow.add(restartButton).colspan(2).center()
				.size(251 * scale_x, 70 * scale_y).bottom();
	}

	public void initializeLabel(BitmapFont font, int numberDefeatedEnemy,
			int numberWave, float time) {
		this.info = new Label[17];
		BitmapFont numberFont = game.fontDataManager.getBasicNumberFont();

		for (int i = 0; i < 17; i++) {
			this.info[i] = new Label("", new LabelStyle(font, new Color(1, 1,
					1, 1)));
		}

		endGameWindow.defaults().expand().left();

		String line = "_";
		int upperBound = PlayGameScreen.SCR_WIDTH / 12;
		for (int i = 0; i < upperBound; i++) {
			line += "_";
		}

		Label.LabelStyle labelStyle = new LabelStyle(game.fontDataManager.getBasicFont(), Color.WHITE);

		info[14] = new Label(line, labelStyle);
		info[15] = new Label(line, labelStyle);
		info[16] = new Label(" ", labelStyle);
		endGameWindow.add(info[16]);
		endGameWindow.row();

		endGameWindow.add(info[0]);
		info[0].setText("Type");

		info[1].setText("Number");
		endGameWindow.add(info[1]).right();

		info[2].setText(" Score");
		endGameWindow.add(info[2]).right();

		endGameWindow.row();
		endGameWindow.add(info[15]).colspan(3).center();
		endGameWindow.row();

		endGameWindow.add(info[3]);
		info[3].setText("Defeated Enemies ");

		info[4] = new Label("" + numberDefeatedEnemy, new LabelStyle(
				numberFont, Color.CYAN));
		endGameWindow.add(info[4]).right();

		info[5] = new Label("" + (numberDefeatedEnemy * 3), new LabelStyle(
				numberFont, Color.GREEN));
		endGameWindow.add(info[5]).right();

		endGameWindow.row();

		endGameWindow.add(info[6]);
		info[6].setText("Time ");

		info[7] = new Label("" + ((int) time) + "s", new LabelStyle(numberFont,
				Color.CYAN));
		endGameWindow.add(info[7]).right();

		info[8] = new Label("" + ((int) time), new LabelStyle(numberFont,
				Color.GREEN));
		endGameWindow.add(info[8]).right();

		endGameWindow.row();

		endGameWindow.add(info[9]);
		info[9].setText("Waves ");

		info[10] = new Label("" + (numberWave), new LabelStyle(numberFont,
				Color.CYAN));
		endGameWindow.add(info[10]).right();

		info[11] = new Label("" + (numberWave * 20), new LabelStyle(numberFont,
				Color.GREEN));
		endGameWindow.add(info[11]).right();

		endGameWindow.row();
		endGameWindow.add(info[14]).colspan(3).center();
		endGameWindow.row();
		totalScore = (numberWave * 20 + (int) time + numberDefeatedEnemy * 3);
		info[12].setText("Total");
		info[13] = new Label(""
				+ (numberWave * 20 + (int) time + numberDefeatedEnemy * 3),
				new LabelStyle(numberFont, Color.YELLOW));
		endGameWindow.add(info[12]).colspan(2);
		endGameWindow.add(info[13]).right();
	}
}
