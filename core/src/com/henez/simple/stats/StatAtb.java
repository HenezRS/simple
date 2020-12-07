package com.henez.simple.stats;

import com.henez.simple.datastructures.Numbers;
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
        add = 100f;
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

    public void reset() {
        current = Numbers.nextFloatBetween(1f, max * 0.8f);
        ready = false;
    }

    public float getPercent() {
        return Numbers.percent(current, max);
    }
}
