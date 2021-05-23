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

    public static Rect borderLeftCornered(Rect rect) {
        return new Rect(rect.x, rect.y + 1, 1, rect.h - 2);
    }

    public static Rect borderRightCornered(Rect rect) {
        return new Rect(rect.xx - 1, rect.y +1, 1, rect.h - 2);
    }

    public static Rect borderUpCornered(Rect rect) {
        return new Rect(rect.x + 0 +1, rect.y, rect.w - 2, 1);
    }

    public static Rect borderDownCornered(Rect rect) {
        return new Rect(rect.x + 0 +1, rect.yy - 1, rect.w - 2, 1);
    }

    public static Rect borderCornered(Rect rect, Facing facing) {
        switch (facing) {
        case RIGHT:
            return borderRightCornered(rect);
        case UP:
            return borderUpCornered(rect);
        case LEFT:
            return borderLeftCornered(rect);
        case DOWN:
            return borderDownCornered(rect);
        }
        return new Rect(0, 0);
    }
}
