package com.henez.simple.stats;

import com.henez.simple.datastructures.Numbers;

public class Stat {
    private int cur;
    private int max;

    public Stat(int max) {
        this.max = max;
        restoreAll();
    }

    public Stat(int cur, int max) {
        this.cur = cur;
        this.max = max;
    }

    public void restore(int amount) {
        cur = Numbers.clamp(cur + amount, 0, max);
    }

    public void restoreAll() {
        cur = max;
    }

    public int get() {
        return cur;
    }

    public int getMax() {
        return max;
    }

    public void empty() {
        cur = 0;
    }

    public boolean isFull() {
        return cur >= max;
    }

    public boolean isEmpty() {
        return cur <= 0;
    }
}
