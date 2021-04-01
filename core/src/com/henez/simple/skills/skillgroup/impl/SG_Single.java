package com.henez.simple.skills.skillgroup.impl;

import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.SC_Missile;
import com.henez.simple.skills.skillgroup.SkillGroup;

public class SG_Single extends SkillGroup {

    public SG_Single(SkillName skillName, SkillTarget skillTarget, SkillComponent skillComponent) {
        super(skillName, skillTarget);
        skillComponent.createSteps(skillName, source, target);
        skillComponents.add(skillComponent);
    }
}
