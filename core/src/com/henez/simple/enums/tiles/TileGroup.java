package com.henez.simple.enums.tiles;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TileGroup {
    error(-1, TileType.back),
    empty(0, TileType.empty),
    back(1, TileType.back),
    floor(11, TileType.floor),
    wall_1(21, TileType.wall),
    wall_2(22, TileType.wall),
    wall_3(23, TileType.wall),
    wall_4(24, TileType.wall),
    ground_1(31, TileType.ground),
    ground_2(32, TileType.ground),
    ground_3(33, TileType.ground),
    ground_4(34, TileType.ground),
    loot(41, TileType.chest_common),
    bigloot(51, TileType.chest_rare),
    bridge_1(61, TileType.bridge),
    bridge_2(62, TileType.bridge),
    down(71, TileType.down),
    up(81, TileType.up),
    ;

    public static int TILE_GROUP_W = 10;

    private int index;
    private TileType tileType;

    TileGroup(int index, TileType tileType) {
        this.index = index;
        this.tileType = tileType;
    }

    public static TileGroup fromIndex(int index) {
        return Arrays.stream(TileGroup.values()).filter(t -> t.index == index).findFirst().orElse(TileGroup.error);
    }
}
