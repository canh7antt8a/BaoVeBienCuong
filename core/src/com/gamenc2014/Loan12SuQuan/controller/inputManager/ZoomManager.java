package com.gamenc2014.Loan12SuQuan.controller.inputManager;

public class ZoomManager {
	public float rateX;
	public float rateY;

	public float[] zoomLevel;

	public int currentZoomLevel;

	public ZoomManager(float scrWidth, float scrHeight, float bgWidth,
			float bgHeight) {
		this.rateX = bgHeight / scrHeight;
		this.rateY = bgWidth / scrWidth;

		this.zoomLevel = new float[3];
		this.currentZoomLevel = 0;

		configZoomLevel();
	}

	public float getZoomLevel(int zoom) {
		if (zoom > -1 & zoom < zoomLevel.length) {
			return zoomLevel[zoom];
		} else {
			return zoomLevel[0];
		}
	}

	public float getMaxZoomLevel() {
		return zoomLevel[zoomLevel.length - 1];
	}

	public void configZoomLevel() {
		zoomLevel[0] = 1.0f;

		float maxRate;

		maxRate = Math.min(rateX, rateY) - 0.05f;

		zoomLevel[1] = (maxRate + 1.0f) / 2;
		zoomLevel[2] = maxRate;
	}

	public float getNextZoomLevel() {
		return zoomLevel[(currentZoomLevel++) % zoomLevel.length];
	}

	public float getCurrentZoomLevel() {
		return currentZoomLevel;
	}

	public boolean isMaxZoom() {
		if (currentZoomLevel == zoomLevel.length) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMinZoom() {
		if (currentZoomLevel == 0) {
			return true;
		} else {
			return false;
		}
	}

}
