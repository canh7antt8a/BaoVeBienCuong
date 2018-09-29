package com.gamenc2014.Loan12SuQuan.model.tower;

import com.gamenc2014.Loan12SuQuan.controller.soundManager.SoundDataManager;
import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;
import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;
import com.gamenc2014.Loan12SuQuan.model.towerData.TowerData;


public class SwordTower extends Tower {

	public SwordTower(int id, Coordinate position, TowerData towerData) {
		super(id, position, towerData);
	}

	@Override
	public void attack(Enemy enemy, Scenario scenario) {
		super.attack(enemy, scenario);
		soundManager.play(SoundDataManager.ONE_HANDED, false);
	}

	@Override
	public void addDamageFromAura(int auraType, float auraRate) {
		switch (auraType) {
		case ITowerConstants.MELEE_TYPE:
			this.damage = (int) (this.damage + (auraRate * this.damage)
					* this.auraReceive);
			break;
		case ITowerConstants.RANGE_TYPE:
			break;
		case ITowerConstants.BOTH_TYPE:
			this.damage = (int) (this.damage + (auraRate * this.damage)
					* this.auraReceive);
			break;
		}
	}
}
