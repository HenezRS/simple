package com.henez.simple.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.*;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import com.henez.simple.global.Global;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Atlas {
    private static TextureRegion[][] texTiles;
    private static TextureRegion[][] texActors;
    private static TextureRegion[][] texEnemies;
    private static TextureRegion[][] texEnemies32;
    private static TextureRegion[][] texEnemies32Portrait;
    private static TextureRegion[][] texEffects;
    private static TextureRegion[][] texIconSkills;
    private static TextureRegion[][] texIcon7;
    private static TextureRegion[][] texIcon14;
    private static TextureRegion[][] texIconTactics;
    private static Map<ImgUi, TextureRegion> texUi;
    private static Map<ImgBackground, TextureRegion> texBackground;

    public static void load() {
        texTiles = loadTilesFromFile("png/tiles.png", Global.tilePixelSize);
        texActors = loadTilesFromFile("png/actors.png", Global.tilePixelSize);
        texEnemies = loadTilesFromFile("png/enemies.png", Global.tilePixelSize);
        texEnemies32 = loadTilesFromFile("png/enemies32.png", Global.tilePixelSize * 2);
        texEnemies32Portrait = createPortraits("png/enemies32.png");
        texEffects = loadTilesFromFile("png/effects.png", Global.tilePixelSize);
        texIconSkills = loadTilesFromFile("png/iconSkills.png", Global.tilePixelSize);
        texIcon7 = loadTilesFromFile("png/icon7.png", 7);
        texIcon14 = loadTilesFromFile("png/icon14.png", 14);
        texIconTactics = loadTilesFromFile("png/iconTactics.png", 14);
        texUi = loadTilesUi();
        texBackground = loadTilesBackground();
    }

    public static TextureRegionEnhanced toTex(ImgTiles img) {
        return new TextureRegionEnhanced(texTiles[img.getY()][img.getX()]);
    }

    public static TextureRegionEnhanced toTex(ImgActors img) {
        return new TextureRegionEnhanced(texActors[img.getY()][img.getX()]);
    }

    public static TextureRegionEnhanced toTex(ImgEnemies img) {
        return new TextureRegionEnhanced(texEnemies[img.getY()][img.getX()]);
    }

    public static TextureRegionEnhanced toTex(ImgEnemies32 img) {
        return new TextureRegionEnhanced(texEnemies32[img.getY()][img.getX()]);
    }

    public static TextureRegionEnhanced toTexPortrait(ImgEnemies32 img) {
        return new TextureRegionEnhanced(texEnemies32Portrait[img.getY()][img.getX()]);
    }

    public static TextureRegionEnhanced toTex(ImgEffects img) {
        return new TextureRegionEnhanced(texEffects[img.getY()][img.getX()]);
    }

    public static TextureRegionEnhanced toTex(ImgIconSkills img) {
        return new TextureRegionEnhanced(texIconSkills[img.getY()][img.getX()]);
    }

    public static TextureRegion toTex(ImgIcon7 img) {
        return texIcon7[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgIcon14 img) {
        return texIcon14[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgIconTactics img) {
        return texIconTactics[img.getY()][img.getX()];
    }

    public static TextureRegion toTex(ImgUi img) {
        return texUi.get(img);
    }

    public static TextureRegion toTex(ImgBackground img) {
        return texBackground.get(img);
    }

    private static TextureRegion[][] createPortraits(String path) {
        Texture t = new Texture(Gdx.files.internal(path));
        int w = texEnemies32.length;
        int h = texEnemies32[0].length;
        TextureRegion[][] dest = new TextureRegion[w][h];
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                dest[i][j] = new TextureRegion(t, (13) + (i * 32), (7) + (j * 32), 16, 16);
                dest[i][j].flip(false, true);
            }
        }
        return dest;
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

    private static Map<ImgBackground, TextureRegion> loadTilesBackground() {
        String path = "png/{name}.png";
        Map<ImgBackground, TextureRegion> dest = new EnumMap<>(ImgBackground.class);

        Arrays.stream(ImgBackground.values()).forEach(img -> {
            Texture tex = new Texture(Gdx.files.internal(path.replace("{name}", img.getPathName())));
            dest.put(img, new TextureRegion(tex, 0, 0, Global.cameraPixelW, Global.cameraPixelH));
            dest.get(img).flip(false, true);
        });

        return dest;
    }
}
