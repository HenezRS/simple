package com.henez.simple.sprite.animation;

import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public class AnimationDynamic {
    private float delay;
    private float speed;
    private int keyFrame;
    private TextureRegionEnhanced[] textureRegions;
    private float frameFirstFaded = 99;
    private float fadePercentPerFrame;

    public AnimationDynamic(float delay, int keyFrame, TextureRegionEnhanced... textureRegions) {
        speed = 1.0f;
        this.delay = delay;
        this.keyFrame = keyFrame;
        this.textureRegions = textureRegions;
    }

    public AnimationDynamic withSpeed(float speed) {
        this.speed = speed;
        return this;
    }
}
