package com.gamenc2014.Loan12SuQuan.model.map;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.model.gameObject.EnumDirectionList;


public class Road {
	private int ID;
	private ArrayList<Coordinate> road;

	public Road(int ID, ArrayList<Coordinate> road) {
		this.ID = ID;
		this.road = road;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Coordinate> getRoad() {
		return road;
	}

	public Coordinate getMileStone(int mileStone) {
		if (isValidMileStone(mileStone)) {
			return road.get(mileStone);
		}
		return null;
	}

	public Coordinate getFirstMileStone() {
		return road.get(0);
	}

	public Coordinate getLastMileStone() {
		return road.get(road.size() - 1);
	}

	public boolean isValidMileStone(int mileStone) {
		if (mileStone > -1 && mileStone < road.size()) {
			return true;
		}
		return false;
	}

	public void setRoad(ArrayList<Coordinate> road) {
		this.road = road;
	}

	public int getRoadLength() {
		return this.road.size();
	}

	public EnumDirectionList getDirection(int mileStoneFrom, int mileStoneTo) {
		if (mileStoneFrom > -1 && mileStoneFrom < road.size()) {
			if (mileStoneTo > -1 && mileStoneTo < road.size()) {
				Coordinate from = road.get(mileStoneFrom);
				Coordinate to = road.get(mileStoneTo);
				if (from.getX() == to.getX()) {
					if (from.getY() < to.getY()) {
						return EnumDirectionList.DOWN_DIRECTION;
					} else {// from.y > to.y
						return EnumDirectionList.UP_DIRECTION;
					}
				} else {// if (from.getY() == to.getY()) {
					if (from.getX() < to.getX()) {
						return EnumDirectionList.RIGHT_DIRECTION;
					} else {// from.x > to.x
						return EnumDirectionList.LEFT_DIRECTION;
					}

				}
			}
		}
		return EnumDirectionList.WRONG_DIRECTION;
	}
}
