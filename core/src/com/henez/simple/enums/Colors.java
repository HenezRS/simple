package com.henez.simple.enums;

import com.badlogic.gdx.graphics.Color;

public enum Colors {
    white(new Color(0xffffffff)),
    white_50(white.withAlpha(0.5f)),
    white_30(white.withAlpha(0.3f)),
    black(new Color(0x000000ff)),
    black_50(black.withAlpha(0.5f)),
    text_default(new Color(0xe5c79eff)),
    text_hover(new Color(0xffffefff)),
    text_click(new Color(0xffffefff)),
    text_player(new Color(0x49aac4ff)),
    text_enemy(new Color(0xc46a49ff)),

    ui_back(new Color(0x261c18ff)),
    ui_bar_back(new Color(0x665742ff)),
    ui_frame(new Color(0x998042ff)),
    ui_bar_front(new Color(0xffb8aeff)),

    //ui_back_blue(new Color(0x1f2326ff)),
    ui_back_blue(ui_back),
    ui_frame_blue(new Color(0x295e5bff)),

    //ui_back_red(new Color(0x3b2525ff)),
    ui_back_red(ui_back),
    ui_frame_red(new Color(0x823838ff)),

    hp(new Color(0x86cf8bff)),
    hp_bar_back(new Color(0x704950ff)),
    mp(new Color(0x86a6cfff)),
    xp(new Color(0xac91baff)),

    castbar(new Color(0xae5bcfff)),
    channelbar(new Color(0x5baecfff)),

    pal_dark_0(new Color(0x08141eff)),

    pal_dark_1(new Color(0x20394fff)),

    pal_dark_2(new Color(0x4e495fff)),

    pal_dark_3(new Color(0x997577ff)),

    red(new Color(0xffaa77ff)),
    red_50(red.withAlpha(0.5f)),
    blue(new Color(0x77aaffff)),
    yellow(new Color(0xffff77ff)),
    green(new Color(0xa7ff78ff)),
    green_50(green.withAlpha(0.5f)),
    ;

    public Color color;

    Colors(Color color) {
        this.color = color;
    }

    Colors(Colors colors) {
        this.color = colors.color;
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