package com.henez.simple.atlas.imgs;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public enum ImgEnemies32 {
    octobig_idle_0(0, 0),
    octobig_idle_1(1, 0),
    octobig_attack_0(2, 0),
    octobig_attack_1(3, 0),
    ;

    private final int x;
    private final int y;

    ImgEnemies32(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionEnhanced asTex() {
        return Atlas.toTex(this);
    }

    public TextureRegionEnhanced asTexPortrait() {
        return Atlas.toTexPortrait(this);
    }
}
