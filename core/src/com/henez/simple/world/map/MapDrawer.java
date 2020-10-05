package com.henez.simple.world.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.global.Global;
import com.henez.simple.world.map.tiles.Tile;

public class MapDrawer {
    public MapDrawer() {
    }

    public TextureRegion draw(int width, int height, Color backColor, GameList<Tile> tilesMap, GameList<Tile> tilesObj) {
        int w = width * Global.tilePixelSize;
        int h = height * Global.tilePixelSize;

        Pixmap pixmapMap = new Pixmap(w, h, Pixmap.Format.RGB888);
        pixmapMap.setColor(backColor);
        pixmapMap.fill();

        Texture map = new Texture(w, h, Pixmap.Format.RGB888);
        tilesMap.forEach(tile -> drawTile(pixmapMap, tile));
        tilesObj.forEach(tile -> drawTile(pixmapMap, tile));

        map.draw(pixmapMap, 0, 0);
        pixmapMap.dispose();
        TextureRegion tex = new TextureRegion(map);
        tex.flip(false, true);
        return tex;
    }

    private void drawTile(Pixmap pixmapMap, Tile tile) {
        if (tile.isDrawable()) {
            Pixmap pm = toPixmap(tile.getTex());
            if (tile.getRotation() != 0) {
                pm = rotatePixmap(pm);
            }
            pixmapMap.drawPixmap(pm, tile.getX(), tile.getY());
            pm.dispose();
        }
    }

    private Pixmap toPixmap(TextureRegion textureRegion) {
        // texture flipping fix
        int yy = -textureRegion.getRegionHeight();

        TextureData textureData = textureRegion.getTexture().getTextureData();
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        Pixmap pixmap = new Pixmap(
                textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(),
                textureData.getFormat()
        );
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.setColor(0, 0, 0, 0.0f);
        pixmap.fill();
        Pixmap pmFrom = textureData.consumePixmap();

        for (int i = 0; i < pixmap.getWidth(); ++i) {
            for (int j = 0; j < pixmap.getHeight(); ++j) {
                int pix = pmFrom.getPixel(textureRegion.getRegionX() + i, textureRegion.getRegionY() + yy + j);
                pixmap.drawPixel(i, j, pix);
            }
        }

        pmFrom.dispose();
        textureData.disposePixmap();

        return pixmap;
    }

    private Pixmap rotatePixmap(Pixmap srcPix) {
        final int width = srcPix.getWidth();
        final int height = srcPix.getHeight();
        Pixmap rotatedPix = new Pixmap(height, width, srcPix.getFormat());

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                rotatedPix.drawPixel(x, y, srcPix.getPixel(y, x));
            }
        }

        srcPix.dispose();
        return rotatedPix;
    }
}
