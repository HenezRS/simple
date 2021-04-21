package com.henez.simple.utils;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.map.tiles.TileDetail;

public class TileDetailUtils {
    private TileDetailUtils() {
    }

    public static boolean tilesAreSame(TileDetail a, TileDetail b) {
        return a.getGx() == b.getGx() && a.getGy() == b.getGy();
    }

    public static boolean tileWithin(TileDetail a, GameList<TileDetail> tiles) {
        return tiles.stream().anyMatch(b -> a.getGx() == b.getGx() && a.getGy() == b.getGy());
    }

    public static boolean tileNotWithin(TileDetail a, GameList<TileDetail> tiles) {
        return !tileWithin(a, tiles);
    }
}
