package com.gamenc2014.Loan12SuQuan.view.content;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;


public abstract class AbstractContent extends Table {
	protected BaoVeBienCuong baoVeBienCuong;

	public AbstractContent(BaoVeBienCuong game) {
		super();
		this.baoVeBienCuong = game;
	}
	
	public abstract void update();
}
