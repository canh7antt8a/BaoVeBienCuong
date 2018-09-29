package com.gamenc2014.Loan12SuQuan.view.drawObject;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gamenc2014.Loan12SuQuan.controller.logicManager.GameLogic;
import com.gamenc2014.Loan12SuQuan.model.gameObject.ICommonConstants;
import com.gamenc2014.Loan12SuQuan.model.map.BattleMap;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.model.scenario.Scenario;
import com.gamenc2014.Loan12SuQuan.view.screen.PlayGameScreen;

public class DrawGrid extends DrawHelper {
	private Texture grid;

	private Coordinate dragCell;

	private int numCols;
	private int numRows;

	private Scenario scenario;

	private ShapeRenderer renderer;

	private OrthographicCamera camera;

	public DrawGrid(Texture grid, SpriteBatch batch, GameLogic gameLogic,
			OrthographicCamera camera) {
		super(batch, gameLogic);

		this.grid = grid;
		this.numCols = gameLogic.getNumCols();
		this.numRows = gameLogic.getNumRows();
		this.scenario = gameLogic.getScenario();

		this.renderer = new ShapeRenderer();
		this.camera = camera;
	}

	public void draw(int actionType) {
		BattleMap map = scenario.getBattleMap();
		switch (actionType) {
		case ICommonConstants.BUILDING:
			for (int i = 0; i < numCols; i++) {
				for (int j = 0; j < numRows; j++) {
					if (scenario.isBuildable(i, j)) {
						batch.draw(grid, i << 7, PlayGameScreen.tempValue
								- (j << 7));
					}
				}
			}

			if (dragCell != null) {
				int tempX = dragCell.getX();
				int tempY = dragCell.getY();
				if (scenario.isBuildable(dragCell.getX(), dragCell.getY())) {
					batch.end();

					int x = (int) worldToScreenX((tempX << 7));
					int y = (int) worldToScreenY((tempY << 7));

					Gdx.gl.glEnable(GL20.GL_BLEND);

					renderer.setColor(1, 1, 1, 0.5f);
					renderer.begin(ShapeType.Filled);//FilledRectangle);

					renderer.rect(x, 0,
							ICommonConstants.DEFAULT_TILE_SIZE / camera.zoom,
							PlayGameScreen.SCR_HEIGHT);

					renderer.rect(0, y, PlayGameScreen.SCR_WIDTH,
							ICommonConstants.DEFAULT_TILE_SIZE / camera.zoom);

					renderer.end();

					batch.begin();
				}
			}

			break;
		case ICommonConstants.MAGIC:
			for (int i = 0; i < numCols; i++) {
				for (int j = 0; j < numRows; j++) {
					if (map.isRoad(i, j)) {
						batch.draw(grid, i << 7, PlayGameScreen.tempValue
								- (j << 7));
					}
				}
			}

			if (dragCell != null) {
				int tempX = dragCell.getX();
				int tempY = dragCell.getY();
				if (map.isRoad(dragCell.getX(), dragCell.getY())) {
					batch.end();

					int x = (int) worldToScreenX((tempX << 7));
					int y = (int) worldToScreenY((tempY << 7));

					Gdx.gl.glEnable(GL20.GL_BLEND);

					renderer.setColor(1, 1, 1, 0.5f);
					renderer.begin(ShapeType.Filled);

					renderer.rect(x, 0,
							ICommonConstants.DEFAULT_TILE_SIZE / camera.zoom,
							PlayGameScreen.SCR_HEIGHT);

					renderer.rect(0, y, PlayGameScreen.SCR_WIDTH,
							ICommonConstants.DEFAULT_TILE_SIZE / camera.zoom);

					renderer.end();

					batch.begin();
				}
			}

			break;
		}
	}

	public void resetDragCell() {
		this.dragCell = null;
	}

	public void setDragCell(Coordinate dragCell) {
		this.dragCell = dragCell;
	}

	public float screenToWorldX(float x) {
		return camera.position.x + (x - PlayGameScreen.SCR_WIDTH / 2)
				* camera.zoom;
	}

	public float screenToWorldY(float y) {
		return camera.position.y + (PlayGameScreen.SCR_HEIGHT / 2 - y)
				* camera.zoom;
	}

	public float worldToScreenX(float x) {
		return (x - camera.position.x) / camera.zoom + PlayGameScreen.SCR_WIDTH
				/ 2;
	}

	public float worldToScreenY(float y) {
		return ((PlayGameScreen.tempValue - y) - camera.position.y)
				/ camera.zoom + PlayGameScreen.SCR_HEIGHT / 2;
	}

}
