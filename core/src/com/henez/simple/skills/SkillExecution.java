package com.henez.simple.skills;

import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillExecution {
    SkillComponent skillComponent;
    SkillName skillName;
    boolean executing = false;

    public SkillExecution() {
    }

    public void executeSkill(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        executing = true;
        skillComponent = skillName.create(skillTarget);
    }

    public void update() {
        skillComponent.update();
    }

    public void draw(Batcher batch) {
        skillComponent.draw(batch);
    }

    public boolean isDone() {
        return skillComponent.isDone();
    }

    public void finish() {
        executing = false;
    }

    public boolean isExecuting() {
        return executing;
    }
}
