package com.henez.simple.sprite.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public class AnimationDynamic {
    private float delay;
    private float speed;
    private int keyFrame;
    private TextureRegion[] textureRegions;

    public AnimationDynamic(float delay, int keyFrame, TextureRegion... textureRegions) {
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
