package com.henez.simple.map.gamemap.impl;

import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.enums.tiles.TileGroup;
import com.henez.simple.map.gamemap.GameMap;
import com.henez.simple.map.tiles.TilePool;

public class TestMap extends GameMap {
    public TestMap() {
        super("map_0");
    }

    @Override
    protected void loadTileGroups() {
        tileGroup.put(TileGroup.floor,new TilePool(ImgTiles.floor));
        tileGroup.put(TileGroup.wall_1,new TilePool(ImgTiles.cave_wall_1, ImgTiles.cave_wall_2, ImgTiles.cave_wall_3, ImgTiles.cave_wall_4, ImgTiles.cave_wall_5, ImgTiles.cave_wall_6));
    }
}
