package com.henez.simple.sprite.spriteeffects;

import com.henez.simple.misc.timer.Timer;
import lombok.Getter;

@Getter
public abstract class SpriteEffect {
    protected boolean done = false;
    protected Timer timer;

    public SpriteEffect() {
        timer = new Timer();
    }

    public abstract void update();

    protected void finish() {
        done = true;
    }
}
