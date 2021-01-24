package com.henez.simple.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.*;
import com.henez.simple.global.Global;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Atlas {
    private static TextureRegion[][] texTiles;
    private static TextureRegion[][] texActors;
    private static TextureRegion[][] texEnemies;
    private static TextureRegion[][] texEffects;
    private static TextureRegion[][] texIcon7;
    private static Map<ImgUi, TextureRegion> texUi;

    public static void load() {
        texTiles = loadTilesFromFile("png/tiles.png", Global.tilePixelSize);
        texActors = loadTilesFromFile("png/actors.png", Global.tilePixelSize);
        texEnemies = loadTilesFromFile("png/enemies.png", Global.tilePixelSize);
        texEffects = loadTilesFromFile("png/effects.png", Global.tilePixelSize);
        texIcon7 = loadTilesFromFile("png/icon7.png", 7);
        texUi = loadTilesUi();
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

    public static TextureRegion toTex(ImgUi img) {
        return texUi.get(img);
    }

    private static TextureRegion[][] loadTilesFromFile(String path, int tileSize) {
        TextureRegion[][] dest = TextureRegion.split(new Texture(Gdx.files.internal(path)), tileSize, tileSize);
        Arrays.stream(dest).forEach(texArray -> Arrays.stream(texArray).forEach(tex -> tex.flip(false, true)));
        return dest;
    }

    private static Map<ImgUi, TextureRegion> loadTilesUi() {
        String path = "png/ui.png";
        Texture tex = new Texture(Gdx.files.internal(path));
        Map<ImgUi, TextureRegion> dest = new EnumMap<>(ImgUi.class);

        Arrays.stream(ImgUi.values()).forEach(img -> {
            dest.put(img, new TextureRegion(tex, img.getX(), img.getY(), img.getW(), img.getH()));
            dest.get(img).flip(false, true);
        });

        return dest;
    }
}
