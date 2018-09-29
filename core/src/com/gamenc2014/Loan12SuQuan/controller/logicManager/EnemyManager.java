package com.gamenc2014.Loan12SuQuan.controller.logicManager;

import com.gamenc2014.Loan12SuQuan.model.enemy.EnemyDataUnit;
import com.gamenc2014.Loan12SuQuan.model.enemy.EnumEnemyList;
import com.gamenc2014.Loan12SuQuan.model.enemy.IEnemyConstants;

public class EnemyManager {
	private EnemyDataUnit[] enemyDataList;
	private int difficulty;

	public EnemyManager(int difficulty) {
		this.difficulty = difficulty;
		this.enemyDataList = new EnemyDataUnit[IEnemyConstants.BOSS_0 + 1];
		initialize();
	}

	public void initialize() {
		int i = 0;
		for (EnumEnemyList enemy : EnumEnemyList.values()) {
			enemyDataList[i] = new EnemyDataUnit(enemy);
			configData(enemyDataList[i]);
			i++;
		}
	}

	public void configData(EnemyDataUnit enemy) {
		switch (difficulty) {
		case 0: {
			break;
		}
		case 1: {
			enemy.setHealth((int) (enemy.getHealth() * 1.25f));
			break;
		}
		case 2: {
			enemy.setHealth((int) (enemy.getHealth() * 1.5f));
			break;
		}
		case 3: {
			enemy.setHealth(enemy.getHealth() * 2);
			break;
		}
		}
	}

	public EnemyDataUnit getEnemyData(int dataID) {
		if (dataID > -1 && dataID < enemyDataList.length) {
			return enemyDataList[dataID];
		} else {
			return null;
		}
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
