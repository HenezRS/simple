package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillExecution {
    Timer skill;

    public SkillExecution() {
        skill = new Timer(Global.SEC * 3);
    }

    public void begin() {
        skill.reset();
    }

    public void update() {
        skill.update();
    }

    public boolean isDone() {
        return skill.isDone();
    }
}
