package com.henez.simple.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class Framerate {
    private long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private float millisSinceGameStart;

    public Framerate() {
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        millisSinceGameStart = 0;
    }

    public void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();
        millisSinceGameStart += delta;

        sinceChange += delta;
        if (sinceChange >= 1000) {
            sinceChange -= 1000;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    public float getFrameRate() {
        return frameRate;
    }

    public float getMillisSinceGameStart() {
        return millisSinceGameStart;
    }

    public int getSecondsSinceGameStart() {
        return (int) millisSinceGameStart / 1000;
    }
}
