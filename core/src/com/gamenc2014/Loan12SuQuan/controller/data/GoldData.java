package com.gamenc2014.Loan12SuQuan.controller.data;

import com.gamenc2014.Loan12SuQuan.model.gameObject.GameObject;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;


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
