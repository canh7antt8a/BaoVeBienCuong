package com.gamenc2014.Loan12SuQuan.model.map;

import java.util.ArrayList;

import com.gamenc2014.Loan12SuQuan.model.enemy.Enemy;
import com.gamenc2014.Loan12SuQuan.model.gameObject.EnumDirectionList;


public class BattleMap {
	private int ID;
	private int[][] battleMap;
	private ArrayList<Road> enemyRoadList;

	public BattleMap(int ID, int[][] battleMap, ArrayList<Road> enemyRoadList) {
		this.ID = ID;
		this.battleMap = battleMap;
		this.enemyRoadList = enemyRoadList;
	}

	public int getNumberColumns() {
		return battleMap[0].length;
	}

	public int getNumberRows() {
		return battleMap.length;
	}

	public int[][] getBattleMap() {
		return battleMap;
	}

	public void setBattleMap(int[][] battleMap) {
		this.battleMap = battleMap;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isValidCoordinate(int coordinateX, int coordinateY) {
		if (coordinateY > -1 && coordinateY < getNumberRows()) {
			if (coordinateX > -1 && coordinateX < getNumberColumns()) {
				return true;
			}
		}
		return false;
	}

	public boolean isStrategyPoint(int coordinateX, int coordinateY) {
		if (isValidCoordinate(coordinateX, coordinateY)) {
			if (battleMap[coordinateY][coordinateX] == IMapConstants.POINT) {
				return true;
			}
		}
		return false;
	}

	public boolean isStrategyPoint(Coordinate coor) {
		int x = coor.getX();
		int y = coor.getY();

		if (isValidCoordinate(x, y)) {
			if (battleMap[y][x] == IMapConstants.POINT) {
				return true;
			}
		}
		return false;
	}

	public boolean isRoad(int coordinateX, int coordinateY) {
		if (isValidCoordinate(coordinateX, coordinateY)) {
			if (battleMap[coordinateY][coordinateX] == IMapConstants.ROAD) {
				return true;
			}
		}
		return false;
	}

	public boolean isRoad(Coordinate coor) {
		int x = coor.getX();
		int y = coor.getY();

		if (isValidCoordinate(x, y)) {
			if (battleMap[y][x] == IMapConstants.ROAD) {
				return true;
			}
		}
		return false;
	}

	public boolean setMapObject(int coordinateX, int coordinateY, int mapObject) {
		if (isValidCoordinate(coordinateX, coordinateY)) {
			battleMap[coordinateY][coordinateX] = mapObject;
			return true;
		} else {
			return false;
		}
	}

	public int getMapObject(int coordinateX, int coordinateY) {
		if (isValidCoordinate(coordinateX, coordinateY)) {
			return battleMap[coordinateY][coordinateX];
		} else {
			return 0;
		}
	}

	public ArrayList<Road> getEnemyRoadList() {
		return enemyRoadList;
	}

	public void setEnemyRoadList(ArrayList<Road> enemyRoadList) {
		this.enemyRoadList = enemyRoadList;
	}

	public boolean isValidRoadID(int roadID) {
		if (roadID > -1 && roadID < enemyRoadList.size()) {
			return true;
		} else {
			return false;
		}
	}

	public Road getEnemyRoad(int roadID) {
		if (isValidRoadID(roadID)) {
			return enemyRoadList.get(roadID);
		} else {
			return null;
		}
	}

	public boolean isValidMileStone(int roadID, int mileStone) {
		if (isValidRoadID(roadID)) {
			ArrayList<Coordinate> road = enemyRoadList.get(roadID).getRoad();
			if (mileStone > -1 && mileStone < road.size()) {
				return true;
			}
		}
		return false;
	}

	public Coordinate getEnemyMileStone(int roadID, int mileStone) {
		if (isValidRoadID(roadID)) {
			ArrayList<Coordinate> road = enemyRoadList.get(roadID).getRoad();
			if (mileStone > -1 && mileStone < road.size()) {
				return road.get(mileStone);
			}
		}
		return null;
	}

	public Coordinate convertToPixel(Coordinate coor) {
		return new Coordinate(coor.getX() << 7, coor.getY() << 7);
	}

	public Coordinate convertToCell(Coordinate coor) {
		return new Coordinate(coor.getX() >> 7, coor.getY() >> 7);
	}

	public boolean isReachMileStone(int roadID, Enemy enemy) {
		Coordinate coor = getEnemyMileStone(roadID, enemy.getCurrentMileStone());
		if (coor != null) {
			if (enemy != null) {
				EnumDirectionList direction = enemy.getDirection();

				switch (direction) {
				case UP_DIRECTION:
					if (enemy.getX() == coor.getX()) {
						if (enemy.getY() <= coor.getY()) {
							return true;
						}
					}
					return false;
				case DOWN_DIRECTION:
					if (enemy.getX() == coor.getX()) {
						if (enemy.getY() >= coor.getY()) {
							return true;
						}
					}
					return false;
				case LEFT_DIRECTION:
					if (enemy.getY() == coor.getY()) {
						if (enemy.getX() <= coor.getX()) {
							return true;
						}
					}
					return false;
				case RIGHT_DIRECTION:
					if (enemy.getY() == coor.getY()) {
						if (enemy.getX() >= coor.getX()) {
							return true;
						}
					}
					return false;
				default:
					break;
				}
				return false;
			}
		}
		return false;
	}

	public int getDistance(int coordinateX_1, int coordinateY_1,
			int coordinateX_2, int coordinateY_2) {
		if (isValidCoordinate(coordinateX_1, coordinateY_1)) {
			if (isValidCoordinate(coordinateX_2, coordinateY_2)) {
				int temp1 = coordinateX_1 - coordinateX_2;
				int temp2 = coordinateY_1 - coordinateY_2;
				return (int) Math.sqrt(temp1 * temp1 + temp2 * temp2);
			}
		}
		return 0;
	}

	private int getCoordinateDistance(int coordinateX_1, int coordinateY_1,
			int coordinateX_2, int coordinateY_2) {
		int temp1 = coordinateX_1 - coordinateX_2;
		int temp2 = coordinateY_1 - coordinateY_2;
		return (int) Math.sqrt(temp1 * temp1 + temp2 * temp2);
	}

	public int getDistance(int roadID, int mileStone_1, int mileStone_2) {
		int mile_1 = mileStone_1;
		int mile_2 = mileStone_2;
		int temp;

		if (mile_1 > mile_2) {
			temp = mile_1;
			mile_1 = mile_2;
			mile_2 = temp;
		}

		Road road = getEnemyRoad(roadID);

		if (road.isValidMileStone(mile_1) == true
				&& road.isValidMileStone(mile_2) == true) {
			int result = 0;
			ArrayList<Coordinate> coorList = road.getRoad();
			Coordinate temp_1, temp_2;

			for (int i = mile_1; i < mile_2; i++) {
				temp_1 = coorList.get(i);
				temp_2 = coorList.get(i + 1);
				result += getDistance(temp_1.getX(), temp_1.getY(),
						temp_2.getX(), temp_2.getY());
			}

			return result;
		} else {
			return 0;
		}
	}

	public int getDistance(int roadID, int mileStone_1) {
		int mile_1 = mileStone_1;

		Road road = getEnemyRoad(roadID);

		if (road.isValidMileStone(mile_1) == true) {
			int result = 0;
			ArrayList<Coordinate> coorList = road.getRoad();
			Coordinate temp_1, temp_2;

			for (int i = mile_1; i < (coorList.size() - 1); i++) {
				temp_1 = coorList.get(i);
				temp_2 = coorList.get(i + 1);
				result += getCoordinateDistance(temp_1.getX(), temp_1.getY(),
						temp_2.getX(), temp_2.getY());
			}

			return result;
		} else {
			return 0;
		}
	}

	public int getDistance(Enemy enemy) {
		Coordinate currentPosition = enemy.getPosition();
		int currentMileStone = enemy.getCurrentMileStone();
		int roadID = enemy.getRoadID();
		Coordinate nextMileStone = enemyRoadList.get(roadID).getMileStone(
				currentMileStone);

		int result = getDistance(currentPosition.getX(),
				currentPosition.getY(), nextMileStone.getX(),
				nextMileStone.getY())
				+ getDistance(roadID, currentMileStone);

		return result;
	}
}
