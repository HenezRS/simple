package com.henez.simple.utils;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.mapobjects.MapObject;

public class MapObjectUtils {
    private MapObjectUtils() {
    }

    public static boolean positionIsEmpty(int gx, int gy, GameList<MapObject> objects) {
        return objects.stream().noneMatch(object -> object.getGx() == gx && object.getGy() == gy);
    }
}
