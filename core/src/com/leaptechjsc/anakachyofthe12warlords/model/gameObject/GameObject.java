package com.leaptechjsc.anakachyofthe12warlords.model.gameObject;

import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.scenario.Scenario;


public abstract class GameObject implements IControllable, IUpdateable {

	protected int ID;
	protected int dataID;
	protected Coordinate position;
	protected float stateTime;

	public GameObject(int id, int dataID, Coordinate position) {
		this.ID = id;
		this.dataID = dataID;
		this.position = position;
		this.stateTime = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void setID(int id) {
		this.ID = id;
	}

	@Override
	public int getDataID() {
		return this.dataID;
	}

	@Override
	public void setDataID(int dataID) {
		this.dataID = dataID;
	}

	@Override
	public void update(float delta, Scenario scenario) {
		this.stateTime += delta;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public float getDistance(Coordinate position) {
		return this.position.dst(position);
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public int getX() {
		return this.position.getX();
	}

	public int getY() {
		return this.position.getY();
	}
}
