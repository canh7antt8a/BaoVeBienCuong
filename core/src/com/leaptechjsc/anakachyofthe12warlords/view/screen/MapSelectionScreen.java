package com.leaptechjsc.anakachyofthe12warlords.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Level_Profile;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.ButtonImage;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.ButtonDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.FontDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.inputManager.InputListener;
import com.leaptechjsc.anakachyofthe12warlords.controller.profileManager.Level_Profile;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.ScreenSoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.LoadingScreen;
import com.leaptechjsc.anakachyofthe12warlords.view.loadingscreen.PreProfileLoadingScreen;


public class MapSelectionScreen extends AbstractScreen {
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage buttonStage;
    private InputListener listener;

    private TextureRegion worldMap;
    private ImageButton upgrade;

    private int[] level;
    private String[] castleName;

    private int selectedCastleIndex;
    private int selectedDifficult;

    private Level_Profile levelProfile;
    private Dialog infoWindow;
    private Dialog noticeWindow;
    private boolean isInfoOpen;

    private ImageButton[] difficultButton;

    private float scale_x = 1.0f;
    private float scale_y = 1.0f;

    private TextureRegion levelStar;
    private TextureRegion unfinishedStar;

    private TextureRegion finished;
    private TextureRegion unfinished;
    private TextureRegion selected;

    private Coordinate[] position;
    private Label notice;

    public MapSelectionScreen(Object[] inputData, BaoVeBienCuong game) {
        super(inputData, game);
        SCR_WIDTH = 1280;
        SCR_HEIGHT = 720;
        camera = new OrthographicCamera(SCR_WIDTH, SCR_HEIGHT);
        camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);

        viewport = new StretchViewport(SCR_WIDTH, SCR_HEIGHT, camera);
        stage.setViewport(viewport);

        this.buttonStage = new Stage();
        this.buttonStage.setViewport(viewport);

        worldMap = baoVeBienCuong.mapScreenDataManager.getWorldMap();

        ButtonImage upgradeImage = game.buttonDataManager
                .getMapButton(ButtonDataManager.UPGRADE);
        upgrade = new ImageButton(new TextureRegionDrawable(
                upgradeImage.getImageUp()), new TextureRegionDrawable(
                upgradeImage.getImageDown()));

        addActorToStage();

        this.selectedCastleIndex = -1;
        this.selectedDifficult = 0;

        this.levelProfile = new Level_Profile(baoVeBienCuong);

        this.levelStar = baoVeBienCuong.mapScreenDataManager.getLevelStar();
        this.unfinishedStar = baoVeBienCuong.mapScreenDataManager
                .getUnfinishedStar();

        this.finished = baoVeBienCuong.mapScreenDataManager.getFinishedCastle();
        this.unfinished = baoVeBienCuong.mapScreenDataManager
                .getUnfinishedCastle();
        this.selected = baoVeBienCuong.mapScreenDataManager.getSelectedCastle();

        setPosition();
        setLabel();
        initializeUnlock();
        initializeInfoWindows();
        initializeNoticeWindow();


        listener = new InputListener(BG_WIDTH, BG_HEIGHT, SCR_WIDTH,
                SCR_HEIGHT, camera, this);
        multiListener.addProcessor(new GestureDetector(listener));
        multiListener.addProcessor(buttonStage);

        float width = SCR_WIDTH * 0.7f;
        float height = SCR_HEIGHT * 0.8f;

        infoWindow.setBounds((SCR_WIDTH - width) / 2,
                (SCR_HEIGHT - height) / 2, width, height);
        noticeWindow.setBounds((SCR_WIDTH - width) / 2,
                (SCR_HEIGHT - height) / 2, width, height);
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
        batch.begin();
        batch.draw(worldMap, 0 ,0);


        BitmapFont font = baoVeBienCuong.fontDataManager.getMapFont();
        font.getData().setScale(1);

        if (selectedCastleIndex != -1) {
            batch.setColor(0.3f, 0.3f, 0.3f, 1f);

            for (int i = 0; i < 9; i++) {
                GlyphLayout glyphLayout = new GlyphLayout();
                glyphLayout.setText(font, castleName[i]);
                font.draw(batch, castleName[i], position[i].getX()
                        - glyphLayout.width / 4, position[i].getY());

                if (i != selectedCastleIndex) {
                    if (level[i] > -1) {
                        batch.draw(finished, position[i].getX(),
                                position[i].getY(), 0, 0, 121, 110, scale_x,
                                scale_y, 0);
                    } else {
                        batch.draw(unfinished, position[i].getX(),
                                position[i].getY(), 0, 0, 121, 110, scale_x,
                                scale_y, 0);
                    }
                }
            }

            batch.draw(selected, position[selectedCastleIndex].getX(),
                    position[selectedCastleIndex].getY(), 0, 0, 121, 110,
                    1.05f, 1.05f, 0);
        } else {
            batch.setColor(1f, 1f, 1f, 1f);

            for (int i = 0; i < 9; i++) {
                GlyphLayout glyphLayout = new GlyphLayout();
                glyphLayout.setText(font, castleName[i]);

                font.draw(batch, castleName[i], position[i].getX()
                        - glyphLayout.width / 4, position[i].getY());

                if (level[i] > -1) {
                    batch.draw(finished, position[i].getX(),
                            position[i].getY(), 0, 0, 121, 110, scale_x,
                            scale_y, 0);
                } else {
                    batch.draw(unfinished, position[i].getX(),
                            position[i].getY(), 0, 0, 121, 110, scale_x,
                            scale_y, 0);
                }
            }
        }

        drawStar();
        batch.end();
        stage.act(delta);
        stage.draw();
        buttonStage.act(delta);
        buttonStage.draw();
    }

    @Override
    public void update(float delta) {
        upgrade.setBounds(camera.position.x + SCR_WIDTH / 2 - 300 * scale_x,
              camera.position.y - SCR_HEIGHT / 2 + 50 * scale_y,
              251 * scale_x, 70 * scale_y);
      System.out.printf("LOGGAME","=================> update");
      if (selectedCastleIndex != -1) {
          if (!isInfoOpen) {
              if (this.level[selectedCastleIndex] > -1) {
                  infoWindow.getTitleLabel().setText(castleName[selectedCastleIndex]);
                  buttonStage.addActor(infoWindow);
                  isInfoOpen = true;
              } else {
                  buttonStage.addActor(noticeWindow);
                  notice.setText("Complete "
                          + castleName[selectedCastleIndex - 1].toLowerCase());
                  isInfoOpen = true;
              }
          }
      }
    }

    @Override
    public void parseInputData(Object[] inputData) {
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.update();
        viewport.setWorldSize(SCR_WIDTH, SCR_HEIGHT);
        viewport.update(width, height, true);
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

    public boolean isInfoWindowOpen() {
        return isInfoOpen;
    }

    public void drawStar() {
        for (int i = 0; i < 9; i++) {
            if (level[i] > -1) {
                if (level[i] == 0) {
                    for (int j = 1; j < 5; j++) {
                        drawSingleStar(i, j, unfinishedStar);
                    }
                } else {
                    for (int j = 1; j <= level[i]; j++) {
                        drawSingleStar(i, j, levelStar);
                    }

                    for (int j = level[i] + 1; j < 5; j++) {
                        drawSingleStar(i, j, unfinishedStar);
                    }
                }
            }
        }
    }

    public void drawSingleStar(int posID, int starID, TextureRegion star) {
        switch (starID) {
            case 1:
                batch.draw(star, position[posID].getX() - 16,
                        position[posID].getY() + 100);
                break;
            case 2:
                batch.draw(star, position[posID].getX() + 16,
                        position[posID].getY() + 100);
                break;
            case 3:
                batch.draw(star, position[posID].getX() + 48,
                        position[posID].getY() + 100);
                break;
            case 4:
                batch.draw(star, position[posID].getX() + 80,
                        position[posID].getY() + 100);
                break;
        }
    }


    public void initializeInfoWindows() {
        BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
        baoVeBienCuong.fontDataManager
                .setDefaultScale(FontDataManager.BASIC_FONT);

        ButtonImage playImage = baoVeBienCuong.buttonDataManager
                .getEndGameButton(ButtonDataManager.PLAY);

        ImageButton play = new ImageButton(new TextureRegionDrawable(
                playImage.getImageUp()), new TextureRegionDrawable(
                playImage.getImageDown()));

        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Object[] inputData = { selectedCastleIndex, selectedDifficult };

                baoVeBienCuong.screenSoundManager.resetSoundID();
                baoVeBienCuong.screenSoundManager.play(
                        ScreenSoundDataManager.CLICK, false);

                if (selectedCastleIndex < 9) {
                    baoVeBienCuong.setScreen(new LoadingScreen(inputData,
                            baoVeBienCuong));
                } else {
                    MapSelectionScreen.this.baoVeBienCuong.noticeText = "Chức năng đang phát triển";
                }
            }
        });

        ButtonImage mapImage = baoVeBienCuong.buttonDataManager
                .getEndGameButton(ButtonDataManager.RETURN_MAP);

        ImageButton mapButton = new ImageButton(new TextureRegionDrawable(
                mapImage.getImageUp()), new TextureRegionDrawable(
                mapImage.getImageDown()));
        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                baoVeBienCuong.screenSoundManager.play(
                        ScreenSoundDataManager.CLICK, false);
                infoWindow.remove();
                selectedCastleIndex = -1;
                isInfoOpen = false;
            }
        });

        BitmapFont mapFont = baoVeBienCuong.fontDataManager.getMapFont();

        mapFont.getData().setScale(scale_x, scale_y);
        this.infoWindow = new Dialog("", new WindowStyle(mapFont, Color.WHITE,
                null));
        mapFont.getData().setScale(1, 1);

        this.infoWindow.setBackground(new TextureRegionDrawable(
                baoVeBienCuong.menuScreenDataManager.getInfoFrame()));
        infoWindow.clear();

        this.difficultButton = new ImageButton[4];
        ButtonImage tempTexture;

        this.infoWindow.defaults().expand();

        ButtonGroup group = new ButtonGroup();

        Label label = new Label("Select Mode",
                new LabelStyle(font, Color.WHITE));

        infoWindow.add(label).colspan(4).center();
        infoWindow.row().pad(0);

        for (int i = 0; i < difficultButton.length; i++) {
            tempTexture = baoVeBienCuong.mapScreenDataManager.getSword(i);
            this.difficultButton[i] = new ImageButton(
                    new TextureRegionDrawable(tempTexture.getImageUp()),
                    new TextureRegionDrawable(tempTexture.getImageDown()),
                    new TextureRegionDrawable(tempTexture.getImageDown()));
            group.add(this.difficultButton[i]);

            final int m = i;

            this.difficultButton[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    baoVeBienCuong.screenSoundManager.play(
                            ScreenSoundDataManager.CLICK, false);
                    selectedDifficult = m;
                }
            });

            infoWindow.add(this.difficultButton[i]).width(147 * scale_x)
                    .height(184 * scale_y);
        }

        infoWindow.row();
        infoWindow.add(mapButton).center().size(200 * scale_x, 50 * scale_y)
                .colspan(2);
        infoWindow.add(play).center().size(200 * scale_x, 50 * scale_y)
                .colspan(2);
    }

    public void initializeNoticeWindow() {

        BitmapFont mapFont = baoVeBienCuong.fontDataManager.getMapFont();

        mapFont.getData().setScale(1, 1);
        this.noticeWindow = new Dialog("Notice", new WindowStyle(mapFont,
                Color.WHITE, null));
        mapFont.getData().setScale(scale_x, scale_y);

        this.noticeWindow.setBackground(new TextureRegionDrawable(
                baoVeBienCuong.menuScreenDataManager.getInfoFrame()));
        noticeWindow.clear();

        BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
        baoVeBienCuong.fontDataManager
                .setDefaultScale(FontDataManager.BASIC_FONT);
        ButtonImage mapImage = baoVeBienCuong.buttonDataManager
                .getEndGameButton(ButtonDataManager.RETURN_MAP);

        ImageButton mapButton = new ImageButton(new TextureRegionDrawable(
                mapImage.getImageUp()), new TextureRegionDrawable(
                mapImage.getImageDown()));
        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                baoVeBienCuong.screenSoundManager.play(
                        ScreenSoundDataManager.CLICK, false);
                noticeWindow.remove();
                selectedCastleIndex = -1;
                isInfoOpen = false;
            }
        });

        notice = new Label("", new LabelStyle(font, Color.YELLOW));

        noticeWindow.add(notice).center();
        noticeWindow.row();
        noticeWindow.add(mapButton).center().size(200 * scale_x, 50 * scale_y);

    }

    public void setPosition() {
        this.position = new Coordinate[9];

        this.position[0] = new Coordinate((int) (71 * scale_x),
                (int) (500 * scale_y));
        this.position[1] = new Coordinate((int) (71 * scale_x),
                (int) (250 * scale_y));

        this.position[2] = new Coordinate((int) (250 * scale_x),
                (int) (50 * scale_y));
        this.position[3] = new Coordinate((int) (433 * scale_x),
                (int) (511 * scale_y));

        this.position[4] = new Coordinate((int) (467 * scale_x),
                (int) (230 * scale_y));
        this.position[5] = new Coordinate((int) (982 * scale_x),
                (int) (209 * scale_y));

        this.position[6] = new Coordinate((int) (715 * scale_x),
                (int) (160 * scale_y));
        this.position[7] = new Coordinate((int) (750 * scale_x),
                (int) (501 * scale_y));

        this.position[8] = new Coordinate((int) (1031 * scale_x),
                (int) (500 * scale_y));
        // this.position[9] = new Coordinate(943, 360);
        //
        // this.position[10] = new Coordinate(846, 104);
        // this.position[11] = new Coordinate(1039, 188);
    }

    public Coordinate[] getPosition() {
        return this.position;
    }

    public void setLabel() {
        this.castleName = new String[9];

        this.castleName[0] = "Co Loa Castle";
        this.castleName[1] = "Hong River";

        this.castleName[2] = "Hoa Lu Castle";
        this.castleName[3] = "Thai Hoa Palace";

        this.castleName[4] = "Tam Diep";
        this.castleName[5] = "Phong Chau";

        this.castleName[6] = "Duong Lam Village";
        this.castleName[7] = "Tieu Du";

        this.castleName[8] = "Bo Hai Khau";
        // this.castleName[9] = "CHI LĂNG";
        //
        // this.castleName[10] = "ĐỖ ĐỘNG GIANG";
        // this.castleName[11] = "THĂNG LONG THÀNH";
    }

    public void initializeUnlock() {
        this.level = levelProfile.getLevel();
    }

    public void setSelectedCastleIndex(int selectedCastleIndex) {
        this.selectedCastleIndex = selectedCastleIndex;
    }

    public void addActorToStage() {
        upgrade.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                baoVeBienCuong.screenSoundManager.play(
                        ScreenSoundDataManager.CLICK, false);
                if (baoVeBienCuong.isProfileScreenLoaded()) {
                    baoVeBienCuong.setScreen(new ProfileScreen(null,
                            baoVeBienCuong));
                } else {
                    baoVeBienCuong.setScreen(new PreProfileLoadingScreen(null,
                            baoVeBienCuong));
                }
            }
        });

        stage.addActor(upgrade);
    }
}