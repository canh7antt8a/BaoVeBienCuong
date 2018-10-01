package com.leaptechjsc.anakachyofthe12warlords.model.tower;

import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ShogunData;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ShogunData;


public class ShogunTower extends Tower {
	private int auraRange;
	private int auraType;
	private float auraRate;
	private int type;

	public ShogunTower(int id, Coordinate position, ShogunData tower) {
		super(id, position, tower);
		this.auraRange = tower.getAuraRange();
		this.auraRange = tower.getAuraType();
		this.auraRate = tower.getAuraRate();
		this.type = tower.getType();
	}

	@Override
	public void addDamageFromAura(int auraType, float auraRate) {
		if (auraType == ITowerConstants.BOTH_TYPE) {
			this.damage = (int) (this.damage + (auraRate * this.damage)
					* this.auraReceive);
		} else if (type == auraType) {
			this.damage = (int) (this.damage + (auraRate * this.damage)
					* this.auraReceive);
		}
	}

	public int getAuraRange() {
		return auraRange;
	}

	public void setAuraRange(int auraRange) {
		this.auraRange = auraRange;
	}

	public int getAuraType() {
		return auraType;
	}

	public void setAuraType(int auraType) {
		this.auraType = auraType;
	}

	public float getAuraRate() {
		return auraRate;
	}

	public void setAuraRate(float auraRate) {
		this.auraRate = auraRate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
