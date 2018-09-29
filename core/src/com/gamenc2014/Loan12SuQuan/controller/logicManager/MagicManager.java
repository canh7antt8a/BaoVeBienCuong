package com.gamenc2014.Loan12SuQuan.controller.logicManager;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.controller.data.EffectData;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.SoundDataManager;
import com.gamenc2014.Loan12SuQuan.model.magic.EnumMagicList;
import com.gamenc2014.Loan12SuQuan.model.magic.IMagicContants;
import com.gamenc2014.Loan12SuQuan.model.magic.MagicData;
import com.gamenc2014.Loan12SuQuan.model.magic.MagicObject;
import com.gamenc2014.Loan12SuQuan.model.magic.RainOfFireData;
import com.gamenc2014.Loan12SuQuan.model.magic.TrapData;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;


public class MagicManager {
	private TrapData trapData;
	private RainOfFireData rainOfFireData;

	public MagicManager(int level, SoundDataManager soundManger) {
		this.trapData = new TrapData(
				EnumMagicList.getMagicData(IMagicContants.TRAP), level,
				soundManger);
		this.rainOfFireData = new RainOfFireData(
				EnumMagicList.getMagicData(IMagicContants.RAIN_OF_FIRE), level,
				soundManger);
	}

	public void useMagic(Scenario scenario, Coordinate castPosition, int dataID) {
		switch (dataID) {
		case IMagicContants.TRAP:
			trapData.use(scenario, castPosition);
			break;
		case IMagicContants.RAIN_OF_FIRE:
			rainOfFireData.use(scenario, castPosition);
			break;
		}
	}

	public void update(float delta, Scenario scenario) {
		trapData.update(delta, scenario);
		rainOfFireData.update(delta, scenario);

		EffectData effectData;
		for (int i = MagicData.effectList.size() - 1; i > -1; i--) {
			effectData = MagicData.effectList.get(i);
			effectData.update(delta, scenario);
			if (effectData.isEnd() == true) {
				MagicData.effectList.remove(i);
			}
		}
	}

	public TrapData getTrapData() {
		return trapData;
	}

	public ArrayList<MagicObject> getTrapList() {
		return trapData.getMagicList();
	}

	public float getTrapCooldownPercent() {
		return trapData.getCooldownPercent();
	}

	public int getNumberTrap() {
		return trapData.getNumberTrap();
	}

	public RainOfFireData getRainOfFireData() {
		return rainOfFireData;
	}

	public ArrayList<MagicObject> getROFList() {
		return rainOfFireData.getMagicList();
	}

	public float getROFCooldownPercent() {
		return rainOfFireData.getCooldownPercent();
	}

	public float getROFCooldown() {
		return rainOfFireData.getCooldownTime();
	}
}
