package com.henez.simple.skills;

import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.skillgroup.SkillGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillExecution {
    SkillGroup skillGroup;
    SkillName skillName;
    boolean executing = false;

    public SkillExecution() {
    }

    public void executeSkill(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        executing = true;
        skillGroup = skillName.create(skillTarget);
    }

    public void update() {
        skillGroup.update();
    }

    public void draw(Batcher batch) {
        skillGroup.draw(batch);
    }

    public boolean isDone() {
        return skillGroup.isDone();
    }

    public void finish() {
        executing = false;
    }

    public boolean isExecuting() {
        return executing;
    }
}
