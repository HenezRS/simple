package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.misc.timer.Timer;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.skillstep.SkillStep;

public class SS_Pause extends SkillStep {
    Timer timer;

    public SS_Pause(int delayFrames) {
        this.timer = new Timer(delayFrames);
    }

    @Override
    public void update() {
        if (timer.update()) {
            finish();
        }
    }

    @Override
    public void draw(Batcher batch) {
    }
}
