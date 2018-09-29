package com.gamenc2014.Loan12SuQuan.model.enemy;

import java.util.ArrayList;

public class EnemyWave {
	private int waveID;
	private ArrayList<EnemyData> enemyDataList;
	private float waveAppearTime;

	public EnemyWave(int ID, ArrayList<EnemyData> enemyDataList,
			float waveAppearTime) {
		this.waveID = ID;
		this.enemyDataList = enemyDataList;
		this.waveAppearTime = waveAppearTime;
	}

	public int getID() {
		return waveID;
	}

	public void setID(int iD) {
		waveID = iD;
	}

	public boolean isValidEnemyID(int enemyID) {
		if (enemyID > -1 && enemyID < enemyDataList.size()) {
			return true;
		}
		return false;
	}

	public int getSize() {
		return enemyDataList.size();
	}

	public ArrayList<EnemyData> getEnemyDataList() {
		return enemyDataList;
	}

	public void setEnemyIDList(ArrayList<EnemyData> enemyDataList) {
		this.enemyDataList = enemyDataList;
	}

	public float getWaveAppearTime() {
		return waveAppearTime;
	}

	public void setWaveAppearTime(float waveAppearTime) {
		this.waveAppearTime = waveAppearTime;
	}

	public void addWaveAppearTime(float time) {
		this.waveAppearTime += time;
	}
}
