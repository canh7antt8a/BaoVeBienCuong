package com.gamenc2014.Loan12SuQuan.controller.dataManager;

import java.util.ArrayList;



import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.Image;
import com.gamenc2014.Loan12SuQuan.controller.data.ObjectAnimation;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;

public class TowerDataManager extends AbstractDataManager {
	private ObjectAnimation[] towerAnimationList;
	private Texture star;
	private TextureRegion lock;

	public TowerDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		String tempTowerPath;

		int i;
		for (i = ITowerConstants.BASIC_SWORD_LV1; i <= ITowerConstants.SHOGUN_3; i += 3) {
			tempTowerPath = resolveID(i);
			assetManager.load(tempTowerPath + "0.png", Texture.class);
			assetManager.load(tempTowerPath + "1.png", Texture.class);
			assetManager.load(tempTowerPath + "2.png", Texture.class);
		}
		assetManager.load("data/tower/star.png", Texture.class);
		assetManager.load("data/profileScreen/lock.png", Texture.class);
	}

	@Override
	public void getData(AssetManager assetManager) {
		ArrayList<Image> towerImageList = new ArrayList<Image>();

		int i;
		String tempTowerPath;
		for (i = ITowerConstants.BASIC_SWORD_LV1; i <= ITowerConstants.SHOGUN_3; i += 3) {
			tempTowerPath = resolveID(i);

			int tempID = i;

			towerImageList.add(new Image(tempID, new TextureRegion(assetManager
					.get(tempTowerPath + "0.png", Texture.class))));
			towerImageList.add(new Image(tempID + 1, new TextureRegion(
					assetManager.get(tempTowerPath + "1.png", Texture.class))));
			towerImageList.add(new Image(tempID + 2, new TextureRegion(
					assetManager.get(tempTowerPath + "2.png", Texture.class))));
		}

		Texture temp;
		Coordinate size;

		this.towerAnimationList = new ObjectAnimation[towerImageList.size()];
		i = 0;
		for (Image towerImage : towerImageList) {
			temp = towerImage.getImage().getTexture();
			temp.setFilter(TextureFilter.Linear, TextureFilter.Linear);

			size = getTowerSize(towerImage.getID());

			towerAnimationList[i] = new ObjectAnimation(towerImage.getID(),
					getAnimationFromTexture(temp, size.getX(), size.getY()));
			i++;
		}

		this.star = assetManager.get("data/tower/star.png", Texture.class);
		this.star.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.lock = new TextureRegion(assetManager.get(
				"data/profileScreen/lock.png", Texture.class));
	}

	public Animation getTowerAnimation(int id) {
		if (id > -1 && id < towerAnimationList.length) {
			return towerAnimationList[id].getAnimation();
		} else {
			return null;
		}
	}

	public TextureRegion getKeyFrame(int id, float stateTime) {
		if (id > -1 && id < towerAnimationList.length) {
			return (TextureRegion)towerAnimationList[id].getAnimation().getKeyFrame(stateTime);
		} else {
			return null;
		}
	}

	public String resolveID(int id) {
		switch (id) {
		case ITowerConstants.BASIC_ARCHER_LV1:
			return "data/tower/archer_0";

		case ITowerConstants.ARCHER_1_LV1:
			return "data/tower/archer_1";

		case ITowerConstants.ARCHER_2_LV1:
			return "data/tower/archer_2";

		case ITowerConstants.BASIC_SWORD_LV1:
			return "data/tower/sword_0";

		case ITowerConstants.SWORD_1_LV1:
			return "data/tower/sword_1";

		case ITowerConstants.SWORD_2_LV1:
			return "data/tower/sword_2";

		case ITowerConstants.BASIC_SHAMAN_LV1:
			return "data/tower/shaman_0";

		case ITowerConstants.SHAMAN_1_LV1:
			return "data/tower/shaman_1";

		case ITowerConstants.SHAMAN_2_LV1:
			return "data/tower/shaman_2";

		case ITowerConstants.BASIC_KNIGHT_LV1:
			return "data/tower/knight_0";

		case ITowerConstants.KNIGHT_1_LV1:
			return "data/tower/knight_1";

		case ITowerConstants.KNIGHT_2_LV1:
			return "data/tower/knight_2";

		case ITowerConstants.SHOGUN_0:
			return "data/tower/shogun_0";

		case ITowerConstants.SHOGUN_1:
			return "data/tower/shogun_1";

		case ITowerConstants.SHOGUN_2:
			return "data/tower/shogun_2";

		case ITowerConstants.SHOGUN_3:
			return "data/tower/shogun_3";

		default:
			return null;
		}
	}

	public static Coordinate getTowerSize(int id) {
		Coordinate result = new Coordinate(0, 0);
		switch (id) {
		case ITowerConstants.BASIC_ARCHER_LV1:
			result.set(143, 154);
			return result;
		case (ITowerConstants.BASIC_ARCHER_LV1 + 1):
			result.set(99, 153);
			return result;
		case (ITowerConstants.BASIC_ARCHER_LV1 + 2):
			result.set(121, 145);
			return result;

		case ITowerConstants.ARCHER_1_LV1:
			result.set(188, 145);
			return result;
		case (ITowerConstants.ARCHER_1_LV1 + 1):
			result.set(138, 198);
			return result;
		case (ITowerConstants.ARCHER_1_LV1 + 2):
			result.set(155, 167);
			return result;

		case ITowerConstants.ARCHER_2_LV1:
			result.set(219, 158);
			return result;
		case (ITowerConstants.ARCHER_2_LV1 + 1):
			result.set(160, 211);
			return result;
		case (ITowerConstants.ARCHER_2_LV1 + 2):
			result.set(162, 177);
			return result;

		case ITowerConstants.BASIC_SWORD_LV1:
			result.set(152, 153);
			return result;
		case (ITowerConstants.BASIC_SWORD_LV1 + 1):
			result.set(193, 193);
			return result;
		case (ITowerConstants.BASIC_SWORD_LV1 + 2):
			result.set(213, 228);
			return result;

		case ITowerConstants.SWORD_1_LV1:
			result.set(211, 202);
			return result;
		case (ITowerConstants.SWORD_1_LV1 + 1):
			result.set(313, 293);
			return result;
		case (ITowerConstants.SWORD_1_LV1 + 2):
			result.set(305, 297);
			return result;

		case ITowerConstants.SWORD_2_LV1:
			result.set(189, 174);
			return result;
		case (ITowerConstants.SWORD_2_LV1 + 1):
			result.set(309, 264);
			return result;
		case (ITowerConstants.SWORD_2_LV1 + 2):
			result.set(273, 308);
			return result;

		case ITowerConstants.BASIC_SHAMAN_LV1:
			result.set(165, 170);
			return result;
		case (ITowerConstants.BASIC_SHAMAN_LV1 + 1):
			result.set(190, 214);
			return result;
		case (ITowerConstants.BASIC_SHAMAN_LV1 + 2):
			result.set(191, 185);
			return result;

		case ITowerConstants.SHAMAN_1_LV1:
			result.set(206, 201);
			return result;
		case (ITowerConstants.SHAMAN_1_LV1 + 1):
			result.set(292, 217);
			return result;
		case (ITowerConstants.SHAMAN_1_LV1 + 2):
			result.set(269, 221);
			return result;

		case ITowerConstants.SHAMAN_2_LV1:
			result.set(253, 224);
			return result;
		case (ITowerConstants.SHAMAN_2_LV1 + 1):
			result.set(292, 234);
			return result;
		case (ITowerConstants.SHAMAN_2_LV1 + 2):
			result.set(296, 242);
			return result;

		case ITowerConstants.BASIC_KNIGHT_LV1:
			result.set(153, 159);
			return result;
		case (ITowerConstants.BASIC_KNIGHT_LV1 + 1):
			result.set(212, 200);
			return result;
		case (ITowerConstants.BASIC_KNIGHT_LV1 + 2):
			result.set(186, 216);
			return result;

		case ITowerConstants.KNIGHT_1_LV1:
			result.set(171, 230);
			return result;
		case (ITowerConstants.KNIGHT_1_LV1 + 1):
			result.set(292, 267);
			return result;
		case (ITowerConstants.KNIGHT_1_LV1 + 2):
			result.set(349, 248);
			return result;

		case ITowerConstants.KNIGHT_2_LV1:
			result.set(230, 205);
			return result;
		case (ITowerConstants.KNIGHT_2_LV1 + 1):
			result.set(265, 308);
			return result;
		case (ITowerConstants.KNIGHT_2_LV1 + 2):
			result.set(302, 270);
			return result;

		case ITowerConstants.SHOGUN_0:
			result.set(281, 248);
			return result;
		case (ITowerConstants.SHOGUN_0 + 1):
			result.set(407, 440);
			return result;
		case (ITowerConstants.SHOGUN_0 + 2):
			result.set(441, 415);
			return result;

		case ITowerConstants.SHOGUN_1:
			result.set(158, 158);
			return result;
		case (ITowerConstants.SHOGUN_1 + 1):
			result.set(158, 158);
			return result;
		case (ITowerConstants.SHOGUN_1 + 2):
			result.set(158, 158);
			return result;

		case ITowerConstants.SHOGUN_2:
			result.set(158, 158);
			return result;
		case (ITowerConstants.SHOGUN_2 + 1):
			result.set(158, 158);
			return result;
		case (ITowerConstants.SHOGUN_2 + 2):
			result.set(158, 158);
			return result;

		case ITowerConstants.SHOGUN_3:
			result.set(158, 158);
			return result;
		case (ITowerConstants.SHOGUN_3 + 1):
			result.set(158, 158);
			return result;
		case (ITowerConstants.SHOGUN_3 + 2):
			result.set(158, 158);
			return result;

		default:
			return null;

		}
	}

	public static Coordinate getTowerPosition(int id) {
		Coordinate result = new Coordinate(0, 0);
		switch (id) {
		case ITowerConstants.BASIC_ARCHER_LV1:
			result.set(-4, 1);
			return result;
		case (ITowerConstants.BASIC_ARCHER_LV1 + 1):
			result.set(12, 10);
			return result;
		case (ITowerConstants.BASIC_ARCHER_LV1 + 2):
			result.set(12, 10);
			return result;

		case ITowerConstants.ARCHER_1_LV1:
			result.set(-30, 5);
			return result;
		case (ITowerConstants.ARCHER_1_LV1 + 1):
			result.set(-21, 19);
			return result;
		case (ITowerConstants.ARCHER_1_LV1 + 2):
			result.set(3, 25);
			return result;

		case ITowerConstants.ARCHER_2_LV1:
			result.set(-42, 0);
			return result;
		case (ITowerConstants.ARCHER_2_LV1 + 1):
			result.set(-36, 32);
			return result;
		case (ITowerConstants.ARCHER_2_LV1 + 2):
			result.set(-7, 21);
			return result;

		case ITowerConstants.BASIC_SWORD_LV1:
			result.set(-43, 0);
			return result;
		case (ITowerConstants.BASIC_SWORD_LV1 + 1):
			result.set(-36, 0);
			return result;
		case (ITowerConstants.BASIC_SWORD_LV1 + 2):
			result.set(-86, 36);
			return result;

		case ITowerConstants.SWORD_1_LV1:
			result.set(-80, 47);
			return result;
		case (ITowerConstants.SWORD_1_LV1 + 1):
			result.set(-100, 52);
			return result;
		case (ITowerConstants.SWORD_1_LV1 + 2):
			result.set(-98, 92);
			return result;

		case ITowerConstants.SWORD_2_LV1:
			result.set(-44, 14);
			return result;
		case (ITowerConstants.SWORD_2_LV1 + 1):
			result.set(-95, 36);
			return result;
		case (ITowerConstants.SWORD_2_LV1 + 2):
			result.set(-100, 97);
			return result;

		case ITowerConstants.BASIC_SHAMAN_LV1:
			result.set(-23, 27);
			return result;
		case (ITowerConstants.BASIC_SHAMAN_LV1 + 1):
			result.set(-40, 55);
			return result;
		case (ITowerConstants.BASIC_SHAMAN_LV1 + 2):
			result.set(-21, 42);
			return result;

		case ITowerConstants.SHAMAN_1_LV1:
			result.set(-74, 0);
			return result;
		case (ITowerConstants.SHAMAN_1_LV1 + 1):
			result.set(-45, 0);
			return result;
		case (ITowerConstants.SHAMAN_1_LV1 + 2):
			result.set(-100, 0);
			return result;

		case ITowerConstants.SHAMAN_2_LV1:
			result.set(-116, 0);
			return result;
		case (ITowerConstants.SHAMAN_2_LV1 + 1):
			result.set(-54, 0);
			return result;
		case (ITowerConstants.SHAMAN_2_LV1 + 2):
			result.set(-100, 0);
			return result;

		case ITowerConstants.BASIC_KNIGHT_LV1:
			result.set(-36, 8);
			return result;
		case (ITowerConstants.BASIC_KNIGHT_LV1 + 1):
			result.set(-25, 0);
			return result;
		case (ITowerConstants.BASIC_KNIGHT_LV1 + 2):
			result.set(-48, 40);
			return result;

		case ITowerConstants.KNIGHT_1_LV1:
			result.set(4, 75);
			return result;
		case (ITowerConstants.KNIGHT_1_LV1 + 1):
			result.set(-90, 69);
			return result;
		case (ITowerConstants.KNIGHT_1_LV1 + 2):
			result.set(-104, 94);
			return result;

		case ITowerConstants.KNIGHT_2_LV1:
			result.set(-5, 51);
			return result;
		case (ITowerConstants.KNIGHT_2_LV1 + 1):
			result.set(-61, 90);
			return result;
		case (ITowerConstants.KNIGHT_2_LV1 + 2):
			result.set(-114, 102);
			return result;

		case ITowerConstants.SHOGUN_0:
			result.set(-120, 55);
			return result;
		case (ITowerConstants.SHOGUN_0 + 1):
			result.set(-145, 100);
			return result;
		case (ITowerConstants.SHOGUN_0 + 2):
			result.set(-165, 100);
			return result;

		case ITowerConstants.SHOGUN_1:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_1 + 1):
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_1 + 2):
			result.set(0, 0);
			return result;

		case ITowerConstants.SHOGUN_2:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_2 + 1):
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_2 + 2):
			result.set(0, 0);
			return result;

		case ITowerConstants.SHOGUN_3:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_3 + 1):
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_3 + 2):
			result.set(0, 0);
			return result;

		default:
			return null;

		}
	}

	public static Coordinate getTowerFlipPosition(int id) {
		Coordinate result = new Coordinate(0, 0);
		switch (id) {
		case ITowerConstants.BASIC_ARCHER_LV1:
			result.set(-4, 1);
			return result;
		case (ITowerConstants.BASIC_ARCHER_LV1 + 1):
			result.set(17, 10);
			return result;
		case (ITowerConstants.BASIC_ARCHER_LV1 + 2):
			result.set(5, 10);
			return result;

		case ITowerConstants.ARCHER_1_LV1:
			result.set(-30, 5);
			return result;
		case (ITowerConstants.ARCHER_1_LV1 + 1):
			result.set(-5, 19);
			return result;
		case (ITowerConstants.ARCHER_1_LV1 + 2):
			result.set(-50, 25);
			return result;

		case ITowerConstants.ARCHER_2_LV1:
			result.set(-42, 0);
			return result;
		case (ITowerConstants.ARCHER_2_LV1 + 1):
			result.set(-14, 32);
			return result;
		case (ITowerConstants.ARCHER_2_LV1 + 2):
			result.set(-30, 21);
			return result;

		case ITowerConstants.BASIC_SWORD_LV1:
			result.set(-43, 0);
			return result;
		case (ITowerConstants.BASIC_SWORD_LV1 + 1):
			result.set(-36, 0);
			return result;
		case (ITowerConstants.BASIC_SWORD_LV1 + 2):
			result.set(0, 36);
			return result;

		case ITowerConstants.SWORD_1_LV1:
			result.set(-80, 47);
			return result;
		case (ITowerConstants.SWORD_1_LV1 + 1):
			result.set(-100, 52);
			return result;
		case (ITowerConstants.SWORD_1_LV1 + 2):
			result.set(-98, 92);
			return result;

		case ITowerConstants.SWORD_2_LV1:
			result.set(-44, 14);
			return result;
		case (ITowerConstants.SWORD_2_LV1 + 1):
			result.set(-85, 36);
			return result;
		case (ITowerConstants.SWORD_2_LV1 + 2):
			result.set(-45, 97);
			return result;

		case ITowerConstants.BASIC_SHAMAN_LV1:
			result.set(-23, 27);
			return result;
		case (ITowerConstants.BASIC_SHAMAN_LV1 + 1):
			result.set(-40, 55);
			return result;
		case (ITowerConstants.BASIC_SHAMAN_LV1 + 2):
			result.set(-40, 42);
			return result;

		case ITowerConstants.SHAMAN_1_LV1:
			result.set(-74, 0);
			return result;
		case (ITowerConstants.SHAMAN_1_LV1 + 1):
			result.set(-115, 0);
			return result;
		case (ITowerConstants.SHAMAN_1_LV1 + 2):
			result.set(-45, 0);
			return result;

		case ITowerConstants.SHAMAN_2_LV1:
			result.set(-116, 0);
			return result;
		case (ITowerConstants.SHAMAN_2_LV1 + 1):
			result.set(-130, 0);
			return result;
		case (ITowerConstants.SHAMAN_2_LV1 + 2):
			result.set(-50, 0);
			return result;

		case ITowerConstants.BASIC_KNIGHT_LV1:
			result.set(-36, 8);
			return result;
		case (ITowerConstants.BASIC_KNIGHT_LV1 + 1):
			result.set(-55, 0);
			return result;
		case (ITowerConstants.BASIC_KNIGHT_LV1 + 2):
			result.set(-20, 40);
			return result;

		case ITowerConstants.KNIGHT_1_LV1:
			result.set(4, 75);
			return result;
		case (ITowerConstants.KNIGHT_1_LV1 + 1):
			result.set(-90, 69);
			return result;
		case (ITowerConstants.KNIGHT_1_LV1 + 2):
			result.set(-104, 94);
			return result;

		case ITowerConstants.KNIGHT_2_LV1:
			result.set(-5, 51);
			return result;
		case (ITowerConstants.KNIGHT_2_LV1 + 1):
			result.set(-80, 90);
			return result;
		case (ITowerConstants.KNIGHT_2_LV1 + 2):
			result.set(-74, 102);
			return result;

		case ITowerConstants.SHOGUN_0:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_0 + 1):
			result.set(-140, 85);
			return result;
		case (ITowerConstants.SHOGUN_0 + 2):
			result.set(-145, 85);
			return result;

		case ITowerConstants.SHOGUN_1:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_1 + 1):
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_1 + 2):
			result.set(0, 0);
			return result;

		case ITowerConstants.SHOGUN_2:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_2 + 1):
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_2 + 2):
			result.set(0, 0);
			return result;

		case ITowerConstants.SHOGUN_3:
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_3 + 1):
			result.set(0, 0);
			return result;
		case (ITowerConstants.SHOGUN_3 + 2):
			result.set(0, 0);
			return result;

		default:
			return null;

		}
	}

	public Texture getStar() {
		return star;
	}

	public TextureRegion getLock() {
		return lock;
	}
}
