package com.henez.simple.misc.timer;

import com.henez.simple.global.Global;
import com.henez.simple.utils.StringUtils;
import lombok.Getter;

@Getter
public class Timer {
    protected int tick;
    protected int delay;
    protected boolean done;

    public Timer() {
        this.delay = 0;
        reset();
    }

    public Timer(int delay) {
        this.delay = delay;
        reset();
    }

    public void reset() {
        tick = 0;
        done = false;
    }

    public boolean update() {
        if (!done) {
            tick++;
            done = tick >= delay && delay > 0;
        }
        return done;
    }

    public String getSeconds() {
        float f = (float) tick / Global.SEC;
        return StringUtils.decimal2(f);
    }
}
