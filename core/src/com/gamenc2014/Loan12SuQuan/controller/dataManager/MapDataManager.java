package com.gamenc2014.Loan12SuQuan.controller.dataManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class MapDataManager extends AbstractDataManager {
	public final static int MAP_0 = 0;
	public final static int MAP_1 = 1;
	public final static int MAP_2 = 2;
	public final static int MAP_3 = 3;
	public final static int MAP_4 = 4;
	public final static int MAP_5 = 5;
	public final static int MAP_6 = 6;
	public final static int MAP_7 = 7;
	public final static int MAP_8 = 8;

	private int mapID;
	private Texture grid;

	private int numCols;
	private int numRows;

	private Texture[] map;

	public MapDataManager(int mapID) {
		this.mapID = mapID;
	}

	public void addToLoad(AssetManager assetManager) {
		int numberMapFragment = MapDataManager.getNumberMapFragment(mapID);
		String mapPath = MapDataManager.resolveID(mapID);

		for (int i = 0; i < numberMapFragment; i++) {
			assetManager.load(mapPath + "m_" + i + ".jpg", Texture.class);
		}

		assetManager.load("data/maps/border.png", Texture.class);
	}

	public void getData(AssetManager assetManager) {
		this.map = new Texture[MapDataManager.getNumberMapFragment(mapID)];
		String mapPath = MapDataManager.resolveID(mapID);

		for (int i = 0; i < map.length; i++) {
			map[i] = assetManager.get(mapPath + "m_" + i + ".jpg",
					Texture.class);
			map[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		setMapSize(mapID);

		this.grid = assetManager.get("data/maps/border.png", Texture.class);
		this.grid.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public Texture getMapFragments(int id) {
		return map[id];
	}

	public Texture[] getMap() {
		return map;
	}

	public static String resolveID(int id) {
		return ("data/maps/map_" + id + "/");
	}

	public static int getNumberMapFragment(int id) {
		switch (id) {
		case MAP_0:
			return 6;
		case MAP_1:
			return 6;
		case MAP_2:
			return 6;
		case MAP_3:
			return 6;
		case MAP_4:
			return 8;
		case MAP_5:
			return 9;			
		case MAP_6:
			return 16;
		case MAP_7:
			return 8;
		case MAP_8:
			return 8;
		default:
			return 6;
		}
	}

	public void setMapSize(int id) {
		switch (id) {
		case MAP_0:
			numCols = 3;
			numRows = 2;
			break;

		case MAP_1:
			numCols = 3;
			numRows = 2;
			break;

		case MAP_2:
			numCols = 3;
			numRows = 2;
			break;

		case MAP_3:
			numCols = 3;
			numRows = 2;
			break;

		case MAP_4:
			numCols = 4;
			numRows = 2;
			break;

		case MAP_5:
			numCols = 3;
			numRows = 3;
			break;
			
		case MAP_6:
			numCols = 4;
			numRows = 4;
			break;
			
		case MAP_7:
			numCols = 4;
			numRows = 2;
			break;
			
		case MAP_8:
			numCols = 2;
			numRows = 4;
			break;

		default:
			numCols = 1;
			numRows = 1;
			break;
		}
	}

	public int getMapID() {
		return mapID;
	}

	public int getNumCols() {
		return numCols;
	}

	public int getNumRows() {
		return numRows;
	}

	public Texture getGrid() {
		return grid;
	}
}
