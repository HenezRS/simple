package com.henez.simple.misc.timer;

public class TimerRepeater extends Timer {
    private int playCount;
    private int playLimit;
    private boolean doneAll;

    public TimerRepeater(int delay) {
        super(delay);
        this.playLimit = 0;
        resetAll();
    }

    public TimerRepeater(int delay, int playLimit) {
        super(delay);
        this.playLimit = playLimit;
        resetAll();
    }

    public void resetAll() {
        reset();
        playCount = 0;
        doneAll = false;
    }

    @Override
    public boolean update() {
        done = false;
        if (!doneAll) {
            if (super.update()) {
                tick = 0;
                playCount++;
                doneAll = playLimit > 0 && playCount >= playLimit;
            }
        }
        return doneAll;
    }

    public boolean completed() {
        return done;
    }
}
