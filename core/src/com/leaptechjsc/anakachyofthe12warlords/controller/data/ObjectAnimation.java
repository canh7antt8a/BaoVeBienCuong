package com.leaptechjsc.anakachyofthe12warlords.controller.data;

import com.badlogic.gdx.graphics.g2d.Animation;

public class ObjectAnimation {
	private int ID;
	private Animation animation;

	public ObjectAnimation(int ID, Animation animation) {
		this.ID = ID;
		this.animation = animation;
		this.animation.setPlayMode(Animation.PlayMode.LOOP);
	}

	public int getID() {
		return ID;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setPlayMode(int playMode) {
		Animation.PlayMode playMode1 = Animation.PlayMode.NORMAL;
		switch (playMode){
			case 1:
				playMode1 = Animation.PlayMode.REVERSED;
				break;
			case 2:
				playMode1 = Animation.PlayMode.LOOP;
				break;
			case 3:
				playMode1 = Animation.PlayMode.LOOP_REVERSED;
				break;
			case 4:
				playMode1 = Animation.PlayMode.LOOP_PINGPONG;
				break;
			case 5:
				playMode1 = Animation.PlayMode.LOOP_RANDOM;
				break;
		}
		animation.setPlayMode(playMode1);
	}
}
