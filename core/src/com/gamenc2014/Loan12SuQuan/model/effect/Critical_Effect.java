package com.gamenc2014.Loan12SuQuan.model.effect;

import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.tower.Tower;


public class Critical_Effect extends Hit_Effect {

	private Tower tower;

	public Critical_Effect(Tower tower) {
		super();
		this.tower = tower;
		this.effectDuration = 2f;
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
		return IEffectConstants.CRITICAL_EFFECT;
	}

	public Tower getTower() {
		return tower;
	}

	public int getX() {
		return tower.getX();
	}

	public int getY() {
		return tower.getY();
	}
}
