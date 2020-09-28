package com.henez.simple.enums.tiles;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TileType {
    empty(0, false),
    none(1, false),
    floor(2, true),
    wall(3, false),
    ground(4, true),
    chest_common(5, false),
    chest_rare(6, false),
    bridge(7, true),
    down(8, true),
    up(9, true),
    ;

    private int index;
    private boolean walkable;

    TileType(int index, boolean walkable) {
        this.index = index;
        this.walkable = walkable;
    }

    public static TileType fromIndex(int index) {
        return Arrays.stream(TileType.values()).filter(t -> t.index==index).findFirst().orElse(TileType.empty);
    }
}
