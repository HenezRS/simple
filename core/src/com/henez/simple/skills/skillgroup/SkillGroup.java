package com.henez.simple.skills.skillgroup;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.skills.SkillExecution;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.world.mapobjects.Fighter;

import java.util.HashMap;
import java.util.Map;

public abstract class SkillGroup {
    protected SkillName skillName;
    protected SkillTarget skillTarget;
    protected Fighter source;
    protected Fighter target;
    protected GameList<Fighter> targets;
    protected GameList<SkillComponent> skillComponents;

    public SkillGroup(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        this.skillTarget = skillTarget;
        skillComponents = new GameList<>();

        source = skillTarget.getSource();
        target = skillTarget.getTarget();
        targets = skillTarget.getTargets();
    }
}
