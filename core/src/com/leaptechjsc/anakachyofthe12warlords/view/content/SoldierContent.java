package com.leaptechjsc.anakachyofthe12warlords.view.content;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.IProfileConstants;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.UnlockArmy_Profile;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.EnumTowerList;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.SwipeButton;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.SwipeView;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.IProfileConstants;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.UnlockArmy_Profile;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.EnumTowerList;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.SwipeButton;
import com.leaptechjsc.anakachyofthe12warlords.view.controlMenu.SwipeView;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.ProfileScreen;

public class SoldierContent extends AbstractContent {
	public ImageButton unlockButton;
	public Label[] soldierInfo;
	public EnumTowerList selectedTower;

	public ImageButton[] levelButton;
	public ScrollPane scrollPane;
	public SwipeView swipeSolider;
	public Table infoTable;

	protected ShapeRenderer drawLine;

	byte[][] tower_is_actived = { { 1, 0, 0 }, { 1, 0, 0 }, { 1, 0, 0 },
			{ 1, 0, 0 } };

	public SwipeButton[][] buttons; // 4 * 3 buttons[i]: mang chua 3 loai tower
//	public Skin skin;
	public UnlockArmy_Profile unlock_profile;

	public int selectedRow;
	public int selectedCol;

	public float rateX;
	public float rateY;

	public SoldierContent(BaoVeBienCuong game) {
		super(game);

		this.rateX = 800f / ProfileScreen.SCR_WIDTH;
		this.rateY = 480f / ProfileScreen.SCR_HEIGHT;

		this.unlock_profile = new UnlockArmy_Profile(baoVeBienCuong);
		setTowerActive();

//		this.skin = skin;
		this.setBounds(0, 5, 520, 400);
		this.selectedCol = 0;
		this.selectedRow = 0;
		this.drawLine = new ShapeRenderer();
		this.infoTable = new Table();

		this.scrollPane = new ScrollPane(infoTable);//, skin);
		this.scrollPane.setBounds(505, 80, 270, 300);
		this.scrollPane.setSmoothScrolling(true);
		this.scrollPane.setOverscroll(false, false);

		this.addActor(scrollPane);

		int i;
		this.soldierInfo = new Label[10];

		BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
		BitmapFont numberFont = baoVeBienCuong.fontDataManager
				.getPlayNumberFont();

		font.getData().setScale(0.4f, 0.4f);
		numberFont.getData().setScale(0.6f, 0.6f);

		infoTable.defaults().left().top().pad(0).expandX();

		soldierInfo[0] = new Label("", new LabelStyle(font, Color.YELLOW));
		infoTable.add(soldierInfo[0]).center().colspan(2);
		infoTable.row();

		for (i = 1; i <= 8; i += 2) {
			soldierInfo[i] = new Label("", new LabelStyle(font, Color.WHITE));
			infoTable.add(soldierInfo[i]);

			soldierInfo[i + 1] = new Label("", new LabelStyle(numberFont,
					Color.GREEN));
			infoTable.add(soldierInfo[i + 1]);

			infoTable.row();
		}

		soldierInfo[9] = new Label(" ", new LabelStyle(font, Color.RED));
		infoTable.add(soldierInfo[9]);
		infoTable.add(new Label("", new LabelStyle(font, Color.WHITE))).expand();
		infoTable.row();

		this.levelButton = new ImageButton[3];
		for (i = 0; i < 3; i++) {
			this.levelButton[i] = new ImageButton(new TextureRegionDrawable(
					game.profileScreenDataManager.getLevelButton(i)));
			levelButton[i].setBounds(505 + i * 90, 65, 90, 32);
			levelButton[i].setColor(0.5f, 0.5f, 0.5f, 0.5f);
			this.addActor(levelButton[i]);
		}

		ButtonImage unlockImage = game.buttonDataManager
				.getMapButton(ButtonDataManager.UNLOCK);

		unlockButton = new ImageButton(new TextureRegionDrawable(
				unlockImage.getImageUp()), new TextureRegionDrawable(
				unlockImage.getImageDown()));
		unlockButton.setBounds(570, 30, 140, 32);
		unlockButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				if (selectedTower != null) {
					if (tower_is_actived[selectedRow][selectedCol] == 0) {
						int money = baoVeBienCuong.requestHandler.getCoin();

						if (EnumTowerList.isEnoughGold(money, selectedTower)) {

							baoVeBienCuong.screenSoundManager.play(
									ScreenSoundDataManager.UPGRADE, false);

							ProfileScreen.setNeedUpdate();

							unlock_profile.setCoins(unlock_profile.getCoins()
									- selectedTower.getUnlockGold());

							switch (selectedRow) {
							case 0: {
								if (selectedCol == 1) {
									unlock_profile
											.putBoolean(
													IProfileConstants.SWORD_1_TAG,
													true);
								} else {
									unlock_profile
											.putBoolean(
													IProfileConstants.SWORD_2_TAG,
													true);
								}
								break;
							}
							case 1: {
								if (selectedCol == 1) {
									unlock_profile.putBoolean(
											IProfileConstants.ARCHER_1_TAG,
											true);
								} else {
									unlock_profile.putBoolean(
											IProfileConstants.ARCHER_2_TAG,
											true);
								}
								break;
							}
							case 2: {
								if (selectedCol == 1) {
									unlock_profile.putBoolean(
											IProfileConstants.KNIGHT_1_TAG,
											true);
								} else {
									unlock_profile.putBoolean(
											IProfileConstants.KNIGHT_2_TAG,
											true);
								}
								break;
							}
							case 3: {
								if (selectedCol == 1) {
									unlock_profile.putBoolean(
											IProfileConstants.SHAMAN_1_TAG,
											true);
								} else {
									unlock_profile.putBoolean(
											IProfileConstants.SHAMAN_2_TAG,
											true);
								}
								break;
							}
							}
							unlock_profile.save();
						} else {
							baoVeBienCuong.requestHandler.shareGetCoin();
						}
					}
				}
			}
		});

		this.addActor(unlockButton);

		this.defaults().expand();
		buttons = new SwipeButton[4][3];

		addButtonToTable();

		this.selectedTower = EnumTowerList
				.getTowerData(ITowerConstants.BASIC_SWORD_LV1);
		this.selectedRow = 0;
		this.selectedCol = 0;
		setInfo();
	}

	public void addButtonToTable() {
		int i, j;

		for (i = 0; i < 3; i++) {
			final int m = i;
			levelButton[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					baoVeBienCuong.screenSoundManager.play(
							ScreenSoundDataManager.CLICK, false);
					if (selectedTower != null) {
						int id = selectedTower.getDataID();
						int tempID = id % 3;
						int dataID = id - tempID + m;

						selectedTower = EnumTowerList.getTowerData(dataID);
						setInfo();

						for (int i = 0; i < 3; i++) {
							if (i != m) {
								levelButton[i].setColor(0.5f, 0.5f, 0.5f, 0.5f);
							} else {
								levelButton[i].setColor(0.5f, 0.5f, 0.5f, 1f);
							}
						}
					}
				}
			});
		}

		for (i = 0; i < 4; i++) {
			for (j = 0; j < 3; j++) {
				buttons[i][j] = new SwipeButton(
						baoVeBienCuong.towerDataManager
								.getTowerAnimation((i * 9 + j * 3)),
						tower_is_actived[i][j], baoVeBienCuong.towerDataManager
								.getLock(), i * 9 + j * 3);

				final int m = i;
				final int n = j;

				buttons[i][j].addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						baoVeBienCuong.screenSoundManager.play(
								ScreenSoundDataManager.CLICK, false);
						selectedTower = EnumTowerList.getTowerData(getDataID(m,
								n));
						buttons[selectedRow][selectedCol].setAnimate(false);
						selectedCol = n;
						selectedRow = m;
						setInfo();
						buttons[m][n].setAnimate(true);
					}
				});

			}
		}
		buttons[selectedRow][selectedCol].setAnimate(true);
		addWidget();
	}

	public void addWidget() {
		Table[] solider = new Table[4];

		Table main = new Table();
		main.row();
		main.align(Align.left);
		for (int i = 0; i < 4; i++) {
			solider[i] = new Table();
			solider[i].setWidth(500);
			solider[i].setHeight(380);
			solider[i].defaults();
			solider[i].add();

			solider[i].add(buttons[i][0]).center().expand();
			solider[i].add();
			solider[i].row();

			solider[i].add(buttons[i][1]).center().expand();
			solider[i].add();

			solider[i].add(buttons[i][2]).center().expand();

			main.add(solider[i]).width(500).height(370).pad(20);

			buttons[i][0].setTable(solider[i]);
			buttons[i][1].setTable(solider[i]);
			buttons[i][2].setTable(solider[i]);
		}

		swipeSolider = new SwipeView(main, 0, 0, 500, 380, baoVeBienCuong);

		this.add(swipeSolider).width(500).height(380).bottom();
	}

	public void setActived(byte[][] isActived) {
		this.tower_is_actived = isActived;
	}

	public void setActived(int typeTower, int typeUpgradeTower, byte value) {
		tower_is_actived[typeTower][typeTower] = value;
	}

	public byte[][] getActived() {
		return tower_is_actived;
	}

	public int getDataID(int row, int col) {
		return ((row * 3 + col) * 3);
	}

	public void setInfo() {
		if (selectedTower != null) {
			soldierInfo[9].setText("");

			soldierInfo[8].setText("" + selectedTower.getUnlockGold());
			soldierInfo[7].setText("Cost: ");

			soldierInfo[6].setText("" + selectedTower.getAttackRange());
			soldierInfo[5].setText("Range");

			soldierInfo[4].setText("" + selectedTower.getAttackTime());
			soldierInfo[3].setText("Speed: ");

			soldierInfo[2].setText("" + selectedTower.getDamage());
			soldierInfo[1].setText("Damage: ");

			soldierInfo[0].setText(selectedTower.getName());

			levelButton[0].setColor(0.5f, 0.5f, 0.5f, 1f);
			levelButton[1].setColor(0.5f, 0.5f, 0.5f, 0.5f);
			levelButton[2].setColor(0.5f, 0.5f, 0.5f, 0.5f);
		}
	}

	public void loadProfile() {
		unlock_profile.load();
		setTowerActive();
	}

	public void setTowerActive() {
		if (unlock_profile.isSword_1() == true) {
			tower_is_actived[0][1] = 1;
		}
		if (unlock_profile.isSword_2() == true) {
			tower_is_actived[0][2] = 1;
		}

		if (unlock_profile.isArcher_1() == true) {
			tower_is_actived[1][1] = 1;
		}
		if (unlock_profile.isArcher_2() == true) {
			tower_is_actived[1][2] = 1;
		}

		if (unlock_profile.isKnight_1() == true) {
			tower_is_actived[2][1] = 1;
		}
		if (unlock_profile.isKnight_2() == true) {
			tower_is_actived[2][2] = 1;
		}

		if (unlock_profile.isShaman_1() == true) {
			tower_is_actived[3][1] = 1;
		}
		if (unlock_profile.isShaman_2() == true) {
			tower_is_actived[3][2] = 1;
		}
	}

	@Override
	public void update() {
		loadProfile();
		int i, j;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 3; j++) {
				if (tower_is_actived[i][j] == 1) {
					buttons[i][j].setColor(0.5f, 0.5f, 0.5f, 1.0f);
				} else {
					buttons[i][j].setColor(0.5f, 0.5f, 0.5f, 0.5f);
				}
			}
		}
	}

//	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].update(0.02f, tower_is_actived[i][j]);
			}
		}
	}
}
