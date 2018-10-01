package com.leaptechjsc.anakachyofthe12warlords.view.content;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;

public class HeroContent extends AbstractContent {
	public Label notice;

	public HeroContent(BaoVeBienCuong game) {
		super(game);
		BitmapFont font = baoVeBienCuong.fontDataManager.getBasicFont();
		font.getData().setScale(1);
		notice = new Label("Chức năng đang phát triển", new LabelStyle(font,
				Color.RED));
		notice.setBounds(100, 100, 500, 200);
		this.addActor(notice);
	}

	@Override
	public void update() {
	}
}
