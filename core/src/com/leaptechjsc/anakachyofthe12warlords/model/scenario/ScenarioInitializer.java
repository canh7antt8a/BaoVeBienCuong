package com.leaptechjsc.anakachyofthe12warlords.model.scenario;

import java.util.ArrayList;



import com.badlogic.gdx.Gdx;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.TowerManager;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.EnumTowerList;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.TowerManager;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.EnemyData;
import com.leaptechjsc.anakachyofthe12warlords.model.enemy.EnemyWave;
import com.leaptechjsc.anakachyofthe12warlords.model.map.BattleMap;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Coordinate;
import com.leaptechjsc.anakachyofthe12warlords.model.map.IMapConstants;
import com.leaptechjsc.anakachyofthe12warlords.model.map.Road;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.ArcherTower;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.KnightTower;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.ShamanTower;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.SwordTower;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.Tower;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.EnumTowerList;
import com.leaptechjsc.anakachyofthe12warlords.model.towerData.ITowerConstants;

public class ScenarioInitializer implements IMapConstants {

	private TowerManager towerManager;
	private int gold;
	private int playerHP;

	public ScenarioInitializer(TowerManager towerManager) {
		this.towerManager = towerManager;
	}

	public Scenario initialize(int mapID) {
		ArrayList<Road> roadList = new ArrayList<Road>();
		ArrayList<Tower> towerList = new ArrayList<Tower>();
		ArrayList<EnemyWave> enemyWaves = new ArrayList<EnemyWave>();

		parseLevelData(mapID, roadList, enemyWaves, towerList);

		BattleMap battleMap = new BattleMap(mapID, getMapDescription(mapID),
				roadList);

		return new Scenario(battleMap, towerList, enemyWaves, gold, playerHP);
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public void setTowerManager(TowerManager towerManager) {
		this.towerManager = towerManager;
	}

	public Road getRoad(int roadID, ArrayList<Coordinate> mileStone) {
		ArrayList<Coordinate> coorList = new ArrayList<Coordinate>();
		Coordinate temp;
		int size = mileStone.size();
		int index = 0;

		while (index < size) {
			temp = mileStone.get(index);
			coorList.add(new Coordinate(temp.getX() << 7, temp.getY() << 7));
			index++;
		}

		return new Road(roadID, coorList);
	}

	public int[][] getMapDescription(int mapID) {
		String data = Gdx.files.internal(
				"data/maps/map_" + mapID + "/map_" + mapID + ".txt")
				.readString();
		int[][] result;

		int i = data.indexOf(":");
		int j = data.indexOf(";");

		int row = Integer.parseInt(data.substring(i + 1, j).trim());

		i = data.indexOf(":", i + 1);
		j = data.indexOf(";", j + 1);

		int column = Integer.parseInt(data.substring(i + 1, j).trim());

		result = new int[row][column];

		int m, n;
		i = j;

		for (m = 0; m < row; m++) {
			for (n = 0; n < column; n++) {
				i = data.indexOf(";", i + 1);
				result[m][n] = Integer
						.parseInt(data.substring(j + 1, i).trim());
				j = i;
				// System.out.printf("%5d", mapDescription[m][n]);
			}
			// System.out.println();
		}

		return result;
	}

	public void parseLevelData(int mapID, ArrayList<Road> roadList,
			ArrayList<EnemyWave> enemyWaves, ArrayList<Tower> towerList) {
		String data = Gdx.files.internal("data/level/level_" + mapID + ".txt")
				.readString();

		int i = data.indexOf(":");
		int j = data.indexOf(";");

		int number = Integer.parseInt(data.substring(i + 1, j).trim());
		int m, n, k;

		int x, y;

		for (m = 0; m < number; m++) {
			i = data.indexOf(":", i + 1);
			j = data.indexOf(";", j + 1);

			n = Integer.parseInt(data.substring(i + 1, j).trim());

			ArrayList<Coordinate> coorList = new ArrayList<Coordinate>();

			for (k = 0; k < n; k++) {
				i = data.indexOf(",", i + 1);
				x = Integer.parseInt(data.substring(j + 1, i).trim());

				j = data.indexOf(";", j + 1);
				y = Integer.parseInt(data.substring(i + 1, j).trim());
				coorList.add(new Coordinate(x, y));
				// System.out.println(x + "-" + y);
			}
			roadList.add(getRoad(m, coorList));
		}

		i = data.indexOf(":", i + 1);
		j = data.indexOf(";", j + 1);
		gold = Integer.parseInt(data.substring(i + 1, j).trim());

		i = data.indexOf(":", i + 1);
		j = data.indexOf(";", j + 1);
		playerHP = Integer.parseInt(data.substring(i + 1, j).trim());

		i = data.indexOf(":", i + 1);
		j = data.indexOf(";", j + 1);
		number = Integer.parseInt(data.substring(i + 1, j).trim());

		float time, waveTime;
		int id;

		for (m = 0; m < number; m++) {
			i = data.indexOf(":", i + 1);
			j = data.indexOf(",", j + 1);

			n = Integer.parseInt(data.substring(i + 1, j).trim());

			i = j;
			j = data.indexOf(";", j + 1);
			waveTime = Float.parseFloat(data.substring(i + 1, j).trim());

			ArrayList<EnemyData> enemyDataList = new ArrayList<EnemyData>();
			EnemyWave enemyWave = new EnemyWave(m, enemyDataList, waveTime);

			for (k = 0; k < n; k++) {
				i = data.indexOf(":", i + 1);

				id = Integer.parseInt(data.substring(j + 1, i).trim());
				j = data.indexOf(",", j + 1);
				time = Float.parseFloat(data.substring(i + 1, j).trim());

				i = j;
				j = data.indexOf(";", j + 1);
				y = Integer.parseInt(data.substring(i + 1, j).trim());
				enemyDataList.add(new EnemyData(id, time, y));
				// System.out.println(time + "-" + y);
			}
			enemyWaves.add(enemyWave);
		}

		i = data.indexOf(":", i + 1);
		j = data.indexOf(";", j + 1);
		number = Integer.parseInt(data.substring(i + 1, j).trim());

		for (m = 0; m < number; m++) {
			i = data.indexOf(":", i + 1);

			id = Integer.parseInt(data.substring(j + 1, i).trim());

			j = data.indexOf(",", j + 1);

			x = Integer.parseInt(data.substring(i + 1, j).trim());

			i = j;
			j = data.indexOf(";", j + 1);
			y = Integer.parseInt(data.substring(i + 1, j).trim());
			addNewTower(id, new Coordinate(x << 7, y << 7), towerList);
		}
	}

	public void addNewTower(int dataID, Coordinate position,
			ArrayList<Tower> towerList) {
		Tower tempTower = null;
		int numberTower = towerList.size();

		switch (dataID) {
		case ITowerConstants.BASIC_ARCHER_LV1:
		case ITowerConstants.BASIC_ARCHER_LV2:
		case ITowerConstants.BASIC_ARCHER_LV3:

		case ITowerConstants.ARCHER_1_LV1:
		case ITowerConstants.ARCHER_1_LV2:
		case ITowerConstants.ARCHER_1_LV3:

		case ITowerConstants.ARCHER_2_LV1:
		case ITowerConstants.ARCHER_2_LV2:
		case ITowerConstants.ARCHER_2_LV3:
			tempTower = new ArcherTower(numberTower, position,
					towerManager.getTower(dataID));
			break;
		case ITowerConstants.BASIC_SWORD_LV1:
		case ITowerConstants.BASIC_SWORD_LV2:
		case ITowerConstants.BASIC_SWORD_LV3:

		case ITowerConstants.SWORD_1_LV1:
		case ITowerConstants.SWORD_1_LV2:
		case ITowerConstants.SWORD_1_LV3:

		case ITowerConstants.SWORD_2_LV1:
		case ITowerConstants.SWORD_2_LV2:
		case ITowerConstants.SWORD_2_LV3:
			tempTower = new SwordTower(numberTower, position,
					towerManager.getTower(dataID));
			break;
		case ITowerConstants.BASIC_KNIGHT_LV1:
		case ITowerConstants.BASIC_KNIGHT_LV2:
		case ITowerConstants.BASIC_KNIGHT_LV3:

		case ITowerConstants.KNIGHT_1_LV1:
		case ITowerConstants.KNIGHT_1_LV2:
		case ITowerConstants.KNIGHT_1_LV3:

		case ITowerConstants.KNIGHT_2_LV1:
		case ITowerConstants.KNIGHT_2_LV2:
		case ITowerConstants.KNIGHT_2_LV3:
			tempTower = new KnightTower(numberTower, position,
					towerManager.getTower(dataID));
			break;
		case ITowerConstants.BASIC_SHAMAN_LV1:
		case ITowerConstants.BASIC_SHAMAN_LV2:
		case ITowerConstants.BASIC_SHAMAN_LV3:

		case ITowerConstants.SHAMAN_1_LV1:
		case ITowerConstants.SHAMAN_1_LV2:
		case ITowerConstants.SHAMAN_1_LV3:

		case ITowerConstants.SHAMAN_2_LV1:
		case ITowerConstants.SHAMAN_2_LV2:
		case ITowerConstants.SHAMAN_2_LV3:
			tempTower = new ShamanTower(numberTower, position,
					towerManager.getTower(dataID));
			break;
		}

		if (tempTower != null) {
			towerList.add(tempTower);
			tempTower.setGoldCost(EnumTowerList.getGoldCost(dataID));
		}
	}
}
