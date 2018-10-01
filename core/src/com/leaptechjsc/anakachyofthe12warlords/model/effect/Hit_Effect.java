package com.leaptechjsc.anakachyofthe12warlords.model.effect;

import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;

public class Hit_Effect extends Abstract_Effect {
	
	public Hit_Effect() {
		super(0.5f, 0);
	}

	@Override
	public void beforeAct(Enemy enemy) {
	}

	@Override
	public void act(Enemy enemy) {
	}

	@Override
	public void afterAct(Enemy enemy) {
	}

	@Override
	public int getEffectType() {
		return IEffectConstants.HIT_EFFECT;
	}
}
