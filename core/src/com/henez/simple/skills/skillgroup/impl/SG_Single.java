package com.henez.simple.skills.skillgroup.impl;

import com.henez.simple.skills.SkillComponentName;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillgroup.SkillGroup;

public class SG_Single extends SkillGroup {

    public SG_Single(SkillName skillName, SkillTarget skillTarget, SkillComponentName skillComponentName) {
        super(skillName, skillTarget);
        skillComponents.add(skillComponentName.create().with(skillName, source, target, speedMulAnimation, speedMulEffect));
    }

    public SG_Single(SkillName skillName, SkillTarget skillTarget, SkillComponentName skillComponentName, float speedMulAnimation, float speedMulEffect) {
        super(skillName, skillTarget);
        skillComponents.add(skillComponentName.create().with(skillName, source, target, speedMulAnimation, speedMulEffect));
    }

    @Override
    public boolean update() {
        done = skillComponents.stream().allMatch(SkillComponent::update);
        return done;
    }
}
