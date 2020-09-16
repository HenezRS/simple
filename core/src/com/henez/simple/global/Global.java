package com.henez.simple.global;

import com.badlogic.gdx.graphics.Pixmap;

public class Global {
    public static String GAME_PACKAGE_NAME = "simple";
    public static String PATH_EXTERNAL = String.format("\\Dropbox\\Java Projects\\LIBGDX Projects\\%s\\docs\\", GAME_PACKAGE_NAME);
    public static String PATH_LOCAL = "bin/";
    public static String PATH_MAPS = "maps/";

    public static final int FLOOR_TILE_MAX_W = 100;
    public static final int FLOOR_TILE_MAX_H = 57;
    public static final Pixmap.Format PIXMAP_FORMAT = Pixmap.Format.RGB888;
    public static int NONE = -1;
    public static int gameScale = 3;
    public static int cameraPixelW = 480;
    public static int cameraPixelH = 270;
    public static int cameraPixelWW = cameraPixelW / 2;
    public static int cameraPixelHH = cameraPixelH / 2;
    public static int windowPixelW = cameraPixelW * gameScale;
    public static int windowPixelH = cameraPixelH * gameScale;
    public static int tilePixel = 16;
    public static int tilePixel2 = 8;
    public static int tilePixelW = 16;
    public static int tilePixelH = 16;
    public static int imgSheetTileWidth = 16;
    public static int windowTileW = windowPixelW / tilePixelW;
    public static int windowTileH = windowPixelH / tilePixelH;
    public static int SEC = 60;
    public static int SEC2 = SEC / 2;
    public static int SEC3 = SEC / 3;
    public static int SEC4 = SEC / 4;
    public static int SEC5 = SEC / 5;
    public static int SEC6 = SEC / 6;
}