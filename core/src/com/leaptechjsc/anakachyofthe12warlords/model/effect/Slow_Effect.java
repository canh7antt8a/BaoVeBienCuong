package com.leaptechjsc.anakachyofthe12warlords.model.effect;

import com.leaptechjsc.anakachyofthe12warlords.model.enemy.Enemy;

public class Slow_Effect extends Abstract_Effect {

	public float slowRate;
	public int initialSpeed;

	public Slow_Effect(float duration, int effectPoint, float slowRate) {
		super(duration, effectPoint);
		this.slowRate = slowRate;
	}

	@Override
	public void beforeAct(Enemy enemy) {
		initialSpeed = enemy.getSpeed();
		enemy.setSpeed((int) (initialSpeed * slowRate));
	}

	@Override
	public void act(Enemy enemy) {
	}

	@Override
	public void afterAct(Enemy enemy) {
		enemy.setSpeed(initialSpeed);
	}

	@Override
	public int getEffectType() {
		return IEffectConstants.SLOW_EFFECT;
	}
}
