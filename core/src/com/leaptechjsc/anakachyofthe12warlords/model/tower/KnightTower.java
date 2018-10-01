package com.leaptechjsc.anakachyofthe12warlords.model.tower;

import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.TowerData;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.TowerData;


public class KnightTower extends Tower {

	public KnightTower(int id, Coordinate position, TowerData towerData) {
		super(id, position, towerData);
	}

	@Override
	public void attack(Enemy enemy, Scenario scenario) {
		super.attack(enemy, scenario);
		soundManager.play(SoundDataManager.TWO_HANDED, false);
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
