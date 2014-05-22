package com.superProduction.awsum.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.superProduction.awsum.awsumPeli;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Awsum " + awsumPeli.VERSION;
		config.useGL30 = false;
		config.width = 1600;
		config.height = 720;
		
		new LwjglApplication(new awsumPeli(), config);
		
	}
}
