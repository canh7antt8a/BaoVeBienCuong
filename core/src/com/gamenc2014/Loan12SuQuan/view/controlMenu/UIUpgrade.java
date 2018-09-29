package com.gamenc2014.Loan12SuQuan.view.controlMenu;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.ButtonDataManager;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.GameLogic;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.ScreenSoundDataManager;
import com.gamenc2014.Loan12SuQuan.model.tower.Tower;
import com.gamenc2014.Loan12SuQuan.model.towerData.TowerData;
import com.gamenc2014.Loan12SuQuan.view.screen.PlayGameScreen;

public class UIUpgrade {

	private ImageButton upLevel;
	private ImageButton upgrade_1;
	private ImageButton upgrade_2;
	private ImageButton sell;

	private OrthographicCamera cam;

	private int scrWidth;
	private int scrHeight;

	private GameLogic gameLogic;

	private boolean isNoUpgrade;
	private boolean isClose;

	private Tower selectedTower;

	private ButtonImage levelUpImage;
	private ButtonImage sellImage;;

	private BaoVeBienCuong baoVeBienCuong;
	private PlayGameScreen screen;
	private Label[] price;

	public UIUpgrade(ButtonDataManager buttonDataManager,
			OrthographicCamera cam, int scrWidth, int scrHeight,
			GameLogic gameLogic, Stage stage, BaoVeBienCuong game,
			PlayGameScreen screen) {

		this.screen = screen;

		this.cam = cam;
		this.baoVeBienCuong = game;

		this.scrWidth = scrWidth;
		this.scrHeight = scrHeight;

		this.gameLogic = gameLogic;

		this.isNoUpgrade = false;
		this.isClose = true;

		levelUpImage = buttonDataManager
				.getUIUpgrade(ButtonDataManager.LEVEL_UP);

		sellImage = buttonDataManager.getUIUpgrade(ButtonDataManager.SELL);

		this.upLevel = new ImageButton(new TextureRegionDrawable(
				levelUpImage.getImageUp()), new TextureRegionDrawable(
				levelUpImage.getImageDown()));

		this.sell = new ImageButton(new TextureRegionDrawable(
				sellImage.getImageUp()), new TextureRegionDrawable(
				sellImage.getImageDown()));

		this.upgrade_1 = new ImageButton(new TextureRegionDrawable(
				levelUpImage.getImageUp()), new TextureRegionDrawable(
				levelUpImage.getImageDown()));

		this.upgrade_2 = new ImageButton(new TextureRegionDrawable(
				sellImage.getImageUp()), new TextureRegionDrawable(
				sellImage.getImageDown()));

		this.price = new Label[4];

		BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
		for (int i = 0; i < price.length; i++) {
			price[i] = new Label("", new Label.LabelStyle(font, Color.WHITE));//game.skin);
		}
	}

	public void addAllListener() {
		upLevel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.UPGRADE, false);
				gameLogic.upgradeLevel();
				removeAllActor();
			}
		});

		sell.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				gameLogic.removeTower();
				removeAllActor();
				UIUpgrade.this.screen.checkShogunButton();
			}
		});

		upgrade_1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.UPGRADE, false);
				gameLogic.upgradeTowerType(0);
				removeAllActor();
			}
		});

		upgrade_2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.UPGRADE, false);
				gameLogic.upgradeTowerType(1);
				removeAllActor();
			}
		});
	}

	public void update() {
		if (isClose == false) {
			float x = worldToScreenX(selectedTower.getX());
			float y = worldToScreenY(selectedTower.getY());

			float temp128 = 128 / cam.zoom;
			float temp192 = 192 / cam.zoom;
			float temp16 = 16 / cam.zoom;

			if (gameLogic.getTowerLevelUp() != null) {
				upLevel.setBounds(x - temp128, y - temp128, temp128, temp128);

				price[0].setBounds(x - temp192 / 4, y - temp128, temp128 / 5,
						32 / cam.zoom);

				if (gameLogic.isEnoughGoldLevelUp()) {
					upLevel.setColor(1, 1, 1, 1f);
				} else {
					upLevel.setColor(1, 1, 1, 0.2f);
				}
			}

			price[1].setBounds(x + 1.5f * temp128 + temp16, y - temp128,
					temp128, 32 / cam.zoom);
			sell.setBounds(x + temp128, y - temp128, temp128, temp128);

			if (isNoUpgrade == false) {
				upgrade_1.setBounds(x - temp128, y + temp128, temp128, temp128);
				if (gameLogic.isEnoughGoldUpgrade(0)) {
					if (gameLogic.isUpgradableInProfile(
							selectedTower.getDataID(), 0)) {
						upgrade_1.setColor(1, 1, 1, 1f);
					} else {
						upgrade_1.setColor(1, 1, 1, 0.2f);
					}
				} else {
					upgrade_1.setColor(1, 1, 1, 0.2f);
				}

				upgrade_2.setBounds(x + temp128, y + temp128, temp128, temp128);
				if (gameLogic.isEnoughGoldUpgrade(1)) {
					if (gameLogic.isUpgradableInProfile(
							selectedTower.getDataID(), 1)) {
						upgrade_2.setColor(1, 1, 1, 1f);
					} else {
						upgrade_2.setColor(1, 1, 1, 0.2f);
					}
				} else {
					upgrade_2.setColor(1, 1, 1, 0.2f);
				}

				price[2].setBounds(x - temp192 / 4, y + temp128, temp128,
						32 / cam.zoom);
				price[3].setBounds(x + 1.5f * temp128 + temp16, y + temp128,
						temp128, 32 / cam.zoom);
			}
		} else {
			removeAllActor();
		}
	}

	public void pinOnStage(boolean isNoUpgrade, Stage stage,
			ButtonDataManager buttonManager) {
		this.upLevel.clear();
		this.sell.clear();
		this.upgrade_1.clear();
		this.upgrade_2.clear();

		removeAllActor();

		this.selectedTower = gameLogic.getSelectedTower();

		this.isNoUpgrade = isNoUpgrade;
		this.isClose = false;

		float x = worldToScreenX(selectedTower.getX());
		float y = worldToScreenY(selectedTower.getY());

		float temp128 = 128 / cam.zoom;

		this.upLevel = new ImageButton(new TextureRegionDrawable(
				levelUpImage.getImageUp()), new TextureRegionDrawable(
				levelUpImage.getImageDown()));

		if (gameLogic.getTowerLevelUp() != null) {
			price[0].setText("" + gameLogic.getTowerLevelUp().getGoldCost());
			price[0].setBounds(x - temp128 / 4, y - temp128, temp128 / 5,
					32 / cam.zoom);
			upLevel.setBounds(x - temp128, y - temp128, temp128, temp128);

			stage.addActor(upLevel);
			stage.addActor(price[0]);
		}

		this.sell = new ImageButton(new TextureRegionDrawable(
				sellImage.getImageUp()), new TextureRegionDrawable(
				sellImage.getImageDown()));

		price[1].setText("" + gameLogic.getSelectedTower().getGoldCost());
		price[1].setBounds(x + 1.75f * temp128, y - temp128, temp128,
				32 / cam.zoom);
		sell.setBounds(x + temp128, y - temp128, temp128, temp128);

		stage.addActor(sell);
		stage.addActor(price[1]);

		if (isNoUpgrade == false) {

			ButtonImage[] upgradeImage = buttonManager
					.getUpgradeImage(selectedTower.getDataID());

			if (gameLogic.isTowerUpgraded(gameLogic.getSelectedTower()
					.getDataID(), 0) == false) {
				upgradeImage[0] = new ButtonImage(0,
						baoVeBienCuong.towerDataManager.getLock(),
						baoVeBienCuong.towerDataManager.getLock());
			}

			if (gameLogic.isTowerUpgraded(gameLogic.getSelectedTower()
					.getDataID(), 1) == false) {
				upgradeImage[1] = new ButtonImage(1,
						baoVeBienCuong.towerDataManager.getLock(),
						baoVeBienCuong.towerDataManager.getLock());
			}

			this.upgrade_1 = new ImageButton(new TextureRegionDrawable(
					upgradeImage[0].getImageUp()), new TextureRegionDrawable(
					upgradeImage[0].getImageDown()));

			this.upgrade_2 = new ImageButton(new TextureRegionDrawable(
					upgradeImage[1].getImageUp()), new TextureRegionDrawable(
					upgradeImage[1].getImageDown()));

			upgrade_1.setBounds(x - temp128, y + temp128, temp128, temp128);
			upgrade_2.setBounds(x + temp128, y + temp128, temp128, temp128);

			TowerData upgradeTower = gameLogic.getUpgradeTowerData(0);
			if (upgradeTower != null) {
				price[2].setText("" + upgradeTower.getGoldCost());
			}

			upgradeTower = gameLogic.getUpgradeTowerData(1);
			if (upgradeTower != null) {
				price[3].setText("" + upgradeTower.getGoldCost());
			}

			price[2].setBounds(x - temp128 / 4, y + temp128, temp128,
					32 / cam.zoom);
			price[3].setBounds(x + 1.75f * temp128, y + temp128, temp128,
					32 / cam.zoom);

			stage.addActor(upgrade_1);
			stage.addActor(price[2]);

			stage.addActor(upgrade_2);
			stage.addActor(price[3]);

			if (selectedTower.getLevel() == 3) {
				upgrade_1.setColor(1, 1, 1, 1);
				upgrade_2.setColor(1, 1, 1, 1);
			} else {
				upgrade_1.setColor(1, 1, 1, 0.2f);
				upgrade_2.setColor(1, 1, 1, 0.2f);
			}
		}

		addAllListener();
	}

	public void removeAllActor() {
		this.isClose = true;
		upLevel.remove();
		sell.remove();
		upgrade_1.remove();
		upgrade_2.remove();

		for (int i = 0; i < price.length; i++) {
			price[i].remove();
		}
	}

	public void turnOff() {
		this.isClose = true;
	}

	public boolean isClose() {
		return this.isClose;
	}

	public float worldToScreenX(float x) {
		return (x - cam.position.x) / cam.zoom + scrWidth / 2;
	}

	public float worldToScreenY(float y) {
		return ((PlayGameScreen.tempValue - y) - cam.position.y) / cam.zoom
				+ scrHeight / 2;
	}
}
