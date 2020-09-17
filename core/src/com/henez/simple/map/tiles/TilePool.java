package com.henez.simple.map.tiles;

import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.datastructures.GameList;

import java.util.Arrays;

public class TilePool {
    private GameList<ImgTiles> tiles;

    public TilePool(ImgTiles... tiles) {
        this.tiles = (GameList<ImgTiles>)Arrays.asList(tiles);
    }

    public ImgTiles getRandom() {
        return tiles.random();
    }
}
