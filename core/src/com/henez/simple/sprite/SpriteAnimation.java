package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.sprite.animation.AnimationAtlas;
import com.henez.simple.sprite.animation.AnimationDynamic;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class SpriteAnimation {
    private GameList<TextureRegion> texs;
    private float tick;
    private float delay;
    private float speed;
    private int currentFrame;
    private int frameCount;
    private boolean done = false;
    private int keyFrame = 0;
    private boolean keyFrameDone = false;
    private boolean keyFrameDoneThisFrame = false;

    public SpriteAnimation(float delay, TextureRegion... textureRegions) {
        init(delay, 1.0f, textureRegions);
    }

    public SpriteAnimation(float delay, float speed, TextureRegion... textureRegions) {
        init(delay, speed, textureRegions);
    }

    public SpriteAnimation(AnimationAtlas animationAtlas) {
        init(animationAtlas.getDelay(), animationAtlas.getSpeed(), animationAtlas.getTextureRegions());
        keyFrame = animationAtlas.getKeyFrame();
    }

    public SpriteAnimation(AnimationDynamic animationDynamic) {
        init(animationDynamic.getDelay(), animationDynamic.getSpeed(), animationDynamic.getTextureRegions());
        keyFrame = animationDynamic.getKeyFrame();
    }

    private void init(float delay, float speed, TextureRegion... textureRegions) {
        texs = new GameList<>();
        texs.addAll(Arrays.asList(textureRegions));
        this.delay = delay;
        this.speed = speed;
        frameCount = texs.size();
        reset();
    }

    public void update() {
        done = false;
        keyFrameDoneThisFrame = false;
        tick += speed;
        if (tick >= delay) {
            tick -= delay;
            progressFrame();
        }
    }

    private void progressFrame() {
        currentFrame++;
        if (currentFrame >= frameCount) {
            currentFrame = 0;
            done = true;
        }

        if (currentFrame >= keyFrame && !keyFrameDone) {
            keyFrameDone = true;
            keyFrameDoneThisFrame = true;
        }
    }

    public void reset() {
        currentFrame = 0;
        tick = 0;
        done = false;
        keyFrameDone = false;
    }

    public void sync(SpriteAnimation spriteAnimation) {
        currentFrame = spriteAnimation.currentFrame;
        tick = spriteAnimation.tick;
    }

    public TextureRegion getCurrent() {
        return texs.get(currentFrame);
    }
}
