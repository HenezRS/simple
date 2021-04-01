package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgEffects {
    slash_0(0, 0),
    slash_1(1, 0),
    slash_2(2, 0),
    slash_3(3, 0),
    slash_4(4, 0),
    slash_5(5, 0),
    missile_particle(6, 0),
    missile_0(7, 0),
    missile_1(8, 0),
    flame_0(9, 0),
    flame_1(10, 0),
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
