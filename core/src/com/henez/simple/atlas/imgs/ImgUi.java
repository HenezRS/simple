package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgUi {
    button(0, 0, 20, 20),
    button_active(20, 0, 20, 20),
    button_hover(40, 0, 20, 20),
    arrow_right(60, 0, 15, 8),
    arrow_right_grey(60, 8, 15, 8);

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    ImgUi(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
