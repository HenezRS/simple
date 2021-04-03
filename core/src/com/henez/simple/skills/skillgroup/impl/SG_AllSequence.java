package com.henez.simple.skills.skillgroup.impl;

import com.henez.simple.skills.SkillComponentName;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillgroup.SkillGroup;

public class SG_AllSequence extends SkillGroup {

    public SG_AllSequence(SkillName skillName, SkillTarget skillTarget, SkillComponentName skillComponentName) {
        super(skillName, skillTarget);
        targets.forEach(target -> {
            skillComponents.add(skillComponentName.create().with(skillName, source, target));
        });
    }

    @Override
    public boolean update() {
        done = skillComponents.stream().allMatch(SkillComponent::update);
        return done;
    }
}
