package com.henez.simple.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.henez.simple.Game;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Simple";
        config.width = 1440;
        config.height = 810;
        config.resizable = false;
        new LwjglApplication(new Game(), config);
    }
}
