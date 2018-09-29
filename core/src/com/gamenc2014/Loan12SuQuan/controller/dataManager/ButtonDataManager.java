package com.gamenc2014.Loan12SuQuan.controller.dataManager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;

public class ButtonDataManager extends AbstractDataManager {
	// PLAY SCREEN
	public static final int PAUSE = 0;
	public static final int SPEED = 1;
	public static final int TRAP = 2;
	public static final int ROF = 3;
	public static final int ZOOM = 4;

	public static final int SHOGUN = 7;
	public static final int KNIGHT = 8;
	public static final int SHAMAN = 9;
	public static final int SWORD = 10;
	public static final int ARCHER = 11;

	public static final int SHOGUN_0 = 5;
	public static final int SHOGUN_1 = 6;
	public static final int SHOGUN_2 = 14;
	public static final int SHOGUN_3 = 15;

	// MENU SCREEN
	public static final int START = 0;
	public static final int SETTINGS = 1;
	public static final int HIGHSCORE = 2;

	// UI UPGRADE
	public static final int SWORD_1 = 0;
	public static final int SWORD_2 = 1;

	public static final int ARCHER_1 = 2;
	public static final int ARCHER_2 = 3;

	public static final int SHAMAN_1 = 4;
	public static final int SHAMAN_2 = 5;

	public static final int KNIGHT_1 = 6;
	public static final int KNIGHT_2 = 7;

	public static final int SELL = 8;
	public static final int LEVEL_UP = 9;

	// END GAME
	public static final int RESTART = 0;
	public static final int RETURN_MAP = 1;
	public static final int OK = 2;
	public static final int PLAY = 3;

	// MAP SCREEN
	public static final int CONTINUE = 0;
	public static final int GUIDE = 1;
	public static final int UNLOCK = 2;
	public static final int UPGRADE = 3;

	// ICON
	public static final int FLAG = 0;
	public static final int GOLD = 1;
	public static final int HP = 2;

	// STANDARD
	public static final int SOCIAL = 0;
	public static final int DOWNLOAD = 1;
	public static final int SHARE = 2;
	public static final int INFO = 3;
	public static final int EXIT = 4;

	private ButtonImage[] playScreenButton;
	private ButtonImage[] endGameButton;
	private ButtonImage[] uiUpgrade;

	private ButtonImage[] standardButton;
	private ButtonImage[] menuButton;

	private ButtonImage[] mapScreenButton;
	private TextureRegion[] icon;

	public ButtonDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		assetManager.load(
				ButtonDataManager.resolveID(ICommonConstants.END_SCREEN),
				Texture.class);
		assetManager.load(
				ButtonDataManager.resolveID(ICommonConstants.MAP_SCREEN),
				Texture.class);
		assetManager.load(
				ButtonDataManager.resolveID(ICommonConstants.PLAY_SCREEN),
				Texture.class);
		assetManager.load(
				ButtonDataManager.resolveID(ICommonConstants.UI_UPGRADE),
				Texture.class);
		assetManager.load(
				ButtonDataManager.resolveID(ICommonConstants.MENU_SCREEN),
				Texture.class);
		assetManager.load("data/playScreen/icon.png", Texture.class);
		assetManager.load("data/menuScreen/standard_Button.png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		Texture playScreenButton = assetManager.get(
				ButtonDataManager.resolveID(ICommonConstants.PLAY_SCREEN),
				Texture.class);
		playScreenButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture menuButton = assetManager.get(
				ButtonDataManager.resolveID(ICommonConstants.MENU_SCREEN),
				Texture.class);
		menuButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture endGameButton = assetManager.get(
				ButtonDataManager.resolveID(ICommonConstants.END_SCREEN),
				Texture.class);
		endGameButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture mapScreenButton = assetManager.get(
				ButtonDataManager.resolveID(ICommonConstants.MAP_SCREEN),
				Texture.class);
		mapScreenButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture uiUpgrade = assetManager.get(
				ButtonDataManager.resolveID(ICommonConstants.UI_UPGRADE),
				Texture.class);
		uiUpgrade.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		initializeButton(ICommonConstants.PLAY_SCREEN, playScreenButton,
				ICommonConstants.DEFAULT_TILE_SIZE,
				ICommonConstants.DEFAULT_TILE_SIZE);

		initializeButton(ICommonConstants.END_SCREEN, endGameButton, 251, 70);

		initializeButton(ICommonConstants.MENU_SCREEN, menuButton, 365, 112);

		initializeButton(ICommonConstants.MAP_SCREEN, mapScreenButton, 251, 70);

		initializeButton(ICommonConstants.UI_UPGRADE, uiUpgrade,
				ICommonConstants.DEFAULT_TILE_SIZE,
				ICommonConstants.DEFAULT_TILE_SIZE);

		Texture tempIcon = assetManager.get("data/playScreen/icon.png",
				Texture.class);
		tempIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tempIconArray = (new TextureRegion(tempIcon)).split(
				ICommonConstants.DEFAULT_TILE_SIZE,
				ICommonConstants.DEFAULT_TILE_SIZE);

		int i;
		icon = new TextureRegion[3];
		for (i = 0; i < icon.length; i++) {
			icon[i] = tempIconArray[0][i];
		}

		tempIcon = assetManager.get("data/menuScreen/standard_Button.png",
				Texture.class);
		tempIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tempIconArray = (new TextureRegion(tempIcon)).split(87, 87);

		standardButton = new ButtonImage[5];
		for (i = 0; i < standardButton.length; i++) {
			standardButton[i] = new ButtonImage(i, tempIconArray[i][0],
					tempIconArray[i][1]);
		}
	}

	public ButtonImage getPlayButton(int id) {
		if (id > -1 && id < playScreenButton.length) {
			return playScreenButton[id];
		} else {
			return null;
		}
	}

	public ButtonImage getEndGameButton(int id) {
		if (id > -1 && id < endGameButton.length) {
			return endGameButton[id];
		} else {
			return null;
		}
	}

	public ButtonImage getMapButton(int id) {
		if (id > -1 && id < mapScreenButton.length) {
			return mapScreenButton[id];
		} else {
			return null;
		}
	}

	public ButtonImage getMenuButton(int dataID) {
		if (dataID > -1 && dataID < menuButton.length) {
			return menuButton[dataID];
		} else {
			return null;
		}
	}

	public ButtonImage getStandardButton(int dataID) {
		if (dataID == 0) {
			return standardButton[4];
		} else if (dataID == 1) {
			return standardButton[2];
		} else {
			return null;
		}
	}

	public ButtonImage getUIUpgrade(int dataID) {
		if (dataID > -1 && dataID < uiUpgrade.length) {
			return uiUpgrade[dataID];
		} else {
			return null;
		}
	}

	public TextureRegion getIcon(int id) {
		if (id > -1 && id < icon.length) {
			return icon[id];
		} else {
			return null;
		}
	}

	public static String resolveID(int dataID) {
		switch (dataID) {
		case ICommonConstants.MENU_SCREEN:
			return ("data/menuScreen/menuButton.png");
		case ICommonConstants.MAP_SCREEN:
			return ("data/mapSelectionScreen/mapScreenButton.png");
		case ICommonConstants.PLAY_SCREEN:
			return ("data/playScreen/playScreenButton.png");
		case ICommonConstants.UI_UPGRADE:
			return ("data/playScreen/upgradeButton.png");
		case ICommonConstants.END_SCREEN:
			return ("data/playScreen/endGame.png");
		default:
			return null;
		}
	}

	public void initializeButton(int dataID, Texture data, int sizeX, int sizeY) {
		TextureRegion[][] tempFrame = TextureRegion.split(data, sizeX, sizeY);

		switch (dataID) {
		case ICommonConstants.MENU_SCREEN:
			this.menuButton = new ButtonImage[3];
			for (int i = START; i <= HIGHSCORE; i++) {
				this.menuButton[i] = new ButtonImage(i, tempFrame[i][0],
						tempFrame[i][1]);
			}
			break;

		case ICommonConstants.MAP_SCREEN:
			this.mapScreenButton = new ButtonImage[4];
			for (int i = CONTINUE; i <= UPGRADE; i++) {
				this.mapScreenButton[i] = new ButtonImage(i, tempFrame[i][0],
						tempFrame[i][1]);
			}
			break;

		case ICommonConstants.PLAY_SCREEN:
			TextureRegion[] tempRegion;
			int index = 0;
			int m;
			int n;

			tempRegion = new TextureRegion[tempFrame[0].length
					* tempFrame.length];

			for (m = 0; m < tempFrame.length; m++) {
				for (n = 0; n < tempFrame[0].length; n++) {
					tempRegion[index++] = tempFrame[m][n];
				}
			}

			this.playScreenButton = new ButtonImage[16];
			for (n = PAUSE; n <= ARCHER; n++) {
				this.playScreenButton[n] = new ButtonImage(n,
						tempRegion[n << 1], tempRegion[(n << 1) + 1]);
			}
			break;

		case ICommonConstants.END_SCREEN:
			this.endGameButton = new ButtonImage[4];
			for (int i = RESTART; i <= PLAY; i++) {
				this.endGameButton[i] = new ButtonImage(i, tempFrame[i][0],
						tempFrame[i][1]);
			}
			break;

		case ICommonConstants.UI_UPGRADE:
			this.uiUpgrade = new ButtonImage[10];
			for (int i = SWORD_1; i <= LEVEL_UP; i++) {
				this.uiUpgrade[i] = new ButtonImage(i, tempFrame[0][i],
						tempFrame[1][i]);
			}
			break;
		}
	}

	public ButtonImage[] getUpgradeImage(int towerID) {
		ButtonImage[] result = null;

		switch (towerID) {
		case ITowerConstants.BASIC_ARCHER_LV1:
		case ITowerConstants.BASIC_ARCHER_LV2:
		case ITowerConstants.BASIC_ARCHER_LV3:
			result = new ButtonImage[2];
			result[0] = uiUpgrade[ARCHER_1];
			result[1] = uiUpgrade[ARCHER_2];
			return result;

		case ITowerConstants.BASIC_SWORD_LV1:
		case ITowerConstants.BASIC_SWORD_LV2:
		case ITowerConstants.BASIC_SWORD_LV3:
			result = new ButtonImage[2];
			result[0] = uiUpgrade[SWORD_1];
			result[1] = uiUpgrade[SWORD_2];
			return result;

		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:
			result = new ButtonImage[2];
			result[0] = uiUpgrade[SHAMAN_1];
			result[1] = uiUpgrade[SHAMAN_2];
			return result;

		case ITowerConstants.BASIC_KNIGHT_LV1:
		case ITowerConstants.BASIC_KNIGHT_LV2:
		case ITowerConstants.BASIC_KNIGHT_LV3:
			result = new ButtonImage[2];
			result[0] = uiUpgrade[KNIGHT_1];
			result[1] = uiUpgrade[KNIGHT_2];
			return result;
		default:
			return result;
		}
	}
}
