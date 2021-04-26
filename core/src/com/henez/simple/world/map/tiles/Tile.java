package com.henez.simple.world.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.enums.tiles.TileGroup;
import com.henez.simple.enums.tiles.TileType;
import com.henez.simple.global.Global;
import lombok.Getter;

@Getter
public class Tile {

    private int x;
    private int y;
    private int gx;
    private int gy;
    private TileType type;
    private float rotation = 0f;
    private TextureRegion tex;
    private boolean drawable = true;

    public Tile(int gx, int gy, TilePool tilePool, TileGroup group) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Global.tilePixelSize;
        this.y = gy * Global.tilePixelSize;
        this.type = group.getTileType();
        drawable = tilePool.isDrawable();
        rotation = tilePool.getRotation();
        if (drawable) {
            this.tex = Atlas.toTex(tilePool.getRandom()).getTex();
        }
    }
}
