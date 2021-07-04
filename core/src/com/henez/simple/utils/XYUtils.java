package com.henez.simple.utils;

import com.henez.simple.misc.XY;
import com.henez.simple.world.mapobjects.MapObject;

public final class XYUtils {
    private XYUtils() {
    }

    public static XY from(MapObject obj) {
        return new XY(obj.getGx(), obj.getGy());
    }

    public static boolean equal(XY xy, XY xy2) {
        return xy.getX() == xy2.getX() && xy.getY() == xy2.getY();
    }

    public static boolean isWithinSquare(XY pos, XY pos2) {
        return (pos.getX() == pos2.getX() && pos.getY() == pos2.getY()) ||
                (pos.getX() == pos2.getX() - 1 && pos.getY() == pos2.getY()) ||
                (pos.getX() == pos2.getX() - 1 && pos.getY() == pos2.getY() - 1) ||
                (pos.getX() == pos2.getX() && pos.getY() == pos2.getY() - 1);
    }
}
