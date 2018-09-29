package com.gamenc2014.Loan12SuQuan.controller.inputManager;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gamenc2014.Loan12SuQuan.model.map.Coordinate;
import com.gamenc2014.Loan12SuQuan.view.screen.MapSelectionScreen;

public class InputListener implements GestureListener {
	private OrthographicCamera cam;
	private float bgWidth;
	private float bgHeight;

	private float scrWidth;
	private float scrHeight;

	private MapSelectionScreen screen;

	private Coordinate[] position;

	public InputListener(float bgWidth, float bgHeight, float scrWidth,
			float scrHeight, OrthographicCamera camera,
			MapSelectionScreen screen) {
		this.bgWidth = bgWidth;
		this.bgHeight = bgHeight;

		this.scrWidth = scrWidth;
		this.scrHeight = scrHeight;

		this.cam = camera;
		this.screen = screen;

		configCoordinate(screen.getPosition());
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {

		int coorX = (int) screenToWorldX(x);
		int coorY = (int) screenToWorldY(y);

		if (!screen.isInfoWindowOpen()) {
            for (int i = 0; i < this.position.length; i++) {
                if (this.position[i].dst(coorX, coorY) < 64) {
                    screen.setSelectedCastleIndex(i);
                    break;
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
//		Vector2 sizeTexture = new Vector2(this.bgWidth, this.bgHeight);
//		cam.position.add(-deltaX * cam.zoom, deltaY * cam.zoom, 0);
//		if (viewPInTex(cam.position, cam.zoom, cam.viewportWidth,
//				cam.viewportHeight, sizeTexture) != 1) {
//			cam.position.add(deltaX * cam.zoom, -deltaY * cam.zoom, 0);
//		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

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

	public float screenToWorldX(float x) {
		return cam.position.x + (x - scrWidth / 2) * cam.zoom;
	}

	public float screenToWorldY(float y) {
		return cam.position.y + (scrHeight / 2 - y) * cam.zoom;
	}

	public void configCoordinate(Coordinate[] coorList) {
		this.position = new Coordinate[coorList.length];
		for (int i = 0; i < coorList.length; i++) {
			this.position[i] = new Coordinate(coorList[i].getX() + 60,
					coorList[i].getY() + 55);
		}
	}
}
