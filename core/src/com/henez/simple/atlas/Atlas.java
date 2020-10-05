package com.henez.simple.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class Atlas {
    private static TextureRegion[][] texTiles;
    private static TextureRegion[][] texActors;
    private static TextureRegion[][] texEnemies;

    public static void load() {
        texTiles = loadTilesFromFile("png/tiles.png");
        texActors = loadTilesFromFile("png/actors.png");
        texEnemies = loadTilesFromFile("png/enemies.png");

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

    private static TextureRegion[][] loadTilesFromFile(String path) {
        TextureRegion[][] dest = TextureRegion.split(new Texture(Gdx.files.internal(path)), 16, 16);
        Arrays.stream(dest).forEach(texArray -> Arrays.stream(texArray).forEach(tex -> tex.flip(false, true)));
        return dest;
    }
}
