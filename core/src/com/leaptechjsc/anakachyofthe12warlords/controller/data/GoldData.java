package com.leaptechjsc.anakachyofthe12warlords.controller.data;

import com.leaptechjsc.anakachyofthe12warlords.model.gameObject.GameObject;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;


public class GoldData extends GameObject {
	private int goldNumber;
	private boolean isEnd;

	public GoldData(int id, int dataID, Coordinate position, int goldNumber) {
		super(id, dataID, position);
		this.goldNumber = goldNumber;
		this.isEnd = false;
	}

	public int getGoldNumber() {
		return goldNumber;
	}

	public boolean isEnd() {
		return isEnd;
	}

	@Override
	public void update(float delta, Scenario scenario) {
		super.update(delta, scenario);
		if (this.stateTime > 5) {
			this.isEnd = true;
		}
	}
}
