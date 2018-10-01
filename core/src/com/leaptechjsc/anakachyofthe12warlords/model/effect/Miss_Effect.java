package com.leaptechjsc.anakachyofthe12warlords.model.effect;

import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;

public class Miss_Effect extends Abstract_Effect {

	public Miss_Effect() {
		super(2f, 0);
	}

	@Override
	public void afterAct(Enemy enemy) {
	}

	@Override
	public void act(Enemy enemy) {
	}

	@Override
	public void beforeAct(Enemy enemy) {
	}

	@Override
	public int getEffectType() {
		return IEffectConstants.MISS_EFFECT;
	}
}
