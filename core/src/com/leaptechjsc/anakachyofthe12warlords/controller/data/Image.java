package com.leaptechjsc.anakachyofthe12warlords.controller.data;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Image {
	private int ID;
	private TextureRegion image;

	public Image(int id, TextureRegion image) {
		this.ID = id;
		this.image = image;
	}

	public int getID() {
		return ID;
	}

	public TextureRegion getImage() {
		return image;
	}
}
