package com.henez.simple.skills.skillgroup;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.SkillExecution;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class SkillGroup {
    protected SkillName skillName;
    protected SkillTarget skillTarget;
    protected Fighter source;
    protected Fighter target;
    protected GameList<Fighter> targets;
    protected GameList<SkillComponent> skillComponents;
    protected GameList<SkillComponent> skillComponentsWaiting;

    protected float speedMulAnimation = 1;
    protected float speedMulEffect = 1;
    protected boolean done = false;

    public SkillGroup(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        this.skillTarget = skillTarget;
        skillComponents = new GameList<>();
        skillComponentsWaiting = new GameList<>();

        source = skillTarget.getSource();
        target = skillTarget.getTarget();
        targets = skillTarget.getTargets();
    }

    public abstract boolean update();

    public void draw(Batcher batch) {
        if (!done) {
            skillComponents.forEach(sc -> sc.draw(batch));
        }
    }

    protected void finish() {
        done = true;
    }
}
