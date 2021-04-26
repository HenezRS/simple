package com.henez.simple.sprite.animation;

import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

import static com.henez.simple.atlas.imgs.ImgEffects.*;
import static com.henez.simple.global.Global.SEC12;
import static com.henez.simple.global.Global.SEC16;
import static com.henez.simple.global.Global.SEC8;

@Getter
public enum EffectAtlas {
    SLASH(SEC16, 3, slash_0.asTex(), slash_1.asTex(), slash_2.asTex(), slash_3.asTex(), slash_4.asTex(), slash_5.asTex()),
    MISSILE(SEC12, 0, missile_0.asTex(), missile_1.asTex()),
    FLAME(SEC8, 1, flame_0.asTex(), flame_1.asTex(), flame_0.asTex(), flame_1.asTex()),
    BOLT_MISSILE(SEC8, 0, bolt_missile_0.asTex(), bolt_missile_1.asTex()),
    BOLT(SEC8, 1, bolt_0.asTex(), bolt_1.asTex(), bolt_0.asTex(), bolt_1.asTex()),
    ICE_MISSILE(SEC16, 0, ice_missile_0.asTex(), ice_missile_1.asTex()),
    ICE(SEC8, 2, ice_0.asTex(), ice_1.asTex(), ice_2.asTex(), ice_2.asTex(), ice_2.asTex(), ice_2.asTexWith(0.8f), ice_2.asTexWith(0.6f), ice_2.asTexWith(0.4f), ice_2.asTexWith(0.2f)),
    ;

    private float delay;
    private int keyFrame;
    private TextureRegionEnhanced[] textureRegions;

    EffectAtlas(float delay, int keyFrame, TextureRegionEnhanced... textureRegions) {
        this.delay = delay;
        this.keyFrame = keyFrame;
        this.textureRegions = textureRegions;
    }

    public AnimationDynamic toDynamic() {
        return new AnimationDynamic(delay, keyFrame, textureRegions);
    }

    public AnimationDynamic toDynamicWithSpeed(float newSpeed) {
        return new AnimationDynamic(delay, keyFrame, textureRegions).withSpeed(newSpeed);
    }
}
