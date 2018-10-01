package com.leaptechjsc.anakachyofthe12warlords.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BaoVeBienCuong(), config);
	}
}
