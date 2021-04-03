package com.henez.simple.skills.skillgroup.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.skills.SkillComponentName;
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

    private Timer timer;
    private int executionCount = 0;
    public SG_All(SkillName skillName, SkillTarget skillTarget, SkillComponentName skillComponentName, int delay) {
        super(skillName, skillTarget);
        targets.forEach(target -> {
            skillComponentsWaiting.add(skillComponentName.create().with(skillName, source, target));
        });
        executionCount = skillComponentsWaiting.size();
        timer = new Timer(delay);
    }

    @Override
    public boolean update() {
        skillComponents.forEach(SkillComponent::update);
        done = executionCount==skillComponents.size() && skillComponents.stream().allMatch(SkillComponent::isDone);
        if(!skillComponentsWaiting.isEmpty() && timer.update()) {
            timer.reset();
            skillComponents.add(skillComponentsWaiting.first());
            skillComponentsWaiting.removeFirst();

        }
        return done;
    }
}
