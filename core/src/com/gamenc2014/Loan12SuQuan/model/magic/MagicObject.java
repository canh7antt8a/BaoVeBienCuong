package com.gamenc2014.Loan12SuQuan.model.magic;

import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;

public class MagicObject {
	private int dataID;
	private float stateTime;
	private float duration;
	private Coordinate castPosition;
	private boolean isEnd;

	public MagicObject(int dataID, Coordinate castPosition, float duration) {
		this.dataID = dataID;
		this.castPosition = castPosition;
		this.stateTime = 0;
		this.duration = duration;
		this.isEnd = false;
	}

	public void update(float delta) {
		this.stateTime += delta;
		if (this.stateTime > duration) {
			this.isEnd = true;
		}
	}

	public int getDataID() {
		return dataID;
	}

	public void setDataID(int dataID) {
		this.dataID = dataID;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public Coordinate getCastPosition() {
		return castPosition;
	}

	public void setCastPosition(Coordinate castPosition) {
		this.castPosition = castPosition;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
}