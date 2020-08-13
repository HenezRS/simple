package com.henez.simple.atlas;

public enum ImgTiles {
    NONE(0, 0),
    GRASS(1, 0),
    TREE(2, 0),
    HILL(3, 0),
    MOUNTAIN(4, 0),
    WATER(5, 0),
    ;

    public final int x;
    public final int y;

    ImgTiles(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
