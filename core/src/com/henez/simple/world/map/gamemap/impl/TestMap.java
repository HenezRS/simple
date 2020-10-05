package com.henez.simple.world.map.gamemap.impl;

import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.tiles.TileGroup;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.map.tiles.TilePool;

public class TestMap extends GameMap {
    public TestMap() {
        super("map_0", Colors.black);
    }

    @Override
    protected void loadTileGroups() {
        tileGroup.put(TileGroup.floor, new TilePool(ImgTiles.floor));
        tileGroup.put(TileGroup.wall_1, new TilePool(ImgTiles.cave_wall_1, ImgTiles.cave_wall_2, ImgTiles.cave_wall_3, ImgTiles.cave_wall_4, ImgTiles.cave_wall_5, ImgTiles.cave_wall_6));
        tileGroup.put(TileGroup.wall_2, new TilePool(ImgTiles.cave_background_wall_1));
        tileGroup.put(TileGroup.wall_3, new TilePool(ImgTiles.cave_spike_1, ImgTiles.cave_spike_2));
        tileGroup.put(TileGroup.wall_4, new TilePool(ImgTiles.cave_background_spike_1, ImgTiles.cave_background_spike_2));
        tileGroup.put(TileGroup.ground_1, new TilePool(ImgTiles.cave_ground_1, ImgTiles.cave_ground_2, ImgTiles.cave_ground_3));
        tileGroup.put(TileGroup.ground_2, new TilePool(ImgTiles.cave_mushroom_1, ImgTiles.cave_mushroom_2, ImgTiles.cave_mushroom_3)
                .withBasicChance(0.55f));
        tileGroup.put(TileGroup.ground_3, new TilePool(ImgTiles.cave_remains_1));
        tileGroup.put(TileGroup.loot, new TilePool(ImgTiles.sack));
        tileGroup.put(TileGroup.bigloot, new TilePool(ImgTiles.chest));
        tileGroup.put(TileGroup.bridge_1, new TilePool(ImgTiles.cave_bridge_1, ImgTiles.cave_bridge_2)
                .withBasicChance(0.8f));
        tileGroup.put(TileGroup.bridge_2, new TilePool(ImgTiles.cave_bridge_1, ImgTiles.cave_bridge_2)
                .withBasicChance(0.8f)
                .withRotation(90));
        tileGroup.put(TileGroup.down, new TilePool(ImgTiles.stairs_down));
        tileGroup.put(TileGroup.up, new TilePool(ImgTiles.stairs_up));
    }
}
