package com.leaptechjsc.anakachyofthe12warlords.controller.inputManager;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;
import com.leaptechjsc.anakachyofthe12warlords.controller.logicManager.GameLogic;
import com.leaptechjsc.anakachyofthe12warlords.model.tower.Tower;
import com.leaptechjsc.anakachyofthe12warlords.view.screen.PlayGameScreen;

public class GameInputListener implements GestureListener {
	private OrthographicCamera cam;

	private float bgWidth;
	private float bgHeight;

	private float scrWidth;
	private float scrHeight;

	private GameLogic gameLogic;
	private int[] selectCell = { -1, -1 };
	private PlayGameScreen screen;

	private boolean firstTap;

	public ZoomManager zoomManager;

	public GameInputListener(float bgWidth, float bgHeight, float scrWidth,
			float scrHeight, PlayGameScreen screen, GameLogic gameLogic) {
		this.bgWidth = bgWidth;
		this.bgHeight = bgHeight;
		this.scrWidth = scrWidth;
		this.scrHeight = scrHeight;
		this.screen = screen;
		this.cam = screen.getCamera();
		this.gameLogic = gameLogic;
		this.firstTap = true;
		
		zoomManager = new ZoomManager(scrWidth, scrHeight, bgWidth, bgHeight);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (screen.isPause() == false) {
			selectCell = selectCell(x, y, gameLogic.getNumRows(),
					gameLogic.getNumCols());
			if (screen.isUIUpgradeOff() == true) {
				this.firstTap = true;
				gameLogic.processSelectedCell(selectCell);
				Tower temp = gameLogic.getSelectedTower();

				if (temp != null) {
					screen.loadImage();
					this.firstTap = true;
				}
			} else {
				Tower temp_1 = gameLogic.getSelectedTower();
				gameLogic.processSelectedCell(selectCell);
				Tower temp_2 = gameLogic.getSelectedTower();

				if (temp_2 != null) {
					if (temp_1 == null) {
						gameLogic.setSelectedTower(null);
						screen.removeUIUpgrade();
					} else {
						if (this.firstTap == true) {
							gameLogic.setSelectedTower(temp_1);
							this.firstTap = false;
						} else {
							screen.removeUIUpgrade();
							screen.loadImage();
							this.firstTap = true;
						}
					}
				} else {
					gameLogic.setSelectedTower(temp_1);
					screen.turnOffUIUpgrade();
					this.firstTap = true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if (screen.isPause() == false) {
			if (PlayGameScreen.enableListener) {
				Vector2 sizeTexture = new Vector2(this.bgWidth, this.bgHeight);
				cam.position.add(-deltaX * cam.zoom, deltaY * cam.zoom, 0);
				if (viewPInTex(cam.position, cam.zoom, cam.viewportWidth,
						cam.viewportHeight, sizeTexture) != 1) {
					cam.position.add(deltaX * cam.zoom, -deltaY * cam.zoom, 0);
				}
			}
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		if (screen.isPause() == false) {
			Vector2 sizeTexture = new Vector2(bgWidth, bgHeight);
			float ratio = 0.01f * (distance - initialDistance)
					/ initialDistance;

			processZoom(sizeTexture, ratio);
		}
		return false;
	}

	public void processZoom(Vector2 sizeTexture, float ratio) {
		if (cam.zoom - ratio >= 1f
				&& cam.zoom - ratio <= zoomManager.getMaxZoomLevel()) {
			cam.zoom -= ratio;
			int temp = viewPInTex(cam.position, cam.zoom - ratio,
					cam.viewportWidth, cam.viewportHeight, sizeTexture);
			if (temp == -1) {
				cam.position.x = (cam.viewportWidth / 2) * cam.zoom;
				cam.position.y = (cam.viewportHeight / 2) * cam.zoom;

			} else if (temp == -2) {
				cam.position.x = (cam.viewportWidth / 2) * cam.zoom;
				cam.position.y = -(cam.viewportHeight / 2) * cam.zoom
						+ sizeTexture.y;

			} else if (temp == -3) {
				cam.position.x = (cam.viewportWidth / 2) * cam.zoom;

			} else if (temp == -4) {
				cam.position.x = -(cam.viewportWidth / 2) * cam.zoom
						+ sizeTexture.x;
				cam.position.y = (cam.viewportHeight / 2) * cam.zoom;

			} else if (temp == -5) {
				cam.position.x = -(cam.viewportWidth / 2) * cam.zoom
						+ sizeTexture.x;
				cam.position.y = -(cam.viewportHeight / 2) * cam.zoom
						+ sizeTexture.y;

			} else if (temp == -6) {
				cam.position.x = -(cam.viewportWidth / 2) * cam.zoom
						+ sizeTexture.x;

			} else if (temp == -7) {
				cam.position.y = (cam.viewportHeight / 2) * cam.zoom;

			} else if (temp == -8) {
				cam.position.y = -(cam.viewportHeight / 2) * cam.zoom
						+ sizeTexture.y;
			}
		}
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}

	/**
	 * @return Array of 2 integer: first number is cols index, second number is
	 *         row index of selected cell if get (-1, -1): no cell is selected
	 */
	public int[] getSelectCell() {
		return selectCell;
	}

	public void setSelectCell(int[] selectCell) {
		this.selectCell = selectCell;
	}

	/**
	 * 
	 * @return true if player selected a cell, false if other
	 */
	public boolean isSelectedCell() {
		return (selectCell[0] >= 0 && selectCell[1] >= 0);
	}

	/**
	 * Selected cell be no cell selected.
	 */
	public void clearSelectedCell() {
		selectCell[0] = -1;
		selectCell[1] = -1;
	}

	private int viewPInTex(Vector3 position, float zoom, float viewPortWidth,
			float viewPortHeight, Vector2 sizeTexture) {
		float kX = (viewPortWidth / 2) * zoom;
		float kY = (viewPortHeight / 2) * zoom;
		if (position.x - kX < 0) {
			if (position.y - kY < 0) {
				// gan x, y = 0
				return -1;

			} else if (position.y + kY > sizeTexture.y) {
				// gan x = 0; y = max - ky
				return -2;
			} else {
				// gan x = 0
				return -3;
			}
		} else if (position.x + kX > sizeTexture.x) {
			if (position.y - kY < 0) {
				// gan x = max, y = 0
				return -4;
			} else if (position.y + kY > sizeTexture.y) {
				// gan x = max; y = max
				return -5;
			} else {
				// gan x = max
				return -6;
			}
		} else {
			if (position.y - kY < 0) {
				// gan y = 0;
				return -7;
			} else if (position.y + kY > sizeTexture.y) {
				// gan y = max
				return -8;
			} else {
				return 1;
			}
		}
	}

	public int[] selectCell(float x, float y, int nRow, int nCols) {
		int[] cell = { -1, -1 };
		cell[0] = (int) (screenToWorldX(x) / bgWidth * nCols);
		cell[1] = nRow - (int) (screenToWorldY(y) / bgHeight * nRow) - 1;
		return cell;
	}

	public float screenToWorldX(float x) {
		return cam.position.x + (x - scrWidth / 2) * cam.zoom;
	}

	public float screenToWorldY(float y) {
		return cam.position.y + (scrHeight / 2 - y) * cam.zoom;
	}
}
