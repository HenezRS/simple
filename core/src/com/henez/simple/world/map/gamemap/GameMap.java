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
    protected GameList<String> missingGroupByNames;
    protected TextureRegion mapTex;
    protected Colors backColor;

    public GameMap(String mapFileName, Colors backColor) {
        this.mapData = new MapDataReader().read(mapFileName);
        this.backColor = backColor;
        this.tileGroup = new HashMap<>();
        this.missingGroupByNames = new GameList<>();
        tileGroup.put(TileGroup.error, new TilePool(ImgTiles.error));
        tileGroup.put(TileGroup.none, new TilePool(ImgTiles.none).dontDraw());
        loadTileGroups();
        loadTilesFromMapData();
        drawToMapTex();
    }

    protected abstract void loadTileGroups();

    private void loadTilesFromMapData() {
        tilesMap = new GameList<>();
        tilesObj = new GameList<>();
        for (int j = 0; j < mapData.getHeight(); ++j) {
            for (int i = 0; i < mapData.getWidth(); ++i) {
                TilePool tilePool = getTilePoolForIndex(mapData.getTileIndex(i, j, Global.LAYER_MAP));
                tilesMap.add(new Tile(i, j, tilePool));

                tilePool = getTilePoolForIndex(mapData.getTileIndex(i, j, Global.LAYER_OBJ));
                tilesObj.add(new Tile(i, j, tilePool));
            }
        }
    }

    public void drawToMapTex() {
        mapTex = new MapDrawer().draw(mapData.getWidth(), mapData.getHeight(), backColor.color, tilesMap, tilesObj);
    }

    private TilePool getTilePoolForIndex(int index) {
        TileGroup group = TileGroup.fromIndex(index);
        if (!tileGroup.containsKey(group)) {
            if (!missingGroupByNames.contains(group.name())) {
                System.out.printf("No tiles for tileGroup '%s'%n", group.name());
                missingGroupByNames.add(group.name());
            }
            group = TileGroup.error;
        }

        return tileGroup.get(group);
    }
}
