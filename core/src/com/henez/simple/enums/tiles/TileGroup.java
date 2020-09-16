package com.henez.simple.enums.tiles;

import lombok.Getter;

@Getter
public enum TileGroup {
    none(0,0,TileType.none),
    floor(0,1,TileType.floor),
    wall_1(0,2,TileType.wall),
    wall_2(1,2,TileType.wall),
    wall_3(2,2,TileType.wall),
    wall_4(3,2,TileType.wall),
    ground_1(0,3,TileType.ground),
    ground_2(1,3,TileType.ground),
    ground_3(2,3,TileType.ground),
    ground_4(3,3,TileType.ground),
    loot(0,4,TileType.chest_common),
    bigloot(0,5,TileType.chest_rare),
    bridge_1(0,6,TileType.bridge),
    bridge_2(1,6,TileType.bridge),
    down(0,7,TileType.down),
    up(0,8,TileType.up),
    ;

    public static int TILE_GROUP_W = 10;

    private int x;
    private int y;
    private TileType tileType;

    TileGroup(int x, int y, TileType tileType) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;
    }
}
