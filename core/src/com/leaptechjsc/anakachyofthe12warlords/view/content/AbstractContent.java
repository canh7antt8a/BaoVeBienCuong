package com.leaptechjsc.anakachyofthe12warlords.view.content;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;


public abstract class AbstractContent extends Table {
	protected BaoVeBienCuong baoVeBienCuong;

	public AbstractContent(BaoVeBienCuong game) {
		super();
		this.baoVeBienCuong = game;
	}
	
	public abstract void update();
}
