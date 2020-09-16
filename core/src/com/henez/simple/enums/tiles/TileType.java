package com.henez.simple.enums.tiles;

import lombok.Getter;

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
}
