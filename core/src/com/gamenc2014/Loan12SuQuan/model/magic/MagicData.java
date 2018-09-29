package com.gamenc2014.Loan12SuQuan.model.magic;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.controller.data.EffectData;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;


public abstract class MagicData {

	protected int damage;
	protected int areaOfEffect;
	protected float castTime;
	protected float duration;
	protected float cooldownTime;
	protected float damageSpeed;
	//
	protected float stateTime;
	protected float startTime;
	protected float controlStartTime;
	protected int state;
	protected float lastDamageTime;
	//
	public static ArrayList<EffectData> effectList;

	public MagicData(EnumMagicList magic, int level) {
		this.damage = magic.getDamage();
		this.areaOfEffect = magic.getAreaOfEffect();
		this.castTime = magic.getCastTime();
		this.duration = magic.getDuration();
		this.cooldownTime = magic.getCooldownTime();
		this.damageSpeed = magic.getDamageSpeed();

		this.startTime = cooldownTime;
		this.lastDamageTime = 0;
		this.stateTime = 0;
		this.state = IMagicContants.WAITING;
		this.controlStartTime = 0;
		effectList = new ArrayList<EffectData>();

		configData(level);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAreaOfEffect() {
		return areaOfEffect;
	}

	public void setAreaOfEffect(int areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}

	public float getCastTime() {
		return castTime;
	}

	public void setCastTime(float castTime) {
		this.castTime = castTime;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public float getCooldownPercent() {
		if (startTime >= cooldownTime) {
			return 1;
		} else {
			return startTime / cooldownTime;
		}
	}

	public float getCooldownTime() {
		return cooldownTime;
	}

	public void setCooldownTime(float cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public float getControlStartTime() {
		return controlStartTime;
	}

	public void setControlStartTime(float controlStartTime) {
		this.controlStartTime = controlStartTime;
	}

	public float getLastDamageTime() {
		return lastDamageTime;
	}

	public void setLastDamageTime(float lastDamageTime) {
		this.lastDamageTime = lastDamageTime;
	}

	public float getDamageSpeed() {
		return damageSpeed;
	}

	public boolean isEndOfReloadTime() {
		float reloadTime = 1f / damageSpeed;
		if ((stateTime - lastDamageTime) < reloadTime) {
			return false;
		} else {
			return true;
		}
	}

	public void setDamageSpeed(float damageSpeed) {
		this.damageSpeed = damageSpeed;
	}

	public float getStartTime() {
		return startTime;
	}

	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public void update(float delta, Scenario scenario) {
		if (this.state == IMagicContants.CASTING) {
			this.controlStartTime += delta;
			if (this.controlStartTime > castTime) {
				this.state = IMagicContants.ACTIVATING;
				this.controlStartTime = 0;
			}
		} else if (this.state == IMagicContants.ACTIVATING) {
			this.controlStartTime += delta;
			if (this.controlStartTime > duration) {
				this.state = IMagicContants.WAITING;
				this.controlStartTime = 0;
			}
		}

		this.startTime += delta;
		this.stateTime += delta;
	}

	public abstract void configData(int level);

	public abstract void act(Scenario scenario, Coordinate coor);

	public abstract void use(Scenario scenario, Coordinate castPosition);

	public abstract ArrayList<MagicObject> getMagicList();
}