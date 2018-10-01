package com.leaptechjsc.anakachyofthe12warlords.model.magic;

import java.util.ArrayList;

import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.CurrentEnemyWave;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;
import com.leaptechjsc.anakachyofthe12warlords.model.map.BattleMap;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;


public class RainOfFireData extends MagicData {

	private ArrayList<MagicObject> rofList;
	private SoundDataManager soundManager;

	public RainOfFireData(EnumMagicList magic, int level,
			SoundDataManager soundManager) {
		super(magic, level);
		this.rofList = new ArrayList<MagicObject>();
		this.soundManager = soundManager;
	}

	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
		MagicObject rof;
		for (int i = rofList.size() - 1; i > -1; i--) {
			rof = rofList.get(i);

			rof.update(delta);
			if (rof.isEnd() == true) {
				rofList.remove(i);
			} else {
				act(scenario, rof.getCastPosition());
			}
		}

		if (rofList.isEmpty() == true) {
			soundManager.stop(SoundDataManager.ROF);
		}
	}

	@Override
	public void configData(int level) {
		switch (level) {
		case 1:
			damage = (int) (damage * 1.5f);
			break;
		case 2:
			damage = (int) (damage * 1.5f);
			duration = 5;
			break;
		case 3:
			damage = (int) (damage * 1.5f);
			duration = 5;
			cooldownTime = 20;
			break;
		case 4:
			duration = 5;
			cooldownTime = 20;
			damage = damage * 5;
			break;
		default:
			duration = 3;
			cooldownTime = 30;
			break;
		}
	}

	@Override
	public void act(Scenario scenario, Coordinate coor) {
		if (isEndOfReloadTime()) {
			this.lastDamageTime = stateTime;

			CurrentEnemyWave enemyWave = scenario.getCurrentEnemyWave();
			if (enemyWave != null) {
				ArrayList<Enemy> enemyList = enemyWave.getEnemyList();
				if (enemyList != null) {
					for (Enemy enemy : enemyList) {
						if (coor.dst(enemy.getPosition()) < areaOfEffect) {
							enemy.decreaseHealth(damage);
						}
					}
				}
			}
		}
	}

	@Override
	public void use(Scenario scenario, Coordinate castPosition) {
		BattleMap map = scenario.getBattleMap();

		if (map.isRoad(castPosition)) {
			this.controlStartTime = 0;
			this.startTime = 0;
			this.state = IMagicContants.CASTING;
			this.stateTime = 0;
			this.lastDamageTime = 0;

			rofList.add(new MagicObject(IMagicContants.RAIN_OF_FIRE, map
					.convertToPixel(castPosition), duration));
			soundManager.play(SoundDataManager.ROF, true);
		}
	}

	@Override
	public ArrayList<MagicObject> getMagicList() {
		return rofList;
	}
}
