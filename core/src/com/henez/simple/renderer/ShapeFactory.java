package com.henez.simple.renderer;

import com.henez.simple.enums.Colors;

public final class ShapeFactory {
    private ShapeFactory() {
    }

    public static void channellingDivider(Shaper shape, int x, int y) {
        shape.rect(x, y, 1, 2, Colors.white.color);
    }
}
