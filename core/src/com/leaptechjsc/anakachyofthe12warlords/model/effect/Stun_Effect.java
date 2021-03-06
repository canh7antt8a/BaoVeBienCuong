package com.leaptechjsc.anakachyofthe12warlords.model.effect;

import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;

public class Stun_Effect extends Abstract_Effect {

	public int initialSpeed;

	public Stun_Effect(float duration, int effectPoint) {
		super(duration, effectPoint);
	}

	@Override
	public void beforeAct(Enemy enemy) {
		this.initialSpeed = enemy.getSpeed();
		enemy.setSpeed(0);
	}

	@Override
	public void act(Enemy enemy) {
	}

	@Override
	public void afterAct(Enemy enemy) {
		enemy.setSpeed(this.initialSpeed);
	}

	@Override
	public int getEffectType() {
		return IEffectConstants.STUN_EFFECT;
	}
}
