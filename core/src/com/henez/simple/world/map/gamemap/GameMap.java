package com.henez.simple.world.map.gamemap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.tiles.TileGroup;
import com.henez.simple.global.Global;
import com.henez.simple.world.map.MapDrawer;
import com.henez.simple.world.map.mapdata.MapData;
import com.henez.simple.world.map.mapdata.MapDataReader;
import com.henez.simple.world.map.tiles.Tile;
import com.henez.simple.world.map.tiles.TileDetail;
import com.henez.simple.world.map.tiles.TilePool;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class GameMap {

    protected MapData mapData;
    protected Map<TileGroup, TilePool> tileGroup;
    protected GameList<Tile> tilesMap;
    protected GameList<Tile> tilesObj;
    protected GameList<Tile> tilesDeco;
    protected GameList<String> missingGroupByNames;
    protected TextureRegion mapTex;
    protected Colors backColor;
    protected int startGx = 0;
    protected int startGy = 0;

    public GameMap(String mapFileName, Colors backColor) {
        this.mapData = new MapDataReader().read(mapFileName);
        this.backColor = backColor;
        this.tileGroup = new HashMap<>();
        this.missingGroupByNames = new GameList<>();
        tileGroup.put(TileGroup.error, new TilePool(ImgTiles.error));
        tileGroup.put(TileGroup.empty, new TilePool(ImgTiles.none).dontDraw());
        tileGroup.put(TileGroup.back, new TilePool(ImgTiles.none).dontDraw());
        loadTileGroups();
        loadTilesFromMapData();
        drawToMapTex();
    }

    public TileDetail getTileDetail(int gx, int gy) {
        return new TileDetail(tilesMap.get(getCoord(gx, gy)), tilesDeco.get(getCoord(gx, gy)), tilesObj.get(getCoord(gx, gy)));
    }

    protected abstract void loadTileGroups();

    private void loadTilesFromMapData() {
        tilesMap = new GameList<>();
        tilesDeco = new GameList<>();
        tilesObj = new GameList<>();
        TileGroup group;
        for (int j = 0; j < mapData.getHeight(); ++j) {
            for (int i = 0; i < mapData.getWidth(); ++i) {
                group = TileGroup.fromIndex(mapData.getTileIndex(i, j, Global.LAYER_MAP));
                TilePool tilePool = getTilePool(group);
                tilesMap.add(new Tile(i, j, tilePool, group));

                group = TileGroup.fromIndex(mapData.getTileIndex(i, j, Global.LAYER_DECO));
                tilePool = getTilePool(group);
                tilesDeco.add(new Tile(i, j, tilePool, group));
                if (group == TileGroup.up) {
                    startGx = i;
                    startGy = j;
                }

                group = TileGroup.fromIndex(mapData.getTileIndex(i, j, Global.LAYER_OBJ));
                tilePool = getTilePool(group);
                tilesObj.add(new Tile(i, j, tilePool, group));
            }
        }
    }

    public void drawToMapTex() {
        mapTex = new MapDrawer().draw(mapData.getWidth(), mapData.getHeight(), backColor.color, tilesMap, tilesDeco);
    }

    private TilePool getTilePool(TileGroup group) {
        if (!tileGroup.containsKey(group)) {
            if (!missingGroupByNames.contains(group.name())) {
                System.out.printf("No tiles for tileGroup '%s'%n", group.name());
                missingGroupByNames.add(group.name());
            }
            group = TileGroup.error;
        }

        return tileGroup.get(group);
    }

    private int getCoord(int gx, int gy) {
        return (gx) + (gy * mapData.getWidth());
    }
}
