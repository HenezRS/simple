package com.henez.simple.atlas.imgs;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.datastructures.TextureRegionEnhanced;
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
    bolt_missile_0(11, 0),
    bolt_missile_1(12, 0),
    bolt_0(13, 0),
    bolt_1(14, 0),
    ice_missile_0(0, 1),
    ice_missile_1(1, 1),
    ice_0(2, 1),
    ice_1(3, 1),
    ice_2(4, 1),
    ;

    private final int x;
    private final int y;

    ImgEffects(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionEnhanced asTex() {
        return Atlas.toTex(this);
    }

    public TextureRegionEnhanced asTexWith(float alpha) {
        TextureRegionEnhanced tex = Atlas.toTex(this);
        tex.setAlpha(alpha);
        return tex;
    }
}
