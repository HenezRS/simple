package com.henez.simple.enums;

import com.badlogic.gdx.graphics.Color;

public enum Colors {
    white(new Color(0xffffffff)),
    black(new Color(0x000000ff)),
    green(new Color(0x00ff00ff)),
    black_50(black.withAlpha(0.5f)),
    text_default(new Color(0xf6d6bdff)),

    ui_back(new Color(0x261c18ff)),
    ui_bar_back(new Color(0x665742ff)),
    ui_frame(new Color(0x998042ff)),
    ui_bar_front(new Color(0xffb8aeff)),

    hp(new Color(0x86cf8bff)),
    hp_bar_back(new Color(0x704950ff)),
    mp(new Color(0x86a6cfff)),
    xp(new Color(0xac91baff)),

    castbar(new Color(0xae5bcfff)),

    pal_dark_0(new Color(0x08141eff)),

    pal_dark_1(new Color(0x20394fff)),

    pal_dark_2(new Color(0x4e495fff)),

    pal_dark_3(new Color(0x997577ff)),

    red(new Color(0xffaa77ff)),

    blue(new Color(0x77aaffff)),
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