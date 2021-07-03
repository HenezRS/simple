package com.henez.simple.utils;

import com.henez.simple.misc.XY;

public final class XYUtils {
    private XYUtils() {
    }

    public static boolean equal(XY xy, XY xy2) {
        return xy.getX() == xy2.getX() && xy.getY() == xy2.getY();
    }
}
