package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.datastructures.GameList;

import java.util.Arrays;

public class SpriteAnimation {
    private GameList<TextureRegion> texs;
    private float tick;
    private float delay;
    private float speed;
    private int currentFrame;
    private int frameCount;

    public SpriteAnimation(float delay, TextureRegion... textureRegions) {
        init(delay, 1.0f, textureRegions);
    }

    public SpriteAnimation(float delay, float speed, TextureRegion... textureRegions) {
        init(delay, speed, textureRegions);
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
        }
    }

    public void reset() {
        currentFrame = 0;
        tick = 0;
    }

    public void sync(SpriteAnimation spriteAnimation) {
        currentFrame = spriteAnimation.currentFrame;
        tick = spriteAnimation.tick;
    }

    public TextureRegion getCurrent() {
        return texs.get(currentFrame);
    }
}
