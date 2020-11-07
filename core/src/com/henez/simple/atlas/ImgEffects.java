package com.henez.simple.atlas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public enum ImgEffects {
    slash_0(0, 0),
    slash_1(1, 0),
    slash_2(2, 0),
    slash_3(3, 0),
    slash_4(4, 0),
    slash_5(5, 0),
    ;

    private final int x;
    private final int y;

    ImgEffects(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
