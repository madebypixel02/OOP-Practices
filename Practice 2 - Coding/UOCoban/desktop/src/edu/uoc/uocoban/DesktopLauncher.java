package edu.uoc.uocoban;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import edu.uoc.uocoban.view.UocobanGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("UOCoban Game");
		config.setResizable(false);
		config.setWindowedMode(UocobanGame.WINDOW_WIDTH, UocobanGame.WINDOW_HEIGHT);;
		new Lwjgl3Application(new UocobanGame(), config);
	}
}
