package com.henez.simple.enums;

public enum TileType {
    none(0, false),
    floor(1, true),
    wall(2, false),
    ;

    public int index;
    public boolean walkable;

    TileType(int index, boolean walkable) {
        this.index = index;
        this.walkable = walkable;
    }
}
