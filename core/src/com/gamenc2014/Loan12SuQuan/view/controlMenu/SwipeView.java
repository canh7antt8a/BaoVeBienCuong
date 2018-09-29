package com.gamenc2014.Loan12SuQuan.view.controlMenu;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gamenc2014.Loan12SuQuan.BaoVeBienCuong;
import com.gamenc2014.Loan12SuQuan.controller.data.ButtonImage;
import com.gamenc2014.Loan12SuQuan.controller.soundManager.ScreenSoundDataManager;

public class SwipeView extends Table {
	protected ScrollPane pane;
	protected Table tables;
	protected Table control;
	protected ButtonGroup list;
	protected ImageButton buttons[];
	protected int numChildrent;
	protected int currentIndex = 0;
	Color uncheck = new Color(0.75f, 0.35f, 0.35f, 0.5f);
	Color check = new Color(0.75f, 0.35f, 0.35f, 1.0f);

	private BaoVeBienCuong baoVeBienCuong;

	public SwipeView(Table tables, int x, int y, int width, int height,
			BaoVeBienCuong game) {
		this.tables = tables;
		this.baoVeBienCuong = game;
		pane = new ScrollPane(tables);
		pane.setBounds(x, y, width, height);
		pane.setScrollingDisabled(false, true);

		numChildrent = tables.getCells().size;

		ButtonImage swipeImage = game.profileScreenDataManager.getSwipeButton();

		buttons = new ImageButton[numChildrent];

		control = new Table();
		control.row();

		for (int i = 0; i < numChildrent; i++) {
			buttons[i] = new ImageButton(new TextureRegionDrawable(
					swipeImage.getImageUp()), new TextureRegionDrawable(
					swipeImage.getImageDown()));
			control.add(buttons[i]).width(20).height(20).padLeft(10);
			buttons[i].setColor(uncheck);
			final int j = i;
			buttons[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					baoVeBienCuong.screenSoundManager.play(
							ScreenSoundDataManager.CLICK, false);
					super.clicked(event, x, y);
					pane.setScrollX(j * SwipeView.this.tables.getWidth()
							/ numChildrent);
					buttons[currentIndex].setColor(uncheck);
					currentIndex = j;
					buttons[currentIndex].setColor(check);
				}
			});
		}
		buttons[0].setColor(check);
		list = new ButtonGroup(buttons);

		this.add(pane);
		this.row();
		this.add(control);
	}

	public void setCurrentIndex(int i) {
		if (i >= 0 && i < numChildrent) {
			buttons[currentIndex].setColor(uncheck);
			currentIndex = i;
			buttons[currentIndex].setColor(check);
		}
	}

//	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		update();
		super.draw(batch, parentAlpha);
	}

	public void update() {
		float temp1 = ((pane.getScrollX() + 10) * numChildrent / tables
				.getWidth());
		int temp = (int) temp1;
		if (temp1 >= temp + 0.5)
			temp += 1;

		if (currentIndex != temp) {
			buttons[currentIndex].setColor(uncheck);
			currentIndex = temp;
			buttons[currentIndex].setColor(check);
		}

		if (!pane.isFlinging() && !pane.isPanning() && !pane.isDragging()) {
			pane.setScrollX(currentIndex * tables.getWidth() / numChildrent);
		}
	}
}
