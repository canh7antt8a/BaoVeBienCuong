package com.gamenc2014.Loan12SuQuan.view.controlMenu;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SwipeButton extends ImageButton {

	protected boolean isAnimate = false;
	protected float timeAnimate = 0;
	protected Animation act;
	protected Table table;
	protected byte isActived;
	protected TextureRegion lock;
	protected int dataID;

	public SwipeButton(Animation act, byte isActived, TextureRegion lock,
			int dataID) {
		super(new TextureRegionDrawable((TextureRegion)act.getKeyFrame(0)),
				new TextureRegionDrawable((TextureRegion)act.getKeyFrame(0)));
		this.act = act;
		this.isActived = isActived;
		this.lock = lock;
		this.dataID = dataID;
	}

	public void setAnimate(boolean isAnimate) {
		this.isAnimate = isAnimate;
		timeAnimate = 0;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void update(float delta, byte isActived) {
		if (isAnimate) {
			timeAnimate += delta;
		}
		this.isActived = isActived;
	}

	public void drawAnimate(SpriteBatch batch, float parentAlpha) {
		batch.draw((TextureRegion)act.getKeyFrame(timeAnimate),
				this.getX() + this.getOriginX(),
				this.getY() + this.getOriginY(), this.getWidth(),
				this.getHeight());

		table.getCell(this);
	}

//	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (isActived == 0) {
			batch.setColor(0.5f, 0.5f, 0.5f, 1f);
		}
		drawAnimate(batch, parentAlpha);
		batch.setColor(1, 1, 1, 1);

		if (isActived == 0) {
			batch.draw(lock, this.getX() + this.getOriginX() + this.getWidth()
					/ 2 + 15, this.getOriginY() + 25, 32, 32);
		}
	}
}
