package com.henez.simple.sprite.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

@Getter
public class AnimationDynamic {
    private float delay;
    private float speed = 1.0f;
    private int keyFrame;
    private TextureRegion[] textureRegions;

    public AnimationDynamic(float delay, int keyFrame, TextureRegion... textureRegions) {
        this.delay = delay;
        this.keyFrame = keyFrame;
        this.textureRegions = textureRegions;
    }

    public AnimationDynamic(float delay, float speed, int keyFrame, TextureRegion... textureRegions) {
        this.speed = speed;
        this.delay = delay;
        this.keyFrame = keyFrame;
        this.textureRegions = textureRegions;
    }
}
