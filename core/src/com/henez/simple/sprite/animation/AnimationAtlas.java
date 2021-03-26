package com.henez.simple.sprite.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

import static com.henez.simple.atlas.imgs.ImgEffects.*;
import static com.henez.simple.global.Global.SEC16;

@Getter
public enum AnimationAtlas {
    SLASH(SEC16, 3, slash_0.asTex(), slash_1.asTex(), slash_2.asTex(), slash_3.asTex(), slash_4.asTex(), slash_5.asTex()),
    MISSILE(SEC16, 0, missile_0.asTex(), missile_1.asTex()),
    ;

    private float delay;
    private int keyFrame;
    private TextureRegion[] textureRegions;

    AnimationAtlas(float delay, int keyFrame, TextureRegion... textureRegions) {
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
