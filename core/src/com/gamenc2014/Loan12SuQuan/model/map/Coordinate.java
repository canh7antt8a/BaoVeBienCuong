package com.gamenc2014.Loan12SuQuan.model.map;

import com.gamenc2014.Loan12SuQuan.model.towerData.ITowerConstants;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void set(Coordinate coor) {
		this.x = coor.getX();
		this.y = coor.getY();
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public float dst(Coordinate coor) {
		float delta_1 = coor.getX() - x;
		float delta_2 = coor.getY() - y;

		return (float) Math.sqrt(delta_1 * delta_1 + delta_2 * delta_2);
	}

	public float dst(int x1, int y1) {
		float delta_1 = x1 - x;
		float delta_2 = y1 - y;

		return (float) Math.sqrt(delta_1 * delta_1 + delta_2 * delta_2);
	}

	public float dst2(Coordinate coor) {
		float delta_1 = coor.getX() - x;
		float delta_2 = coor.getY() - y;

		return (delta_1 * delta_1 + delta_2 * delta_2);
	}

	public float dst2(int x1, int y1) {
		float delta_1 = x1 - x;
		float delta_2 = y1 - y;

		return (delta_1 * delta_1 + delta_2 * delta_2);
	}

	public void addX(int delta) {
		this.x += delta;
	}

	public void addY(int delta) {
		this.y += delta;
	}

	public boolean isEqual(Coordinate coor) {
		if (x == coor.getX()) {
			if (y == coor.getY()) {
				return true;
			}
		}

		return false;
	}

	public int getDirection(int locationX, int locationY) {
		boolean isLeft = false, isTop = false;

		if (x > locationX) {
			isLeft = true;
		}

		if (y > locationY) {
			isTop = true;
		}

		if (isLeft == true) {
			if (isTop == true) {
				return ITowerConstants.TOP_LEFT;
			} else {
				return ITowerConstants.BOTTOM_LEFT;
			}
		} else {// if(isLeft==false)
			if (isTop == true) {
				return ITowerConstants.TOP_RIGHT;
			} else {
				return ITowerConstants.BOTTOM_RIGHT;
			}
		}
	}
}
