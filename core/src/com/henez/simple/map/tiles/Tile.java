package com.henez.simple.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.global.Global;
import lombok.Getter;

@Getter
public class Tile {

    private int x;
    private int y;
    private int gx;
    private int gy;
    private TextureRegion tex;

    public Tile(int gx, int gy, ImgTiles img) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx* Global.tilePixelSize;
        this.y = gy* Global.tilePixelSize;
        this.tex = Atlas.getImgTiles(img);
    }
}
