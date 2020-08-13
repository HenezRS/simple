package com.henez.simple.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Atlas {
    private static Texture textureTiles;
    private static TextureRegion[][] textureRegionTiles;

    public static void load() {
        textureTiles = new Texture(Gdx.files.internal("png/tiles.png"));
        textureRegionTiles = TextureRegion.split(textureTiles, 16, 16);
        for (int j = 0; j < textureRegionTiles.length; ++j) {
            for (int i = 0; i < textureRegionTiles[j].length; ++i) {
                textureRegionTiles[j][i].flip(false, true);
            }
        }
    }

    public static TextureRegion getImgTiles(ImgTiles img) {
        return textureRegionTiles[img.y][img.x];
    }
}
