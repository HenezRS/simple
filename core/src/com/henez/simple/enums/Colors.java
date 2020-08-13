package com.henez.simple.enums;

import com.badlogic.gdx.graphics.Color;

public enum Colors {
    black(new Color(0x000000ff)),
    black_50(black.withAlpha(0.5f)),
    text_default(new Color(0xf6d6bdff)),

    pal_dark_0(new Color(0x08141eff)),
    pal_dark_1(new Color(0x20394fff)),
    pal_dark_2(new Color(0x4e495fff)),
    pal_dark_3(new Color(0x997577ff)),

    ;

    public Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color withAlpha(float a) {
        return new Color(color.r, color.g, color.b, a);
    }

    public Color mul(float mul, float a) {
        return new Color(color.r * mul, color.g * mul, color.b * mul, a);
    }

    public float[] getAsFloats3() {
        return new float[] { color.r, color.g, color.b };
    }
}