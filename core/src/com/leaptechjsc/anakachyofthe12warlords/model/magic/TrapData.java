package com.leaptechjsc.anakachyofthe12warlords.model.magic;

import java.util.ArrayList;

import com.leaptechjsc.anakachyofthe12warlords.controller.data.EffectData;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.effect.Stun_Effect;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;
import com.leaptechjsc.anakachyofthe12warlords.controller.data.EffectData;
import com.leaptechjsc.anakachyofthe12warlords.controller.soundManager.SoundDataManager;
import com.leaptechjsc.anakachyofthe12warlords.model.effect.Stun_Effect;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.CurrentEnemyWave;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;
import com.leaptechjsc.anakachyofthe12warlords.model.map.BattleMap;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;


public class TrapData extends MagicData {

	private int numberTrap;
	private int coldTime;
	private ArrayList<MagicObject> trapList;
	private boolean isActivated;
	private SoundDataManager soundManager;

	public TrapData(EnumMagicList magic, int level,
			SoundDataManager soundManager) {
		super(magic, level);
		this.trapList = new ArrayList<MagicObject>();
		this.isActivated = false;
		this.soundManager = soundManager;
	}

	public int getNumberTrap() {
		return numberTrap;
	}

	public void setNumberTrap(int numberTrap) {
		this.numberTrap = numberTrap;
	}

	public int getColdTime() {
		return coldTime;
	}

	public void setColdTime(int coldTime) {
		this.coldTime = coldTime;
	}

	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
		MagicObject trap;
		for (int i = trapList.size() - 1; i > -1; i--) {
			trap = trapList.get(i);

			trap.update(delta);
			if (trap.isEnd() == true) {
				trapList.remove(i);
			} else {
				act(scenario, trap.getCastPosition());
				if (isActivated == true) {
					isActivated = false;
					trapList.remove(i);
				}
			}
		}
	}

	@Override
	public void configData(int level) {
		switch (level) {
		case 1:
			numberTrap = 5;
			coldTime = 2;
			break;
		case 2:
			numberTrap = 8;
			coldTime = 2;
			break;
		case 3:
			numberTrap = 8;
			coldTime = 5;
			break;
		case 4:
			numberTrap = 8;
			coldTime = 5;
			damage = damage * 5;
			break;
		default:
			numberTrap = 3;
			coldTime = 2;
			break;
		}
	}

	@Override
	public void act(Scenario scenario, Coordinate coor) {
		CurrentEnemyWave enemyWave = scenario.getCurrentEnemyWave();
		if (enemyWave != null) {
			ArrayList<Enemy> enemyList = enemyWave.getEnemyList();
			if (enemyList != null) {
				for (Enemy enemy : enemyList) {
					if (enemy.isFlyUnit() == false) {
						if (coor.dst(enemy.getPosition()) < areaOfEffect) {
							enemy.decreaseHealth(damage);
							enemy.addEffect(new Stun_Effect(coldTime, 100));
							isActivated = true;

							effectList.add(new EffectData(
									IMagicContants.TRAP_EFFECT,
									IMagicContants.TRAP_EFFECT, coor, 0.6f));
							soundManager.play(SoundDataManager.TRAP_EFFECT,
									false);
							return;
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
			Coordinate coor = map.convertToPixel(castPosition);
			for (MagicObject object : trapList) {
				if (object.getCastPosition().isEqual(coor)) {
					return;
				}
			}

			this.controlStartTime = 0;
			this.startTime = 0;
			this.state = IMagicContants.CASTING;
			this.stateTime = 0;
			this.lastDamageTime = 0;
			this.numberTrap--;

			trapList.add(new MagicObject(IMagicContants.TRAP, coor, duration));
			soundManager.play(SoundDataManager.TRAP, false);
		}
	}

	@Override
	public ArrayList<MagicObject> getMagicList() {
		return trapList;
	}
}
