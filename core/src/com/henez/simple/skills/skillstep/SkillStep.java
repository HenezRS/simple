package com.henez.simple.skills.skillstep;

import com.henez.simple.renderer.Batcher;
import lombok.Getter;

@Getter
public abstract class SkillStep {
    protected boolean done = false;

    public void init() {

    }

    public abstract void update();

    public void draw(Batcher batch) {

    }

    protected void finish() {
        done = true;
    }
}
