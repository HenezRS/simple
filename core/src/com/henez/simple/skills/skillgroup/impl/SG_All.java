package com.henez.simple.skills.skillgroup.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.SC_Missile;
import com.henez.simple.skills.skillgroup.SkillGroup;
import com.henez.simple.skills.skillstep.impl.SS_ApplyDamage;
import com.henez.simple.skills.skillstep.impl.SS_PlayAnimUntilKeyFrame;
import com.henez.simple.skills.skillstep.impl.SS_PlayEffect;
import com.henez.simple.skills.skillstep.impl.SS_PlayEffectProjectileUntilCollision;
import com.henez.simple.sprite.animation.AnimationAtlas;

public class SG_All extends SkillGroup {

    public SG_All(SkillName skillName, SkillTarget skillTarget, SkillComponent skillComponent) {
        super(skillName, skillTarget);
        targets.forEach(target -> {
            skillComponent.createSteps(skillName, source, target);
            skillComponents.add(skillComponent);
        });
    }

    @Override
    public boolean update() {
        done = skillComponents.stream().allMatch(SkillComponent::update);
        return done;
    }
}
