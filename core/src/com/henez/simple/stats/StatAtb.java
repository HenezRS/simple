package com.henez.simple.stats;

import com.henez.simple.datastructures.Numbers;
import com.henez.simple.global.Global;
import lombok.Getter;

public class StatAtb {
    private float current;
    private float max;
    private float add;

    @Getter
    private boolean ready;

    public StatAtb() {
        current = 0;
        max = 1000f;
        ready = false;
        add = 100f / Global.SEC;
    }

    public boolean update() {
        if (!ready) {
            current += add;
            if (current >= max) {
                ready = true;
            }
        }

        return ready;
    }

    public void spend() {
        current -= max;
        ready = false;
    }

    public void reset(float pos, float fighterCount) {
        current = max - (((pos + 1) / (fighterCount + 1)) * max);
        ready = false;
    }

    public float getPercent() {
        return Numbers.percent(current, max);
    }
}
