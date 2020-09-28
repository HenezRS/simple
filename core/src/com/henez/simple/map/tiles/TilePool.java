package com.henez.simple.map.tiles;

import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.datastructures.GameList;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TilePool {
    private GameList<ImgTiles> tiles;

    public TilePool(ImgTiles... tiles) {
        this.tiles = Arrays.stream(tiles).collect(Collectors.toCollection(GameList::new));
    }

    public ImgTiles getRandom() {
        return tiles.random();
    }
}
