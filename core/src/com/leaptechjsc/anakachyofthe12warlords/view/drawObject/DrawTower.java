package com.leaptechjsc.anakachyofthe12warlords.view.drawObject;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.TowerDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.Tower;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.dataManager.TowerDataManager;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.ICommonConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.Tower;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class DrawTower extends DrawHelper {
	private ArrayList<Tower> towerList;
	private TowerDataManager towerDataManager;

	private Coordinate size;

	public DrawTower(TowerDataManager towerDataManager,
			ArrayList<Tower> towerList, SpriteBatch batch, GameLogic gameLogic) {
		super(batch, gameLogic);
		this.towerDataManager = towerDataManager;
		this.towerList = towerList;
	}

	public void draw(boolean isUIupgradeClose) {
		TextureRegion temp;
		Tower selectedTower;
		int x, y, level;

		if (isUIupgradeClose == false) {
			selectedTower = gameLogic.getSelectedTower();
		} else {
			selectedTower = null;
		}

		if (selectedTower != null) {
			for (Tower tower : towerList) {
				level = tower.getLevel();

				if (tower == selectedTower) {
					batch.setColor(1, 1, 1, 1);
				} else {
					batch.setColor(1, 1, 1, 0.5f);
				}

				if (tower.getState() == ITowerConstants.WAITING) {
					temp = towerDataManager.getKeyFrame(tower.getDrawID(),
							tower.getStateTime());

					size = TowerDataManager.getTowerPosition(tower.getDrawID());

					x = tower.getX() + size.getX();
					y = PlayGameScreen.tempValue - (tower.getY() + size.getY());

					batch.draw(temp, x, y);

				} else if (tower.getState() == ITowerConstants.ATTACKING) {
					switch (tower.getDirection()) {
					case ITowerConstants.TOP_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, tower.getStateTime());

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.TOP_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, tower.getStateTime());

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					case ITowerConstants.BOTTOM_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, tower.getStateTime());

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.BOTTOM_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, tower.getStateTime());

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					default:
						temp = towerDataManager.getKeyFrame(tower.getDrawID(),
								tower.getStateTime());

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID());

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					}
				} else {
					switch (tower.getDirection()) {
					case ITowerConstants.TOP_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, 0f);

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.TOP_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, 0f);

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					case ITowerConstants.BOTTOM_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, 0f);

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.BOTTOM_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, 0f);

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					default:
						temp = towerDataManager.getKeyFrame(tower.getDrawID(),
								0f);

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID());

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					}
				}
				drawLevel(batch, x + ICommonConstants.DEFAULT_TILE_SIZE, y
						+ ICommonConstants.DEFAULT_TILE_SIZE, level);
			}
		} else {

			for (Tower tower : towerList) {
				level = tower.getLevel();

				if (tower.getState() == ITowerConstants.WAITING) {
					temp = towerDataManager.getKeyFrame(tower.getDrawID(),
							tower.getStateTime());

					size = TowerDataManager.getTowerPosition(tower.getDrawID());

					x = tower.getX() + size.getX();
					y = PlayGameScreen.tempValue - (tower.getY() + size.getY());

					batch.draw(temp, x, y);

				} else if (tower.getState() == ITowerConstants.ATTACKING) {
					switch (tower.getDirection()) {
					case ITowerConstants.TOP_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, tower.getStateTime());

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.TOP_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, tower.getStateTime());

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					case ITowerConstants.BOTTOM_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, tower.getStateTime());

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.BOTTOM_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, tower.getStateTime());

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					default:
						temp = towerDataManager.getKeyFrame(tower.getDrawID(),
								tower.getStateTime());

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID());

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;
					}
				} else {
					switch (tower.getDirection()) {
					case ITowerConstants.TOP_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, 0f);

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.TOP_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 1, 0f);

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 1);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					case ITowerConstants.BOTTOM_LEFT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, 0f);

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;

					case ITowerConstants.BOTTOM_RIGHT:
						temp = towerDataManager.getKeyFrame(
								tower.getDrawID() + 2, 0f);

						size = TowerDataManager.getTowerFlipPosition(tower
								.getDrawID() + 2);

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						temp.flip(true, false);
						batch.draw(temp, x, y);
						temp.flip(true, false);
						break;

					default:
						temp = towerDataManager.getKeyFrame(tower.getDrawID(),
								0f);

						size = TowerDataManager.getTowerPosition(tower
								.getDrawID());

						x = tower.getX() + size.getX();
						y = PlayGameScreen.tempValue
								- (tower.getY() + size.getY());

						batch.draw(temp, x, y);
						break;
					}
				}
				drawLevel(batch, x + ICommonConstants.DEFAULT_TILE_SIZE, y
						+ ICommonConstants.DEFAULT_TILE_SIZE, level);
			}
		}
	}

	public void drawLevel(SpriteBatch batch, int x, int y, int level) {
		for (int i = 0; i < level; i++) {
			batch.draw(towerDataManager.getStar(), x + i * 16, y);
		}
	}
}
