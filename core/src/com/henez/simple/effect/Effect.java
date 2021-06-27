package com.henez.simple.effect;

import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.renderer.Batcher;
import lombok.Getter;

@Getter
public abstract class Effect {
    protected float x = 0;
    protected float y = 0;
    protected boolean done = false;
    protected boolean firstUpdate = true;
    protected Timer timer;

    public Effect() {
    }

    public abstract void update();

    public abstract void draw(Batcher batcher);

    public void setGridPos(int gx, int gy) {
        x = gx * Global.tilePixelSize;
        y = gy * Global.tilePixelSize;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void finish() {
        done = true;
    }
}
