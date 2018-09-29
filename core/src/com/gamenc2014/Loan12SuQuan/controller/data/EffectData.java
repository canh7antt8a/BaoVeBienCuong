package com.gamenc2014.Loan12SuQuan.controller.data;

import com.gamenc2014.Loan12SuQuan.model.gameObject.GameObject;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;


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
