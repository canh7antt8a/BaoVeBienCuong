package com.gamenc2014.Loan12SuQuan.view.content;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.ButtonDataManager;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.IProfileConstants;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.Skill_Profile;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.ScreenSoundDataManager;
import com.gamenc2014.Loan12SuQuan.view.screen.ProfileScreen;

public class SkillContent extends AbstractContent {
	public static int NUM_ROW = 4;
	public static int NUM_COL = 5;

	public ImageButton[][] buttons;
	public ImageButton upgrade;
	public Label[] textInfo;
	public ScrollPane scrollPane;
	public Table infoTable;

	public boolean[][] isActived;
	public Skill_Profile skill_Profile;

//	public Skin skin;
	public EnumSkillDescription skill;
	public int selectedRow;
	public int selectedCol;

	float scale_x = 1;
	float scale_y = 1;

	public SkillContent(BaoVeBienCuong game) {
		super(game);
//		this.skin = skin;

		this.selectedRow = 0;
		this.selectedCol = 1;
		update();
	}

	public void setIsActived(int level, int row) {
		isActived[row][0] = true;
		for (int i = 1; i <= level; i++) {
			isActived[row][i] = true;
		}

		for (int i = level + 1; i < 5; i++) {
			isActived[row][i] = false;
		}
	}

	public void loadProfile() {
		skill_Profile.load();
		setIsActived(skill_Profile.getMeleeLevel(), 0);
		setIsActived(skill_Profile.getRangeLevel(), 1);
		setIsActived(skill_Profile.getSupportLevel(), 2);
		setIsActived(skill_Profile.getHeroLevel(), 3);
	}

	public boolean isUpgradable() {
		if (selectedCol != -1 && selectedRow != -1) {
			for (int i = 0; i < selectedCol; i++) {
				if (isActived[selectedRow][i] == false) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update() {
		skill_Profile = new Skill_Profile(baoVeBienCuong);

		int i, j;
		isActived = new boolean[4][5];
		for (i = 0; i < isActived.length; i++) {
			isActived[i] = new boolean[5];
		}

		loadProfile();

		scale_x = 800f / 1280;
		scale_y = 480f / 720;

		this.setBounds(0, 5, 500, 400);
		this.defaults().expand();

		this.infoTable = new Table();
		this.scrollPane = new ScrollPane(infoTable);//, skin);
		this.scrollPane.setBounds(505, 80, 270, 300);
		this.scrollPane.setSmoothScrolling(true);
		this.scrollPane.setOverscroll(false, false);

		this.addActor(scrollPane);

		infoTable.defaults().left().top().pad(0).expand();

		BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
		BitmapFont numberFont = baoVeBienCuong.fontDataManager
				.getPlayNumberFont();

		textInfo = new Label[9];

		textInfo[0] = new Label("", new LabelStyle(font, Color.YELLOW));
		infoTable.add(textInfo[0]).colspan(2).center();
		infoTable.row();

		font.getData().setScale(0.4f, 0.4f);
		numberFont.getData().setScale(0.6f, 0.6f);

		for (i = 1; i < 8; i += 2) {
			textInfo[i] = new Label("", new LabelStyle(numberFont, Color.GREEN));
			infoTable.add(textInfo[i]);

			textInfo[i + 1] = new Label("", new LabelStyle(font, Color.WHITE));
			infoTable.add(textInfo[i + 1]);

			infoTable.row();
		}

		buttons = new ImageButton[NUM_ROW][NUM_COL];

		buttons[0][0] = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.profileScreenDataManager.getSkillType(0)));
		buttons[1][0] = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.profileScreenDataManager.getSkillType(1)));
		buttons[2][0] = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.profileScreenDataManager.getSkillType(2)));
		buttons[3][0] = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.profileScreenDataManager.getSkillType(3)));

		ButtonImage upgradeImage = baoVeBienCuong.buttonDataManager
				.getMapButton(ButtonDataManager.UPGRADE);

		upgrade = new ImageButton(new TextureRegionDrawable(
				upgradeImage.getImageUp()), new TextureRegionDrawable(
				upgradeImage.getImageDown()));
		upgrade.setBounds(570, 30, 140, 32);

		final SkillContent skillContent = this;

		upgrade.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				if (skill != null) {
					if (isActived[selectedRow][selectedCol] == false) {
						if (isUpgradable() == true) {
							int money = baoVeBienCuong.requestHandler.getCoin();
							int star = baoVeBienCuong.requestHandler.getStar();

							if (EnumSkillDescription
									.isEnoughMoney(money, skill)) {
								if (EnumSkillDescription.isEnoughStar(star,
										skill)) {

									baoVeBienCuong.screenSoundManager.play(
											ScreenSoundDataManager.UPGRADE,
											false);

									ProfileScreen.setNeedUpdate();
									skillContent.clear();

									skill_Profile.setCoins(money
											- skill.getGold());
									skill_Profile.setStars(star
											- skill.getStar());

									switch (selectedRow) {
									case 0:
										skill_Profile
												.putInteger(
														IProfileConstants.MELEE_TAG,
														skill_Profile
																.getMeleeLevel() + 1);
										break;
									case 1:
										skill_Profile
												.putInteger(
														IProfileConstants.RANGE_TAG,
														skill_Profile
																.getRangeLevel() + 1);
										break;
									case 2:
										skill_Profile
												.putInteger(
														IProfileConstants.SUPPORT_TAG,
														skill_Profile
																.getSupportLevel() + 1);
										break;
									case 3:
										skill_Profile
												.putInteger(
														IProfileConstants.HERO_LEVEL_TAG,
														skill_Profile
																.getHeroLevel() + 1);
										break;
									}

									skill_Profile.save();

								} else {
									baoVeBienCuong.requestHandler
											.shareGetStar();
								}
							} else {
								baoVeBienCuong.requestHandler.shareGetCoin();
							}
						}
					}
				}
			}
		});

		this.addActor(upgrade);

		this.skill = EnumSkillDescription.getSkillDescription(selectedRow,
				selectedCol - 1);
		setInfo();

		TextureRegionDrawable normalSkill, selectedSkill;
		for (i = 0; i < NUM_ROW; i++) {
			for (j = 1; j < NUM_COL; j++) {

				if (isActived[i][j] == true) {
					normalSkill = new TextureRegionDrawable(
							baoVeBienCuong.profileScreenDataManager
									.getSkillButton(i, j - 1));
					selectedSkill = new TextureRegionDrawable(
							baoVeBienCuong.profileScreenDataManager
									.getSelectedSkillButton(i, j - 1));

					buttons[i][j] = new ImageButton(normalSkill, selectedSkill,
							selectedSkill);
				} else {
					normalSkill = new TextureRegionDrawable(
							baoVeBienCuong.profileScreenDataManager
									.getLockedSkill(i, j - 1));
					selectedSkill = new TextureRegionDrawable(
							baoVeBienCuong.profileScreenDataManager
									.getLockedSelectedSkill(i, j - 1));

					buttons[i][j] = new ImageButton(normalSkill, selectedSkill,
							selectedSkill);
				}

				final int m = i;
				final int n = j - 1;

				buttons[i][j].addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						baoVeBienCuong.screenSoundManager.play(
								ScreenSoundDataManager.CLICK, false);
						buttons[selectedRow][selectedCol].setChecked(false);

						selectedCol = n + 1;
						selectedRow = m;

						buttons[selectedRow][selectedCol].setChecked(true);

						skill = EnumSkillDescription.getSkillDescription(m, n);
						setInfo();
					}
				});
			}
		}

		buttons[selectedRow][selectedCol].setChecked(true);

		for (i = 0; i < NUM_ROW; i++) {
			for (j = 0; j < NUM_COL; j++) {
				this.add(buttons[i][j]).width(64).height(64);
			}
			this.row();
		}
	}

	public void setInfo() {
		textInfo[4].setText(skill.getUp_2());
		textInfo[3].setText(skill.getDesc_2());

		textInfo[2].setText(skill.getUp_1());
		textInfo[1].setText(skill.getDesc_1());

		textInfo[8].setText("Star");
		textInfo[7].setText(skill.getStar() + "");

		textInfo[6].setText("Gold");
		textInfo[5].setText(skill.getGold() + "");

		textInfo[0].setText(skill.getName());
	}

	public void openDialogAskSendPayment() {

	}
}
