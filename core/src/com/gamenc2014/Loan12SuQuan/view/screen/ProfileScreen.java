package com.gamenc2014.Loan12SuQuan.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;
import com.gamenc2014.Loan12SuQuan.controller.profileManager.AbstractProfile;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.ScreenSoundDataManager;
import com.gamenc2014.Loan12SuQuan.view.content.AbstractContent;
import com.gamenc2014.Loan12SuQuan.view.content.HeroContent;
import com.gamenc2014.Loan12SuQuan.view.content.ItemContent;
import com.gamenc2014.Loan12SuQuan.view.content.SkillContent;
import com.gamenc2014.Loan12SuQuan.view.content.SoldierContent;

public class ProfileScreen extends AbstractScreen {
	public static final int HERO = 0;
	public static final int ITEM = 1;
	public static final int SOLDIER = 2;
	public static final int SKILL = 3;
	public static final int GOLD = 4;

	protected AbstractProfile profile;
	protected Table headerTable;
	protected AbstractContent contentTable;

	protected ImageButton[] headerButton;
	protected Label[] moneyInfo;
	protected int contentIndex;
	protected OrthographicCamera camera;
    private Viewport viewport;
	protected static boolean isNeedUpdate;
	protected float scale_x;
	protected float scale_y;

	public ProfileScreen(Object[] inputData, BaoVeBienCuong game) {
		super(inputData, game);

		game.screenSoundManager.stopAll();
		game.screenSoundManager.play(ScreenSoundDataManager.PROFILE, true);

		SCR_WIDTH = 1280;
		SCR_HEIGHT = 720;

		scale_x = 800f / 1280f;
		scale_y = 480f / 720f;

		camera = new OrthographicCamera(SCR_WIDTH, SCR_HEIGHT);
		camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);

        viewport = new StretchViewport(SCR_WIDTH, SCR_HEIGHT, camera);

        stage.setViewport(viewport);

		this.profile = new AbstractProfile(baoVeBienCuong);
		this.contentIndex = 3;

		this.headerTable = new Table();//baoVeBienCuong.skin);//son cmt

		this.headerTable.setBounds(15, 405, 770, 75);

		this.contentTable = getContentTable(contentIndex);
		isNeedUpdate = false;
		initMenuStage();
	}

	public void initMenuStage() {
		ButtonGroup menuGroup;

		headerButton = new ImageButton[8];

		ButtonImage temp = baoVeBienCuong.profileScreenDataManager
				.getHeroContent();
		headerButton[0] = new ImageButton(new TextureRegionDrawable(
				temp.getImageUp()), new TextureRegionDrawable(
				temp.getImageDown()), new TextureRegionDrawable(
				temp.getImageDown()));

		temp = baoVeBienCuong.profileScreenDataManager.getItemContent();
		headerButton[1] = new ImageButton(new TextureRegionDrawable(
				temp.getImageUp()), new TextureRegionDrawable(
				temp.getImageDown()), new TextureRegionDrawable(
				temp.getImageDown()));

		temp = baoVeBienCuong.profileScreenDataManager.getTowerContent();
		headerButton[2] = new ImageButton(new TextureRegionDrawable(
				temp.getImageUp()), new TextureRegionDrawable(
				temp.getImageDown()), new TextureRegionDrawable(
				temp.getImageDown()));

		temp = baoVeBienCuong.profileScreenDataManager.getSkillContent();
		headerButton[3] = new ImageButton(new TextureRegionDrawable(
				temp.getImageUp()), new TextureRegionDrawable(
				temp.getImageDown()), new TextureRegionDrawable(
				temp.getImageDown()));

		temp = baoVeBienCuong.profileScreenDataManager.getChargeMoney();
		headerButton[4] = new ImageButton(new TextureRegionDrawable(
				temp.getImageUp()), new TextureRegionDrawable(
				temp.getImageDown()), new TextureRegionDrawable(
				temp.getImageDown()));

		headerButton[5] = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.profileScreenDataManager.getGold()));

		headerButton[6] = new ImageButton(new TextureRegionDrawable(
				baoVeBienCuong.profileScreenDataManager.getStar()));

		temp = baoVeBienCuong.profileScreenDataManager.getExitButton();
		headerButton[7] = new ImageButton(new TextureRegionDrawable(
				temp.getImageUp()), new TextureRegionDrawable(
				temp.getImageDown()));

		headerButton[7].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				baoVeBienCuong.screenSoundManager.play(
						ScreenSoundDataManager.CLICK, false);
				baoVeBienCuong.setScreen(new MapSelectionScreen(null,
						baoVeBienCuong));
			}
		});

		for (int i = HERO; i < GOLD; i++) {
			final int j = i;
			headerButton[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					baoVeBienCuong.screenSoundManager.play(
							ScreenSoundDataManager.CLICK, false);
					headerButton[contentIndex].setColor(1f, 1f, 1f, 0.5f);
					contentIndex = j;
					headerButton[contentIndex].setColor(1f, 1f, 1f, 1f);

					if (getContentTable(contentIndex) != null) {
						contentTable.remove();
						contentTable = getContentTable(contentIndex);
						stage.addActor(contentTable);
					}
				}
			});
		}

		headerButton[4].addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				baoVeBienCuong.requestHandler.shareGetCoin();
			}
		});

		menuGroup = new ButtonGroup(headerButton);
		menuGroup.setMaxCheckCount(1);

		float tempSizeX = 135 * scale_x;
		float tempSizeY = 94 * scale_y;

		for (int i = 0; i < 5; i++) {
			headerButton[i].setBounds(i * 145 * scale_x, 0, tempSizeX,
					tempSizeY);
			headerButton[i].setColor(1f, 1f, 1f, 0.5f);
			if (i == 2 || i == 3) {
				headerTable.addActor(headerButton[i]);
			}
		}

		headerButton[5].setBounds(5 * 145 * scale_x, 15, 79 * scale_x,
				47 * scale_y);
		headerTable.addActor(headerButton[5]);

		headerButton[6].setBounds(6 * 145 * scale_x + 50, 10, 61 * scale_x,
				58 * scale_y);
		headerTable.addActor(headerButton[6]);

		headerButton[7].setBounds(6 * headerTable.getWidth() / 7 + 40, 0,
				135 * scale_x, 94 * scale_y);
		headerTable.addActor(headerButton[7]);

		headerButton[contentIndex].setColor(1f, 1f, 1f, 1f);

		moneyInfo = new Label[2];
		moneyInfo[0] = new Label("" + profile.getMoney(), new LabelStyle(
				baoVeBienCuong.fontDataManager.getBasicNumberFont(),
				Color.YELLOW));
		moneyInfo[0].setBounds(5 * 145 * scale_x + 59, 15, 120 * scale_x,
				47 * scale_y);

		moneyInfo[1] = new Label("" + profile.getStar(), new LabelStyle(
				baoVeBienCuong.fontDataManager.getBasicNumberFont(),
				Color.YELLOW));
		moneyInfo[1].setBounds(6 * 145 * scale_x + 98, 15, 100 * scale_x,
				47 * scale_y);
		headerTable.addActor(moneyInfo[0]);
		headerTable.addActor(moneyInfo[1]);

		stage.addActor(headerTable);
		stage.addActor(contentTable);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0.796f, 0.871f, 0.412f, 1.0f);

		batch.begin();
		batch.draw(baoVeBienCuong.profileScreenDataManager.getDownBackground(),
				0, 0);
		batch.end();

		contentTable.act(delta);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void update(float delta) {
		if (isNeedUpdate == true) {
			this.profile.load();
			isNeedUpdate = false;

			contentTable.update();

			moneyInfo[0].setText("" + profile.getMoney());
			moneyInfo[1].setText("" + profile.getStar());
		}
		moneyInfo[0].setText("" + baoVeBienCuong.requestHandler.getCoin());
		moneyInfo[1].setText("" + baoVeBienCuong.requestHandler.getStar());
	}

	@Override
	public void parseInputData(Object[] inputData) {

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
//		stage.setViewport(800, 480, false);
			camera.update();
			viewport.setWorldSize(SCR_WIDTH, SCR_HEIGHT);
			viewport.update(width, height, true);
	}

	public AbstractContent getContentTable(int id) {
		switch (id) {
		case ITEM:
			return new ItemContent(baoVeBienCuong);
		case HERO:
			return new HeroContent(baoVeBienCuong);
		case SKILL:
			return new SkillContent(baoVeBienCuong);
		case SOLDIER:
			return new SoldierContent(baoVeBienCuong);
		default:
			return null;
		}
	}

	public static void setNeedUpdate() {
		isNeedUpdate = true;
	}

	public void updateText() {
		this.profile.load();
		isNeedUpdate = false;

		contentTable.update();

		moneyInfo[0].setText("" + profile.getMoney());
		moneyInfo[1].setText("" + profile.getStar());
	}

	@Override
	public void processBackKey() {
		baoVeBienCuong.setScreen(new MapSelectionScreen(null, baoVeBienCuong));
	}

	@Override
	public int getScreenID() {
		return PROFILE_SCREEN;
	}

	@Override
	public void processHomeKey() {
	}
}
