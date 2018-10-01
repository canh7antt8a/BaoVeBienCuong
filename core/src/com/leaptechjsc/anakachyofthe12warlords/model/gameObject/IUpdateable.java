package com.leaptechjsc.anakachyofthe12warlords.model.gameObject;

import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;

public interface IUpdateable {
	public void update(float delta, Scenario scenario);
	// delta: seconds between the current frame and the last frame (not: nano
	// second)
}
