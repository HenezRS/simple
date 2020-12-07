package com.henez.simple.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.*;
import com.henez.simple.global.Global;

import java.util.Arrays;

public class Atlas {
    private static TextureRegion[][] texTiles;
    private static TextureRegion[][] texActors;
    private static TextureRegion[][] texEnemies;
    private static TextureRegion[][] texEffects;
    private static TextureRegion[][] texIcon7;

    public static void load() {
        texTiles = loadTilesFromFile("png/tiles.png", Global.tilePixelSize);
        texActors = loadTilesFromFile("png/actors.png", Global.tilePixelSize);
        texEnemies = loadTilesFromFile("png/enemies.png", Global.tilePixelSize);
        texEffects = loadTilesFromFile("png/effects.png", Global.tilePixelSize);
        texIcon7 = loadTilesFromFile("png/icon7.png", 7);

    }

    public static TextureRegion toTex(ImgTiles img) {
        return texTiles[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgActors img) {
        return texActors[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgEnemies img) {
        return texEnemies[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgEffects img) {
        return texEffects[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgIcon7 img) {
        return texIcon7[img.getY()][img.getX()];
    }

    private static TextureRegion[][] loadTilesFromFile(String path, int tileSize) {
        TextureRegion[][] dest = TextureRegion.split(new Texture(Gdx.files.internal(path)), tileSize, tileSize);
        Arrays.stream(dest).forEach(texArray -> Arrays.stream(texArray).forEach(tex -> tex.flip(false, true)));
        return dest;
    }
}
