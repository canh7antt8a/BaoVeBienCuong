package com.leaptechjsc.anakachyofthe12warlords.controller.data;

import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.GameObject;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;


public class EffectData extends GameObject {

	private boolean isEnd;
	private float duration;

	public EffectData(int id, int dataID, Coordinate position, float duration) {
		super(id, dataID, position);
		this.isEnd = false;
		this.duration = duration;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public float getDuration() {
		return duration;
	}

	@Override
	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
		if (this.stateTime > duration) {
			this.isEnd = true;
		}
	}
}
