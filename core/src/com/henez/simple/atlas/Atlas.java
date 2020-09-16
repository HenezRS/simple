package com.henez.simple.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Atlas {
    private static TextureRegion[][] textureRegionTiles;

    public static void load() {
        Texture textureTiles = new Texture(Gdx.files.internal("png/tiles.png"));
        textureRegionTiles = TextureRegion.split(textureTiles, 16, 16);
        for (TextureRegion[] textureRegionTile : textureRegionTiles) {
            for (TextureRegion textureRegion : textureRegionTile) {
                textureRegion.flip(false, true);
            }
        }
    }

    public static TextureRegion getImgTiles(ImgTiles img) {
        return textureRegionTiles[img.getY()][img.getX()];
    }
}
