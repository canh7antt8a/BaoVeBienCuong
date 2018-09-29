package com.gamenc2014.Loan12SuQuan.view.drawObject;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.EnemyDataManager;
import com.gamenc2014.Loan12SuQuan.controller.dataManager.MapDataManager;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.GameLogic;
import com.gamenc2014.Loan12SuQuan.model.effect.Abstract_Effect;
import com.gamenc2014.Loan12SuQuan.model.effect.Critical_Effect;
import com.gamenc2014.Loan12SuQuan.model.effect.IEffectConstants;
import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.gameObject.EnumDirectionList;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.view.screen.PlayGameScreen;

public class DrawEnemy extends DrawHelper {
	private ArrayList<Enemy> enemyList;
	private EnemyDataManager enemyDataManager;
	private Texture hp;
	private Texture hp_bar;
	private Texture miss;
	private Texture critical;
	private int mapID;
	private Vector2 chokePosition;
	private Vector2 spawnPosition;

	public DrawEnemy(EnemyDataManager enemyDataManager, SpriteBatch batch,
			GameLogic gameLogic) {
		super(batch, gameLogic);
		this.enemyDataManager = enemyDataManager;

		this.hp = enemyDataManager.getHP();
		this.hp_bar = enemyDataManager.getHP_bar();
		this.miss = enemyDataManager.getMiss();
		this.critical = enemyDataManager.getCritical();

		this.enemyList = new ArrayList<Enemy>();
		this.mapID = gameLogic.getMapID();

		if (mapID == MapDataManager.MAP_5) {
			chokePosition = new Vector2(
					13 * ICommonConstants.DEFAULT_TILE_SIZE,
					10 * ICommonConstants.DEFAULT_TILE_SIZE);
			spawnPosition = new Vector2(
					11 * ICommonConstants.DEFAULT_TILE_SIZE,
					10 * ICommonConstants.DEFAULT_TILE_SIZE);
		} else if (mapID == MapDataManager.MAP_7) {
			chokePosition = new Vector2(
					4 * ICommonConstants.DEFAULT_TILE_SIZE,
					3 * ICommonConstants.DEFAULT_TILE_SIZE);
			spawnPosition = new Vector2(
					4 * ICommonConstants.DEFAULT_TILE_SIZE,
					5 * ICommonConstants.DEFAULT_TILE_SIZE);
		}
	}

	public void draw() {
		int tempX = (hp_bar.getWidth() - ICommonConstants.DEFAULT_TILE_SIZE) / 2;
		int x, y;
		Color color = batch.getColor();
		Abstract_Effect tempEffect;

		for (Enemy enemy : enemyList) {

			x = enemy.getX();
			y = enemy.getY();

			if (mapID == MapDataManager.MAP_5) {
				if (y == chokePosition.y) {
					if (x < chokePosition.x && x > spawnPosition.x) {
						continue;
					}
				}
			} else if (mapID == MapDataManager.MAP_7) {
				if (x == chokePosition.x) {
					if (y > chokePosition.y && y < spawnPosition.y) {
						continue;
					}
				}
			}

			if (enemy.findEffect(IEffectConstants.SLOW_EFFECT) != null
					|| enemy.findEffect(IEffectConstants.STUN_EFFECT) != null) {
				batch.setColor(0, 0, 1, 1);
			} else if (enemy.findEffect(IEffectConstants.HIT_EFFECT) != null) {
				batch.setColor(1, 0, 0, 1);
			}

			if (enemy.getDirection() == EnumDirectionList.LEFT_DIRECTION) {
				TextureRegion temp = enemyDataManager.getKeyFrame(
						enemy.getStateID(), enemy.getStateTime());

				temp.flip(true, false);
				batch.draw(
						temp,
						x
								- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1),
						PlayGameScreen.tempValue
								- y
								- ((temp.getRegionHeight() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1));
				batch.draw(hp_bar, x - tempX, PlayGameScreen.tempValue - y
						+ ICommonConstants.DEFAULT_TILE_SIZE);

				batch.draw(hp, x - tempX, PlayGameScreen.tempValue - y
						+ ICommonConstants.DEFAULT_TILE_SIZE,
						enemy.getHealthPercent(), hp.getHeight());

				temp.flip(true, false);

			} else {
				TextureRegion temp = enemyDataManager.getKeyFrame(
						enemy.getStateID(), enemy.getStateTime());

				batch.draw(
						temp,
						x
								- ((temp.getRegionWidth() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1),
						PlayGameScreen.tempValue
								- y
								- ((temp.getRegionHeight() - ICommonConstants.DEFAULT_TILE_SIZE) >> 1));
				batch.draw(hp_bar, x - tempX, PlayGameScreen.tempValue - y
						+ ICommonConstants.DEFAULT_TILE_SIZE);

				batch.draw(hp, x - tempX, PlayGameScreen.tempValue - y
						+ ICommonConstants.DEFAULT_TILE_SIZE,
						enemy.getHealthPercent(), hp.getHeight());
			}

			tempEffect = enemy.findEffect(IEffectConstants.MISS_EFFECT);
			if (tempEffect != null) {
				batch.setColor(1, 1, 1,
						tempEffect.getRemainingDurationPercent());
				batch.draw(miss, x + 20, PlayGameScreen.tempValue - y + 64);
			}

			tempEffect = enemy.findEffect(IEffectConstants.CRITICAL_EFFECT);
			if (tempEffect != null) {
				batch.setColor(1, 1, 1,
						tempEffect.getRemainingDurationPercent());
				batch.draw(critical,
						((Critical_Effect) tempEffect).getX() + 10,
						PlayGameScreen.tempValue
								- ((Critical_Effect) tempEffect).getY()
								+ ICommonConstants.DEFAULT_TILE_SIZE);
			}

			batch.setColor(color);
		}
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
}
