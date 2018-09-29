package com.gamenc2014.Loan12SuQuan.model.effect;

import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;

public abstract class Abstract_Effect {
	protected float effectStateTime;
	protected float effectDuration;
	protected int effectPoint;
	private boolean isEnd;

	public Abstract_Effect(float duration, int effectPoint) {
		this.effectStateTime = 0;
		this.effectDuration = duration;
		this.effectPoint = effectPoint;
		this.isEnd = false;
	}

	public float getEffectStartTime() {
		return effectStateTime;
	}

	public void setEffectStartTime(float effectStartTime) {
		this.effectStateTime = effectStartTime;
	}

	public float getEffectDuration() {
		return effectDuration;
	}

	public float getRemainingDuration() {
		return Math.abs(effectDuration - effectStateTime);
	}

	public float getRemainingDurationPercent() {
		return (effectDuration - effectStateTime) / effectDuration;
	}

	public void setEffectDuration(float effectDuration) {
		this.effectDuration = effectDuration;
	}

	public int getEffectPoint() {
		return effectPoint;
	}

	public void setEffectPoint(int effectPoint) {
		this.effectPoint = effectPoint;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public void update(Enemy enemy, float delta) {
		this.effectStateTime += delta;
		if (this.effectStateTime > effectDuration) {
			this.isEnd = true;
		} else {
			this.act(enemy);
		}

	}

	public abstract void beforeAct(Enemy enemy);

	public abstract void act(Enemy enemy);

	public abstract void afterAct(Enemy enemy);

	public abstract int getEffectType();
}
