package me.jack.ld51;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.jack.ld51.LD51Game;
import me.jack.ld51.ui.HUD;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(640, (int) (480 + HUD.wheelRadius*2));
		config.setForegroundFPS(60);
		config.setTitle("LD51 - Theme: Every 10 Seconds");
		new Lwjgl3Application(new LD51Game(), config);
	}
}
