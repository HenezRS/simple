package com.henez.simple.map.gamemap;

import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.tiles.TileGroup;
import com.henez.simple.global.Global;
import com.henez.simple.map.mapdata.MapData;
import com.henez.simple.map.mapdata.MapDataReader;
import com.henez.simple.map.tiles.Tile;
import com.henez.simple.map.tiles.TilePool;
import com.henez.simple.renderer.Batcher;

import java.util.HashMap;
import java.util.Map;

public abstract class GameMap {

    protected MapData mapData;
    protected Map<TileGroup, TilePool> tileGroup;
    protected GameList<Tile> tilesMap;
    protected GameList<Tile> tilesObj;

    public GameMap(String mapFileName) {
        this.mapData = new MapDataReader().read(mapFileName);
        this.tileGroup = new HashMap<>();
        tileGroup.put(TileGroup.error,new TilePool(ImgTiles.error));
        tileGroup.put(TileGroup.none,new TilePool(ImgTiles.none));
        loadTileGroups();
        loadTilesFromMapData();
    }

    protected abstract void loadTileGroups();

    private void loadTilesFromMapData() {
        tilesMap = new GameList<>();
        tilesObj = new GameList<>();
        for(int j=0; j<mapData.getHeight(); ++j) {
            for(int i=0; i<mapData.getWidth(); ++i) {
                tilesMap.add(new Tile(i,j,getRandomTileFromGroup(mapData.getTileIndex(i,j, Global.LAYER_MAP))));
                tilesObj.add(new Tile(i,j,getRandomTileFromGroup(mapData.getTileIndex(i,j, Global.LAYER_OBJ))));
            }
        }
    }

    public void draw(Batcher batch) {
        tilesMap.forEach(tile -> {
            batch.draw(tile.getTex(),tile.getX(),tile.getY());
        });
    }

    private ImgTiles getRandomTileFromGroup(int index) {
        TileGroup group = TileGroup.fromIndex(index);
        if(!tileGroup.containsKey(group)) {
            System.out.printf("No tiles for tileGroup '%s'%n",group.name());
            group = TileGroup.error;
        }
        return tileGroup.get(group).getRandom();
    }
}
