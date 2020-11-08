package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.ImgEffects;
import lombok.Getter;

@Getter
public enum AnimationAtlas {
    SLASH(16.0f, ImgEffects.slash_0.asTex(), ImgEffects.slash_1.asTex(), ImgEffects.slash_2.asTex(), ImgEffects.slash_3.asTex(), ImgEffects.slash_4.asTex(), ImgEffects.slash_5.asTex()),
    ;

    private float delay;
    private float speed;
    private TextureRegion[] textureRegions;

    AnimationAtlas(float delay, TextureRegion... textureRegions) {
        speed = 1.0f;
        this.delay = delay;
        this.textureRegions = textureRegions;
    }
}
