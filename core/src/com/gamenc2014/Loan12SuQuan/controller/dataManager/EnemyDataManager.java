package com.gamenc2014.Loan12SuQuan.controller.dataManager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.ObjectAnimation;
import com.gamenc2014.Loan12SuQuan.model.enemy.IEnemyConstants;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;

public class EnemyDataManager extends AbstractDataManager {
	private ObjectAnimation[] enemy;
	private Animation goldAnimation;
	private Texture HP_bar;
	private Texture HP;
	private Texture critical;
	private Texture miss;

	public EnemyDataManager() {
	}

	@Override
	public void addToLoad(AssetManager assetManager) {
		for (int i = 0; i < 21; i++) {
			assetManager.load(EnemyDataManager.resolveID(i), Texture.class);
		}

		assetManager.load("data/enemy/HP_bar.png", Texture.class);
		assetManager.load("data/enemy/HP.png", Texture.class);
		assetManager.load("data/enemy/gold.png", Texture.class);

		assetManager.load("data/enemy/miss.png", Texture.class);
		assetManager.load("data/enemy/critical.png", Texture.class);
	}

	@Override
	public void getData(AssetManager assetManager) {
		Texture goldTexture = assetManager.get("data/enemy/gold.png",
				Texture.class);
		goldTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.goldAnimation = AbstractDataManager.getAnimationFromTexture(
				goldTexture, 56, 67);
		this.goldAnimation.setPlayMode(Animation.PlayMode.LOOP);

		this.HP_bar = assetManager.get("data/enemy/HP_bar.png", Texture.class);
		this.HP_bar.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.HP = assetManager.get("data/enemy/HP.png", Texture.class);
		this.HP.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		enemy = new ObjectAnimation[21];

		Texture temp;
		Coordinate size;

		for (int i = 0; i < enemy.length; i++) {
			temp = assetManager.get(EnemyDataManager.resolveID(i),
					Texture.class);
			temp.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			size = getSize(i);
			enemy[i] = new ObjectAnimation(i, getAnimationFromTexture(temp,
					size.getX(), size.getY()));
		}

		this.critical = assetManager.get("data/enemy/critical.png",
				Texture.class);
		this.miss = assetManager.get("data/enemy/miss.png", Texture.class);
	}

	public Animation getEnemyAnimation(int id) {
		if (id > -1 && id < enemy.length) {
			return enemy[id].getAnimation();
		} else {
			return null;
		}
	}

	public TextureRegion getKeyFrame(int id, float stateTime) {
		if (id > -1 && id < enemy.length) {
			return (TextureRegion)enemy[id].getAnimation().getKeyFrame(stateTime);
		} else {
			return null;
		}
	}

	public static String resolveID(int id) {
		return ("data/enemy/enemy_" + id + ".png");
	}

	public Texture getHP_bar() {
		return HP_bar;
	}

	public Texture getHP() {
		return HP;
	}

	public Animation getGoldAnimation() {
		return goldAnimation;
	}

	public Coordinate getSize(int ID) {
		Coordinate result = new Coordinate(0, 0);
		switch (ID) {
		case IEnemyConstants.ARMOR_NGANG:
			result.set(127, 174);
			return result;
		case IEnemyConstants.ARMOR_THANG:
			result.set(181, 152);
			return result;
		case IEnemyConstants.ARMOR_SAULUNG:
			result.set(148, 148);
			return result;

		case IEnemyConstants.FLY_NGANG:
			result.set(150, 164);
			return result;
		case IEnemyConstants.FLY_THANG:
			result.set(212, 153);
			return result;
		case IEnemyConstants.FLY_SAULUNG:
			result.set(190, 176);
			return result;

		case IEnemyConstants.NORMAL_NGANG:
			result.set(147, 138);
			return result;
		case IEnemyConstants.NORMAL_THANG:
			result.set(98, 163);
			return result;
		case IEnemyConstants.NORMAL_SAULUNG:
			result.set(97, 133);
			return result;

		case IEnemyConstants.CARGO_NGANG:
			result.set(270, 144);
			return result;
		case IEnemyConstants.CARGO_THANG:
			result.set(158, 242);
			return result;
		case IEnemyConstants.CARGO_SAULUNG:
			result.set(158, 242);
			return result;

		case IEnemyConstants.BOSS_0_NGANG:
			result.set(183, 304);
			return result;
		case IEnemyConstants.BOSS_0_THANG:
			result.set(280, 244);
			return result;
		case IEnemyConstants.BOSS_0_SAULUNG:
			result.set(252, 230);
			return result;

		case IEnemyConstants.WITCH_NGANG:
			result.set(125, 182);
			return result;
		case IEnemyConstants.WITCH_THANG:
			result.set(94, 187);
			return result;
		case IEnemyConstants.WITCH_SAULUNG:
			result.set(130, 160);
			return result;

		case IEnemyConstants.GHOST_NGANG:
			result.set(109, 158);
			return result;
		case IEnemyConstants.GHOST_THANG:
			result.set(140, 160);
			return result;
		case IEnemyConstants.GHOST_SAULUNG:
			result.set(138, 152);
			return result;

		default:
			return null;
		}
	}

	public Texture getCritical() {
		return critical;
	}

	public Texture getMiss() {
		return miss;
	}
}
