package com.gamenc2014.Loan12SuQuan.model.gameObject;

import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;

public interface IUpdateable {
	public void update(float delta, Scenario scenario);
	// delta: seconds between the current frame and the last frame (not: nano
	// second)
}
