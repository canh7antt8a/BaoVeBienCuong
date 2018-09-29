package com.gamenc2014.Loan12SuQuan.controller.dataManager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;

public class ProfileScreenDataManager extends AbstractDataManager {
	private TextureRegion downBackground;

	private TextureRegion star;
	private TextureRegion gold;

	private ButtonImage skillContent;
	private ButtonImage heroContent;
	private ButtonImage towerContent;
	private ButtonImage itemContent;

	private ButtonImage exitButton;
	private ButtonImage swipeButton;
	private ButtonImage chargeMoney;

	private TextureRegion[][] skillButton;
	private TextureRegion[][] selectedSkillButton;

	private TextureRegion[][] lockSkill;
	private TextureRegion[][] lockedSelectedSkill;

	private TextureRegion[] skillType;

	private TextureRegion[] levelButton;

	public ProfileScreenDataManager() {
	}

	public void addToLoad(AssetManager assetManager) {
		assetManager.load("data/profileScreen/downBackground.jpg",
				Texture.class);

		assetManager.load("data/profileScreen/exit.png", Texture.class);
		assetManager.load("data/profileScreen/swipeButton.png", Texture.class);
		assetManager
				.load("data/profileScreen/profileButton.png", Texture.class);

		assetManager.load("data/profileScreen/star.png", Texture.class);
		assetManager.load("data/profileScreen/gold.png", Texture.class);

		assetManager.load("data/profileScreen/skillButton.png", Texture.class);
		assetManager
				.load("data/profileScreen/selectedSkill.png", Texture.class);

		assetManager.load("data/profileScreen/lockedSkill.png", Texture.class);
		assetManager.load("data/profileScreen/lockedSelectedSkill.png",
				Texture.class);

		assetManager.load("data/profileScreen/skillType.png", Texture.class);
		assetManager.load("data/profileScreen/levelButton.png", Texture.class);
		assetManager.load("data/profileScreen/chargeMoney.png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		loadButton(assetManager.get("data/profileScreen/profileButton.png",
				Texture.class), assetManager.get("data/profileScreen/exit.png",
				Texture.class), assetManager.get(
				"data/profileScreen/swipeButton.png", Texture.class),
				assetManager.get("data/profileScreen/levelButton.png",
						Texture.class), assetManager.get(
						"data/profileScreen/chargeMoney.png", Texture.class));

		loadMoney(
				assetManager.get("data/profileScreen/star.png", Texture.class),
				assetManager.get("data/profileScreen/gold.png", Texture.class));

		loadBackground(assetManager.get(
				"data/profileScreen/downBackground.jpg", Texture.class));

		loadSkillButton(assetManager.get("data/profileScreen/skillButton.png",
				Texture.class), assetManager.get(
				"data/profileScreen/skillType.png", Texture.class),
				assetManager.get("data/profileScreen/selectedSkill.png",
						Texture.class), assetManager.get(
						"data/profileScreen/lockedSkill.png", Texture.class),
				assetManager.get("data/profileScreen/lockedSelectedSkill.png",
						Texture.class));
	}

	public void loadBackground(Texture downBackground) {
		downBackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.downBackground = new TextureRegion(downBackground);
	}

	public void loadMoney(Texture star, Texture gold) {
		star.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		gold.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.star = new TextureRegion(star);
		this.gold = new TextureRegion(gold);
	}

	public void loadButton(Texture profileButton, Texture exitButton,
			Texture swipeButton, Texture levelButton, Texture chargeMoney) {
		profileButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		exitButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		swipeButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		levelButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		chargeMoney.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion[][] tempFrame = (new TextureRegion(profileButton)).split(
				135, 94);

		this.skillContent = new ButtonImage(0, tempFrame[0][0], tempFrame[0][1]);
		this.towerContent = new ButtonImage(1, tempFrame[0][2], tempFrame[0][3]);
		this.heroContent = new ButtonImage(2, tempFrame[1][0], tempFrame[1][1]);
		this.itemContent = new ButtonImage(3, tempFrame[1][2], tempFrame[1][3]);

		tempFrame = (new TextureRegion(exitButton)).split(87, 87);
		this.exitButton = new ButtonImage(0, tempFrame[0][0], tempFrame[0][1]);

		tempFrame = (new TextureRegion(swipeButton)).split(32, 32);
		this.swipeButton = new ButtonImage(0, tempFrame[0][0], tempFrame[0][1]);

		this.levelButton = new TextureRegion[3];

		tempFrame = (new TextureRegion(levelButton)).split(115, 61);
		for (int i = 0; i < this.levelButton.length; i++) {
			this.levelButton[i] = tempFrame[0][i];
		}

		tempFrame = (new TextureRegion(chargeMoney)).split(135, 94);

		this.chargeMoney = new ButtonImage(0, tempFrame[0][0], tempFrame[0][1]);
	}

	public void loadSkillButton(Texture skillButton, Texture skillType,
			Texture selectedSkill, Texture lockedSkill,
			Texture lockedSelectedSkill) {
		skillButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		selectedSkill.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		lockedSkill.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		lockedSelectedSkill.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);

		skillType.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		this.skillButton = (new TextureRegion(skillButton)).split(97, 97);
		this.selectedSkillButton = (new TextureRegion(selectedSkill)).split(97,
				97);

		this.lockSkill = (new TextureRegion(lockedSkill)).split(97, 97);
		this.lockedSelectedSkill = (new TextureRegion(lockedSelectedSkill))
				.split(97, 97);

		this.skillType = (new TextureRegion(skillType)).split(108, 108)[0];
	}

	public TextureRegion getDownBackground() {
		return downBackground;
	}

	public TextureRegion getStar() {
		return star;
	}

	public TextureRegion getGold() {
		return gold;
	}

	public ButtonImage getSkillContent() {
		return skillContent;
	}

	public ButtonImage getHeroContent() {
		return heroContent;
	}

	public ButtonImage getTowerContent() {
		return towerContent;
	}

	public ButtonImage getItemContent() {
		return itemContent;
	}

	public ButtonImage getExitButton() {
		return exitButton;
	}

	public ButtonImage getSwipeButton() {
		return swipeButton;
	}

	public TextureRegion getLevelButton(int level) {
		if (level > -1 && level < this.levelButton.length) {
			return this.levelButton[level];
		} else {
			return null;
		}
	}

	public TextureRegion getSkillButton(int row, int col) {
		return skillButton[row][col];
	}

	public TextureRegion getSelectedSkillButton(int row, int col) {
		return selectedSkillButton[row][col];
	}

	public TextureRegion getLockedSkill(int row, int col) {
		return lockSkill[row][col];
	}

	public TextureRegion getLockedSelectedSkill(int row, int col) {
		return lockedSelectedSkill[row][col];
	}

	public TextureRegion getSkillType(int numberID) {
		return skillType[numberID];
	}

	public ButtonImage getChargeMoney() {
		return chargeMoney;
	}
}
