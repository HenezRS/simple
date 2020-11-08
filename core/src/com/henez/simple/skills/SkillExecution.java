package com.henez.simple.skills;

import com.henez.simple.renderer.Batcher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillExecution {
    Skill skill;
    SkillName skillName;
    boolean executing = false;

    public SkillExecution() {
    }

    public void executeSkill(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        executing = true;
        skill = skillName.create(skillTarget);
    }

    public void update() {
        skill.update();
    }

    public void draw(Batcher batch) {
        skill.draw(batch);
    }

    public boolean isDone() {
        return skill.isDone();
    }

    public void finish() {
        executing = false;
    }

    public boolean isExecuting() {
        return executing;
    }
}
