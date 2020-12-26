package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_PlayAnimUntilKeyFrame extends SkillStep {
    Fighter stepTarget;
    Animation animation;

    public SS_PlayAnimUntilKeyFrame(Fighter stepTarget, Animation animation) {
        this.stepTarget = stepTarget;
        this.animation = animation;
    }

    @Override
    public void init() {
        stepTarget.getSprite().setAnimationAndResetPlayOnce(animation);
    }

    @Override
    public void update() {
        if (stepTarget.getSprite().isKeyFrameDoneThisFrame()) {
            finish();
        }
    }
}
