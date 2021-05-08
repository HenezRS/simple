package com.henez.simple.utils;

import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Facing;
import com.henez.simple.global.Global;
import com.henez.simple.world.mapobjects.MapObject;

public class RectUtils {
    private RectUtils() {
    }

    public static Rect get(MapObject mapObject) {
        return new Rect(mapObject.getX(), mapObject.getY(), Global.tilePixelSize, Global.tilePixelSize);
    }

    public static Rect borderLeft(Rect rect) {
        return new Rect(rect.x, rect.y, 1, rect.h);
    }

    public static Rect borderRight(Rect rect) {
        return new Rect(rect.xx - 1, rect.y, 1, rect.h);
    }

    public static Rect borderUp(Rect rect) {
        return new Rect(rect.x + 1, rect.y, rect.w - 2, 1);
    }

    public static Rect borderDown(Rect rect) {
        return new Rect(rect.x + 1, rect.yy - 1, rect.w - 2, 1);
    }

    public static Rect borderLeftFull(Rect rect) {
        return new Rect(rect.x - 1, rect.y - 0, 1, rect.h + 0);
    }

    public static Rect borderRightFull(Rect rect) {
        return new Rect(rect.xx - 0, rect.y, 1, rect.h - 0);
    }

    public static Rect borderUpFull(Rect rect) {
        return new Rect(rect.x + 0, rect.y - 1, rect.w + 0, 1);
    }

    public static Rect borderDownFull(Rect rect) {
        return new Rect(rect.x + 0, rect.yy - 0, rect.w, 1);
    }

    public static Rect borderFull(Rect rect, Facing facing) {
        switch (facing) {
        case RIGHT:
            return borderRightFull(rect);
        case UP:
            return borderUpFull(rect);
        case LEFT:
            return borderLeftFull(rect);
        case DOWN:
            return borderDownFull(rect);
        }
        return new Rect(0, 0);
    }
}
