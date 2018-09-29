package com.gamenc2014.Loan12SuQuan.view.controlMenu;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gamenc2014.Loan12SuQuan.view.screen.AbstractScreen;

public class NewStage extends Stage {

	private AbstractScreen screen;

	public NewStage(AbstractScreen screen) {
		super();
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.ESCAPE) {
			screen.processBackKey();
		}
		return true;
	}
}
