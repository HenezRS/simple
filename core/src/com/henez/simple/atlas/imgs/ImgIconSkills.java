package com.henez.simple.atlas.imgs;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public enum ImgIconSkills {
    missing,
    attack,
    flame,
    ice_spike;

    private final int x;
    private final int y;

    ImgIconSkills() {
        this.x = this.ordinal() % 16;
        this.y = this.ordinal() / 16;
    }

    public TextureRegionEnhanced asTex() {
        return Atlas.toTex(this);
    }
}
