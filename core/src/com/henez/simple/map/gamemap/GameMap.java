package com.henez.simple.map.gamemap;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.tiles.TileGroup;
import com.henez.simple.map.mapdata.MapData;
import com.henez.simple.map.mapdata.MapDataReader;
import com.henez.simple.map.tiles.Tile;
import com.henez.simple.map.tiles.TilePool;

import java.util.HashMap;
import java.util.Map;

public abstract class GameMap {

    protected MapData mapData;
    protected Map<TileGroup, TilePool> tileGroup;
    protected GameList<Tile> tiles;

    public GameMap(String mapFileName) {
        this.mapData = new MapDataReader().read(mapFileName);
        this.tileGroup = new HashMap<>();
        loadTileGroups();
    }

    protected abstract void loadTileGroups();

    public void draw() {

    }
}
