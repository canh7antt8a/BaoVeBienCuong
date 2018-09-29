package com.gamenc2014.Loan12SuQuan.model.enemy;

public class EnemyData {
	private int dataID;
	private float appearTime;
	private int roadID;

	public EnemyData(int dataID, float appearTime, int roadID) {
		this.dataID = dataID;
		this.appearTime = appearTime;
		this.roadID = roadID;
	}

	public int getDataID() {
		return dataID;
	}

	public void setDataID(int dataID) {
		this.dataID = dataID;
	}

	public float getAppearTime() {
		return appearTime;
	}

	public void setAppearTime(float appearTime) {
		this.appearTime = appearTime;
	}

	public int getRoadID() {
		return roadID;
	}

	public void setRoadID(int roadID) {
		this.roadID = roadID;
	}
}
