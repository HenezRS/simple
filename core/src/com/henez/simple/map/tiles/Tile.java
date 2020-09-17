package com.henez.simple.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.ImgTiles;
import lombok.Getter;

@Getter
public class Tile {

    private int x;
    private int y;
    private TextureRegion tex;

    public Tile(int x, int y, ImgTiles img) {
        this.x = x;
        this.y = y;
        this.tex = Atlas.getImgTiles(img);
    }
}
