package com.henez.simple.skills;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.world.Fighter;
import lombok.Getter;

@Getter
public abstract class Skill {
    protected SkillName skillName;
    protected SkillTarget skillTarget;
    protected Fighter source;
    protected Fighter target;
    protected GameList<Fighter> targets;
    protected boolean done = false;
    protected int state = 0;

    public Skill(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        this.skillTarget = skillTarget;

        source = skillTarget.getSource();
        target = skillTarget.getTarget();
        targets = skillTarget.getTargets();
    }

    public abstract boolean update();

    public abstract void draw(Batcher batch);

    protected void nextState() {
        state++;
    }

    protected void finish() {
        done = true;
    }
}
