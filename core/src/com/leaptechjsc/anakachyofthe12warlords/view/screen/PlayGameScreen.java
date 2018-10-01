package com.leaptechjsc.anakachyofthe12warlords.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.FontDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.MapDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.inputManager.GameInputListener;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.magic.IMagicContants;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.IScenarioConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.Tower;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.TowerData;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.EndGameMenu;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.PauseGameMenu;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.UIUpgrade;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawBullet;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawEffect;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawEnemy;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawGold;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawGrid;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawMap;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawROF;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawTower;
import com.leaptechjsc.anakachyofthe12warlords.view.drawObject.DrawTrap;

public class PlayGameScreen extends AbstractScreen {

	private OrthographicCamera camera;
	private Viewport viewport;
	private GameInputListener listener;
	public static boolean enableListener = true;

	private DrawMap drawMap;
	private DrawTower drawTower;
	private DrawEnemy drawEnemy;
	private DrawBullet drawBullet;
	private DrawROF drawROF;
	private DrawGrid drawGrid;
	private DrawTrap drawTrap;
	private DrawGold drawGold;
	private DrawEffect drawEffect;
	private ShapeRenderer renderer;

	private MapDataManager mapDataManager;
	private SoundDataManager soundDataManager;

	private GameLogic gameLogic;

	private ImageButton pauseGame;
	private ImageButton incrSpeed; // Increase game speed
	private ImageButton trap; // Trap object
	private ImageButton ROF; // Rain of Fire
	private ImageButton tower[];// List 4 tower use in this map.
	private ImageButton shogun;// List 4 shogun use in this map.
	private ImageButton flag; // flag appear when enemy appear.
	private ImageButton money; // Money in game
	private ImageButton HP; // HP of player. 1 enemy over destination, lose 1 HP
	private ImageButton zoom;

	private Label[] price;

	private Label noHp;
	private Label noFlag;
	private Label noMoney;

	private boolean isPause;
	private int actionType;

	private Stage buttonStage;
	private UIUpgrade uiUpgrade;

	private float scale_x;
	private float scale_y;

	public static int tempValue;

	private boolean[] towerGold;
	private boolean[] shogunGold;

	private EndGameMenu endGameMenu;
	private PauseGameMenu pauseGameMenu;

	public PlayGameScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);
		parseInputData(inputData);
//		SCR_WIDTH = 1280;
//		SCR_HEIGHT = 720;

		scale_x = SCR_WIDTH / 800f;
		scale_y = SCR_HEIGHT / 480f;

		this.actionType = ICommonConstants.NOTHING;
		this.gameLogic = new GameLogic((Integer) inputData[0], 0,
				(Integer) inputData[1], soundDataManager, game);
		Texture[] mapFragments = mapDataManager.getMap();

		BG_WIDTH = mapFragments[0].getWidth() * mapDataManager.getNumCols();
		BG_HEIGHT = mapFragments[0].getHeight() * mapDataManager.getNumRows();

		this.isPause = false;

		camera = new OrthographicCamera();//SCR_WIDTH, SCR_HEIGHT);
		camera.zoom = 1;

		camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
		viewport = new StretchViewport(SCR_WIDTH, SCR_HEIGHT, camera);

		stage.setViewport(viewport);

		buttonStage = new Stage();
//        buttonStage.setViewport(viewport);
		uiUpgrade = new UIUpgrade(baoVeBienCuong.buttonDataManager, camera,
				SCR_WIDTH, SCR_HEIGHT, gameLogic, buttonStage, baoVeBienCuong,
				this);

		tempValue = BG_HEIGHT - ICommonConstants.DEFAULT_TILE_SIZE;

		renderer = new ShapeRenderer();

		listener = new GameInputListener(BG_WIDTH, BG_HEIGHT, SCR_WIDTH,
				SCR_HEIGHT, this, gameLogic);

		multiListener.addProcessor(new GestureDetector(listener));
		multiListener.addProcessor(buttonStage);

		towerGold = gameLogic.isNotEnoughGold();
		shogunGold = gameLogic.isNotEnoughGoldShogun();

		this.drawMap = new DrawMap(mapFragments, mapDataManager.getNumCols(),
				mapDataManager.getNumRows(), batch, gameLogic);
		this.drawTower = new DrawTower(game.towerDataManager, gameLogic
				.getScenario().getTowerList(), batch, gameLogic);
		this.drawEnemy = new DrawEnemy(game.enemyDataManager, batch, gameLogic);
		this.drawBullet = new DrawBullet(game.bulletDataManager, gameLogic
				.getScenario().getBulletList(), batch, gameLogic);
		this.drawROF = new DrawROF(game.magicDataManager,
				gameLogic.getROFList(), batch, gameLogic);
		this.drawGrid = new DrawGrid(mapDataManager.getGrid(), batch,
				gameLogic, camera);
		this.drawTrap = new DrawTrap(game.magicDataManager,
				gameLogic.getTrapList(), batch, gameLogic);
		this.drawEffect = new DrawEffect(game.magicDataManager, batch,
				gameLogic);
		this.drawGold = new DrawGold(gameLogic.getGoldList(),
				game.enemyDataManager.getGoldAnimation(), batch, gameLogic);

		initializeControlMenu();//son
		initializeMagicButton();//son

		addTowerListener();
		addShogunListener();

		addActorToStage();

		this.pauseGameMenu = new PauseGameMenu(game, buttonStage, this);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
        camera.update();
        viewport.setWorldSize(SCR_WIDTH, SCR_HEIGHT);
        viewport.update(width, height, true);
		listener.processZoom(new Vector2(BG_WIDTH, BG_HEIGHT), camera.zoom
				- listener.zoomManager.getMaxZoomLevel());

		baoVeBienCuong.screenSoundManager.stopAll();
		soundDataManager.playScenarioSound();
	}

	@Override
	public void render(float delta) {
		if (endGameMenu == null) {
			super.render(delta);

			if (gameLogic.getCurrentEnemyWave() != null) {
				drawEnemy.setEnemyList(gameLogic.getCurrentEnemyWave()
						.getEnemyList());
				drawROF.setRofList(gameLogic.getROFList());
				drawTrap.setTrapList(gameLogic.getTrapList());
			}

			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.setColor(1, 1, 1, 1f);
			batch.begin();
			drawMap.draw();

			if (actionType != ICommonConstants.NOTHING) {
				drawGrid.draw(actionType);
			}

			if (!uiUpgrade.isClose()) {
				Tower tempTower = gameLogic.getSelectedTower();
				int x = (int) worldToScreenX(tempTower.getX() + 64);
				int y = (int) worldToScreenY(tempTower.getY() - 64);
				batch.end();

				Gdx.gl.glEnable(GL20.GL_BLEND);

				renderer.begin(ShapeType.Filled);
				renderer.setColor(0.5f, 0.5f, 0.5f, 0.35f);
				renderer.circle(x, y, (tempTower.getAttackRange() + 64)
						/ camera.zoom);
				renderer.end();

				renderer.begin(ShapeType.Filled);
				renderer.setColor(0, 0, 0, 0.9f);
				renderer.circle(x, y, (tempTower.getAttackRange() + 64f)
						/ camera.zoom);
				renderer.end();

				batch.begin();
			}

			drawTrap.draw();

			drawEnemy.draw();

			drawGold.draw();
			drawBullet.draw();

			drawTower.draw(uiUpgrade.isClose());

			drawROF.draw();
			drawEffect.draw();

			batch.end();

			float zoom = camera.zoom;
			camera.zoom = 1;

			buttonStage.act(delta);
			buttonStage.draw();

			camera.zoom = zoom;
		} else {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			camera.update();
			batch.setProjectionMatrix(camera.combined);

			batch.begin();

			drawMap.draw();
			drawTrap.draw();
			drawEnemy.draw();
			drawGold.draw();
			drawBullet.draw();
			drawTower.draw(true);
			drawROF.draw();
			drawEffect.draw();

			batch.end();

			buttonStage.act(delta);
			buttonStage.draw();
		}
	}

	@Override
	public void update(float delta) {
		uiUpgrade.update();

		if (isPause == false) {
			gameLogic.update(delta * BaoVeBienCuong.getSpeed());
			if (gameLogic.getState() == IScenarioConstants.ENDING) {
				initializeEndGameMenu();
			}
		}

		if (gameLogic.getNumberTrap() <= 0) {
			trap.setChecked(true);
			trap.getListeners().clear();
			trap.setDisabled(true);
		}

		if (gameLogic.getROFCooldownPercent() >= 1) {
			ROF.clearActions();
		}

		towerGold = gameLogic.isNotEnoughGold();
		for (int i = 0; i < towerGold.length; i++) {
			tower[i].setChecked(towerGold[i]);
		}
		shogunGold = gameLogic.isNotEnoughGoldShogun();
		shogun.setChecked(shogunGold[0]);

		noHp.setText(" " + gameLogic.getPlayerHP());
		noFlag.setText(" " + gameLogic.getCurrentWaveNumber() + " / "
				+ gameLogic.getNumberWave());
		noMoney.setText(" " + gameLogic.getGold());
	}

	@Override
	public void dispose() {
		super.dispose();
		renderer.dispose();
		buttonStage.dispose();

		soundDataManager.stopAll();
		soundDataManager.dispose();
	}

	public void stopAllSound() {
		soundDataManager.stopAll();
	}

	public void playSound(int soundID, boolean isLooping) {
		soundDataManager.play(soundID, isLooping);
	}

	public SoundDataManager getSoundDataManager() {
		return this.soundDataManager;
	}

	public void addActorToStage() {
		buttonStage.addActor(pauseGame);
		buttonStage.addActor(incrSpeed);
		buttonStage.addActor(zoom);

		buttonStage.addActor(money);
		buttonStage.addActor(flag);
		buttonStage.addActor(HP);

		buttonStage.addActor(trap);
		buttonStage.addActor(ROF);

		buttonStage.addActor(noHp);
		buttonStage.addActor(noFlag);
		buttonStage.addActor(noMoney);

		for (int i = 0; i < tower.length; i++) {
			buttonStage.addActor(tower[i]);
			price[i].setText("" + gameLogic.getTowerCost(i));
		}

		if (!gameLogic.isHasShogunTower(ITowerConstants.SHOGUN_0)) {
			buttonStage.addActor(shogun);
			shogun.setBounds((int) (SCR_WIDTH - 80 * scale_x), 5, 64 * scale_x,
					64 * scale_y);
			price[4].setText("" + gameLogic.getShogunCost(0));
			buttonStage.addActor(price[4]);
		} else {
			shogun.remove();
			price[4].remove();
		}

		for (int i = 0; i < tower.length; i++) {
			buttonStage.addActor(price[i]);
		}
	}

	public void parseInputData(Object[] inputData) {
		mapDataManager = (MapDataManager) inputData[2];
		soundDataManager = (SoundDataManager) inputData[3];
	}

	public float screenToWorldX(float x) {
		return camera.position.x + (x - SCR_WIDTH / 2) * camera.zoom;
	}

	public float screenToWorldY(float y) {
		return camera.position.y + (SCR_HEIGHT / 2 - y) * camera.zoom;
	}

	public float worldToScreenX(float x) {
		return (x - camera.position.x) / camera.zoom + SCR_WIDTH / 2;
	}

	public float worldToScreenY(float y) {
		return ((PlayGameScreen.tempValue - y) - camera.position.y)
				/ camera.zoom + SCR_HEIGHT / 2;
	}

	public void changePauseState() {
		if (isPause == false) {
			isPause = true;
			openPauseGameMenu();
			uiUpgrade.removeAllActor();
		} else {
			isPause = false;
			pauseGameMenu.closePauseGameMenu();
		}
	}

	public boolean isPause() {
		return isPause;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void loadImage() {
		TowerData[] result = gameLogic.getTowerUpgrade();
		if (result != null) {
			uiUpgrade.pinOnStage(false, buttonStage,
					baoVeBienCuong.buttonDataManager);
		} else {
			uiUpgrade.pinOnStage(true, buttonStage,
					baoVeBienCuong.buttonDataManager);
		}
	}

	public boolean isUIUpgradeOff() {
		return uiUpgrade.isClose();
	}

	public void turnOffUIUpgrade() {
		uiUpgrade.turnOff();
	}

	public void removeUIUpgrade() {
		uiUpgrade.removeAllActor();
	}

	public void checkShogunButton() {
		if (gameLogic.isHasShogunTower(ITowerConstants.SHOGUN_0) == false) {
			buttonStage.addActor(shogun);
			shogun.setBounds((int) (SCR_WIDTH - 80 * scale_x), 5, 64 * scale_x,
					64 * scale_y);
			price[4].setText("" + gameLogic.getShogunCost(0));
			buttonStage.addActor(price[4]);
		} else {
			shogun.remove();
			price[4].remove();
		}
	}

	public void addTowerListener() {
		ButtonImage temp;
		final int tempAlign = (int) (SCR_WIDTH - 5 * 80 * scale_x);
		tower = new ImageButton[4];

		for (int i = 0; i < 4; i++) {

			final int j = i;
			temp = baoVeBienCuong.buttonDataManager.getPlayButton(gameLogic
					.getTowerImageID(i));
			tower[i] = new ImageButton(new TextureRegionDrawable(
					temp.getImageUp()), new TextureRegionDrawable(
					temp.getImageDown()), new TextureRegionDrawable(
					temp.getImageDown()));

			tower[i].setBounds(tempAlign + i * 80 * scale_x, 5, 64 * scale_x,
					64 * scale_y);

			tower[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					baoVeBienCuong.screenSoundManager.play(
							ScreenSoundDataManager.CLICK, false);
					tower[j].setChecked(false);
				}
			});

			tower[i].addListener(new DragListener() {
				@Override
				public void dragStart(InputEvent event, float x, float y,
						int pointer) {
					if (towerGold[j] == false) {
						enableListener = false;
						actionType = ICommonConstants.BUILDING;
						super.dragStart(event, x, y, pointer);
						buttonStage.getActors().clear();
						buttonStage.addActor(tower[j]);
						turnOffUIUpgrade();
					}
				}

				@Override
				public void drag(InputEvent event, float x, float y, int pointer) {
					if (towerGold[j] == false) {
						super.drag(event, x, y, pointer);
						tower[j].setBounds(event.getStageX() - 32,
								event.getStageY() - 32, 64 * scale_x,
								64 * scale_y);
						drawGrid.setDragCell(new Coordinate(
								(int) screenToWorldX((event.getStageX()))
										/ ICommonConstants.DEFAULT_TILE_SIZE,
								(int) (BG_HEIGHT - camera.position.y + (-event
										.getStageY() + SCR_HEIGHT / 2)
										* camera.zoom)
										/ ICommonConstants.DEFAULT_TILE_SIZE));
					}
				}

				@Override
				public void dragStop(InputEvent event, float x, float y,
						int pointer) {
					if (towerGold[j] == false) {
						addActorToStage();
						super.dragStop(event, x, y, pointer);
						enableListener = true;
						drawGrid.resetDragCell();
						tower[j].setChecked(false);
						tower[j].setBounds(tempAlign + j * 80 * scale_x, 5,
								64 * scale_x, 64 * scale_y);
						gameLogic.buildNewTower(
								j,
								new Coordinate((int) screenToWorldX(event
										.getStageX()), (int) (BG_HEIGHT
										- camera.position.y + (-event
										.getStageY() + SCR_HEIGHT / 2)
										* camera.zoom)));
						actionType = ICommonConstants.NOTHING;
					}
				}

			});
		}
	}

	public void addShogunListener() {
		ButtonImage temp;
		final int tempAlign = (int) (SCR_WIDTH - 80 * scale_x);

		temp = baoVeBienCuong.buttonDataManager.getPlayButton(gameLogic
				.getShogunDataID(0));
		shogun = new ImageButton(new TextureRegionDrawable(temp.getImageUp()),
				new TextureRegionDrawable(temp.getImageDown()),
				new TextureRegionDrawable(temp.getImageDown()));

		shogun.setBounds(tempAlign, 5, 64 * scale_x, 64 * scale_y);

		shogun.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				shogun.setChecked(false);
			}
		});

		if (gameLogic.getHeroLevel(0) != -1) {
			shogun.setColor(1, 1, 1, 1f);
			shogun.addListener(new DragListener() {
				@Override
				public void dragStart(InputEvent event, float x, float y,
						int pointer) {
					if (shogunGold[0] == false) {
						if (gameLogic.isHasShogunTower(gameLogic.getShogunID(0)) == false) {
							buttonStage.getActors().clear();
							turnOffUIUpgrade();
							buttonStage.addActor(shogun);
							enableListener = false;
							actionType = ICommonConstants.BUILDING;
							super.dragStart(event, x, y, pointer);
						}
					}
				}

				@Override
				public void drag(InputEvent event, float x, float y, int pointer) {
					if (shogunGold[0] == false) {
						if (gameLogic.isHasShogunTower(gameLogic.getShogunID(0)) == false) {
							super.drag(event, x, y, pointer);
							shogun.setBounds(event.getStageX() - 32,
									event.getStageY() - 32, 64 * scale_x,
									64 * scale_y);
							drawGrid.setDragCell(new Coordinate(
									(int) screenToWorldX((event.getStageX()))
											/ ICommonConstants.DEFAULT_TILE_SIZE,
									(int) (BG_HEIGHT - camera.position.y + (-event
											.getStageY() + SCR_HEIGHT / 2)
											* camera.zoom)
											/ ICommonConstants.DEFAULT_TILE_SIZE));
						}
					}
				}

				@Override
				public void dragStop(InputEvent event, float x, float y,
						int pointer) {
					if (shogunGold[0] == false) {
						if (gameLogic.isHasShogunTower(gameLogic.getShogunID(0)) == false) {
							super.dragStop(event, x, y, pointer);
							drawGrid.resetDragCell();
							enableListener = true;
							shogun.setChecked(false);
							shogun.setBounds(tempAlign + 80 * scale_x, 5,
									64 * scale_x, 64 * scale_y);
							gameLogic.buildNewShogun(
									0,
									new Coordinate((int) screenToWorldX(event
											.getStageX()), (int) (BG_HEIGHT
											- camera.position.y + (-event
											.getStageY() + SCR_HEIGHT / 2)
											* camera.zoom)));
							actionType = ICommonConstants.NOTHING;
							price[4].remove();
						}
					}

					addActorToStage();
				}
			});
		} else {
			shogun.setColor(1, 1, 1, 0.2f);
		}
	}

	public void initializeControlMenu() {
		ButtonImage pauseGameImage = baoVeBienCuong.buttonDataManager
				.getPlayButton(ButtonDataManager.PAUSE);

		pauseGame = new ImageButton(new TextureRegionDrawable(
				pauseGameImage.getImageUp()), new TextureRegionDrawable(
				pauseGameImage.getImageDown()));
		pauseGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				changePauseState();
			}
		});
		pauseGame.setBounds(0, SCR_HEIGHT - 64 * scale_y, 64 * scale_x,
				64 * scale_y);

		ButtonImage incrSpeedImage = baoVeBienCuong.buttonDataManager
				.getPlayButton(ButtonDataManager.SPEED);

		incrSpeed = new ImageButton(new TextureRegionDrawable(
				incrSpeedImage.getImageUp()), new TextureRegionDrawable(
				incrSpeedImage.getImageDown()));
		incrSpeed.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				BaoVeBienCuong.changeSpeed();
				if (isPause == true) {
					isPause = false;
				}
			}
		});
		incrSpeed.setBounds(80 * scale_x, SCR_HEIGHT - 64 * scale_y,
				64 * scale_x, 64 * scale_y);

		ButtonImage zoomInImage = baoVeBienCuong.buttonDataManager
				.getPlayButton(ButtonDataManager.ZOOM);

		zoom = new ImageButton(new TextureRegionDrawable(
				zoomInImage.getImageUp()), new TextureRegionDrawable(
				zoomInImage.getImageDown()));

        zoom.setBounds(160 * scale_x, SCR_HEIGHT - 64 * scale_y, 64 * scale_x,
                64 * scale_y);

		//son cmt

		zoom.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				listener.processZoom(new Vector2(BG_WIDTH, BG_HEIGHT),
						camera.zoom - listener.zoomManager.getNextZoomLevel());
			}
		});

		baoVeBienCuong.fontDataManager
				.setDefaultScale(FontDataManager.PLAY_NUMBER_FONT);

		LabelStyle labelStyle = new LabelStyle(
				baoVeBienCuong.fontDataManager.getPlayNumberFont(), Color.WHITE);

		noMoney = new Label(" " + gameLogic.getGold(), labelStyle);
		noMoney.setBounds(SCR_WIDTH / 2 - 96 * scale_x, SCR_HEIGHT - 52
				* scale_y, 64 * scale_x, 64 * scale_y);

		noFlag = new Label(" " + gameLogic.getCurrentWaveNumber() + "/"
				+ gameLogic.getNumberWave(), labelStyle);
		noFlag.setBounds(SCR_WIDTH / 2 + 64 * scale_x, SCR_HEIGHT - 52
				* scale_y, 64 * scale_x, 64 * scale_y);

		noHp = new Label(" " + gameLogic.getPlayerHP(), labelStyle);
		noHp.setBounds(SCR_WIDTH - 128 * scale_x, SCR_HEIGHT - 52 * scale_y,
				64 * scale_x, 64 * scale_y);

		money = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.buttonDataManager
						.getIcon(ButtonDataManager.GOLD)));
		money.setBounds(noMoney.getX() + 48 * scale_x, SCR_HEIGHT - 64
				* scale_y, 64 * scale_x, 64 * scale_y);
		money.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// initializeEndGameMenu();
			}
		});

		flag = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.buttonDataManager
						.getIcon(ButtonDataManager.FLAG)));
		flag.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				gameLogic.callEnemyWave();
			}
		});
		flag.setBounds(noFlag.getX() + 64 * scale_x, SCR_HEIGHT - 64 * scale_y,
				64 * scale_x, 64 * scale_y);

		HP = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.buttonDataManager.getIcon(ButtonDataManager.HP)));
		HP.setBounds(noHp.getX() + 48 * scale_x, SCR_HEIGHT - 64 * scale_y,
				64 * scale_x, 64 * scale_y);

		price = new Label[5];

		int tempAlign = (int) (SCR_WIDTH - 5 * 80 * scale_x);

		BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
		for (int i = 0; i < price.length; i++) {
			price[i] = new Label("", new LabelStyle(font, Color.WHITE));//baoVeBienCuong.skin);
			price[i].setBounds(tempAlign + (i * 80 + 40) * scale_x, 0,
					64 * scale_x, 32);
		}
	}

	public void initializeMagicButton() {
		ButtonImage trapImage = baoVeBienCuong.buttonDataManager
				.getPlayButton(ButtonDataManager.TRAP);
		ButtonImage rofImage = baoVeBienCuong.buttonDataManager
				.getPlayButton(ButtonDataManager.ROF);

		trap = new ImageButton(
				new TextureRegionDrawable(trapImage.getImageUp()),
				new TextureRegionDrawable(trapImage.getImageDown()),
				new TextureRegionDrawable(trapImage.getImageDown()));
		trap.setBounds(5, 5, 64 * scale_x, 64 * scale_y);

		ROF = new ImageButton(new TextureRegionDrawable(rofImage.getImageUp()),
				new TextureRegionDrawable(rofImage.getImageDown()),
				new TextureRegionDrawable(rofImage.getImageDown()));
		ROF.setBounds(5 + 80 * scale_x, 5, 64 * scale_x, 64 * scale_y);

		trap.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				trap.setChecked(false);
			}
		});

		trap.addListener(new DragListener() {
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				actionType = ICommonConstants.MAGIC;
				enableListener = false;
				super.dragStart(event, x, y, pointer);

			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				super.drag(event, x, y, pointer);
				trap.setBounds(event.getStageX() - 32 * scale_x,
						event.getStageY() - 32 * scale_y, 64 * scale_x,
						64 * scale_y);
				drawGrid.setDragCell(new Coordinate((int) screenToWorldX((event
						.getStageX())) / ICommonConstants.DEFAULT_TILE_SIZE,
						(int) (BG_HEIGHT - camera.position.y + (-event
								.getStageY() + SCR_HEIGHT / 2) * camera.zoom)
								/ ICommonConstants.DEFAULT_TILE_SIZE));
				buttonStage.getActors().clear();
				turnOffUIUpgrade();
				buttonStage.addActor(trap);
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				super.dragStop(event, x, y, pointer);
				addActorToStage();
				enableListener = true;
				drawGrid.resetDragCell();
				trap.setBounds(0, 5, 64 * scale_x, 64 * scale_y);
				actionType = ICommonConstants.NOTHING;
				gameLogic.useMagic(
						IMagicContants.TRAP,
						new Coordinate((int) screenToWorldX(event.getStageX()),
								(int) (BG_HEIGHT - camera.position.y + (-event
										.getStageY() + SCR_HEIGHT / 2)
										* camera.zoom)));
			}
		});

		ROF.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				ROF.setChecked(false);
			}
		});

		ROF.addListener(new DragListener() {
			@Override
			public void dragStart(InputEvent event, float x, float y,
					int pointer) {
				float tempCooldownPercent = gameLogic.getROFCooldownPercent();
				if (tempCooldownPercent >= 1f) {
					buttonStage.getActors().clear();
					buttonStage.addActor(ROF);
					turnOffUIUpgrade();
					actionType = ICommonConstants.MAGIC;
					enableListener = false;
					super.dragStart(event, x, y, pointer);
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				float tempCooldownPercent = gameLogic.getROFCooldownPercent();
				if (tempCooldownPercent >= 1f) {
					super.drag(event, x, y, pointer);
					ROF.setBounds(event.getStageX() - 32 * scale_x,
							event.getStageY() - 32 * scale_y, 64 * scale_x,
							64 * scale_y);

					drawGrid.setDragCell(new Coordinate(
							(int) screenToWorldX((event.getStageX()))
									/ ICommonConstants.DEFAULT_TILE_SIZE,
							(int) (BG_HEIGHT - camera.position.y + (-event
									.getStageY() + SCR_HEIGHT / 2)
									* camera.zoom)
									/ ICommonConstants.DEFAULT_TILE_SIZE));
				}
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				super.dragStop(event, x, y, pointer);
				enableListener = true;
				ROF.setBounds(80 * scale_x, 5, 64 * scale_x, 64 * scale_y);

				float tempCooldownPercent = gameLogic.getROFCooldownPercent();
				if (tempCooldownPercent >= 1f) {
					addActorToStage();
					actionType = ICommonConstants.NOTHING;
					gameLogic
							.useMagic(
									IMagicContants.RAIN_OF_FIRE,
									new Coordinate((int) screenToWorldX(event
											.getStageX()), (int) (BG_HEIGHT
											- camera.position.y + (-event
											.getStageY() + SCR_HEIGHT / 2)
											* camera.zoom)));
					drawGrid.resetDragCell();

					if (gameLogic.getROFCooldownPercent() < 1) {
						float cooldownTime = gameLogic.getROFCooldown();

						ROF.setColor(1, 1, 1, 0);

						ROF.addAction(Actions.fadeIn(cooldownTime));
					}
				}
			}
		});
	}

	public void initializeEndGameMenu() {
		buttonStage.clear();
		multiListener.clear();
		multiListener.addProcessor(buttonStage);
		batch.setColor(0.2f, 0.2f, 0.2f, 1f);

		if (gameLogic.getPlayerHP() > 0) {
			endGameMenu = new EndGameMenu(this, true,
					Scenario.numberDefeatedEnemy, gameLogic.getScenario()
							.getCurrentWaveID(), gameLogic.getScenario()
							.getStateTime(), gameLogic.getMapID(),
					gameLogic.getDifficulty());
		} else {
			endGameMenu = new EndGameMenu(this, false,
					Scenario.numberDefeatedEnemy, gameLogic.getScenario()
							.getCurrentWaveID(), gameLogic.getScenario()
							.getStateTime(), gameLogic.getMapID(),
					gameLogic.getDifficulty());
		}

		endGameMenu.addAllActor(buttonStage);
		baoVeBienCuong.requestHandler.showLargeAd();
	}

	@Override
	public void processBackKey() {
		if (gameLogic.getState() == IScenarioConstants.ENDING) {
		} else {
			if (this.isPause == true) {
				this.isPause = false;
				pauseGameMenu.removeFromStage();
			} else {
				openPauseGameMenu();
			}
		}
	}

	public void openPauseGameMenu() {
		this.isPause = true;
		pauseGameMenu.openPauseGameMenu();
	}

	@Override
	public int getScreenID() {
		return PLAY_SCREEN;
	}

	@Override
	public void processHomeKey() {
		processBackKey();
	}

	@Override
	public void pause() {
		if (gameLogic.getState() == IScenarioConstants.ENDING) {
		} else if (this.isPause == true) {
		} else {
			openPauseGameMenu();
		}
	}

	@Override
	public void resume() {
	}
}