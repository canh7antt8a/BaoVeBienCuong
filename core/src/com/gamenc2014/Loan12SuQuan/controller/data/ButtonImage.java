package com.gamenc2014.Loan12SuQuan.controller.data;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ButtonImage {
	private int ID;
	private TextureRegion imageUp;
	private TextureRegion imageDown;

	public ButtonImage(int ID, TextureRegion imageUp, TextureRegion imageDown) {
		this.ID = ID;
		this.imageUp = imageUp;
		this.imageDown = imageDown;
	}

	public int getID() {
		return ID;
	}

	public TextureRegion getImageUp() {
		return imageUp;
	}

	public TextureRegion getImageDown() {
		return imageDown;
	}
}