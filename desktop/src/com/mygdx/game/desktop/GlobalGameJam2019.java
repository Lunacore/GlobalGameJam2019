package com.mygdx.game.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class GlobalGameJam2019 {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		boolean debug = false;
		
		if(debug) {
			config.width = 1280;
			config.height = 720;
			config.fullscreen = false;
		}
		else {
			config.width = d.width;
			config.height = d.height;
			config.fullscreen = true;
		}
		new LwjglApplication(new MyGdxGame(), config);
	}
}
