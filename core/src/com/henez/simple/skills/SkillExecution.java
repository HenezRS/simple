package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillExecution {
    Timer skill;
    boolean started = false;

    public SkillExecution() {
        skill = new Timer(Global.SEC);
    }

    public void begin() {
        started = true;
        skill.reset();
    }

    public void update() {
        skill.update();
    }

    public boolean isDone() {
        return skill.isDone();
    }

    public void finish() {
        started = false;
    }

    public boolean isExecuting() {
        return started;
    }
}
