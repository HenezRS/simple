package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_PlayAnimUntilKeyFrame extends SkillStep {
    private Fighter stepTarget;
    private Animation animation;
    private float speedMulAnimation = 1.0f;

    public SS_PlayAnimUntilKeyFrame(Fighter stepTarget, Animation animation) {
        this.stepTarget = stepTarget;
        this.animation = animation;
    }

    public SS_PlayAnimUntilKeyFrame(Fighter stepTarget, Animation animation, float mul) {
        this.stepTarget = stepTarget;
        this.animation = animation;
        this.speedMulAnimation = mul;
    }

    @Override
    public void init() {
        stepTarget.getSprite().setAnimationAndResetPlayOnce(animation);
        stepTarget.getSprite().setAnimationSpeedMul(speedMulAnimation);
    }

    @Override
    public void update() {
        if (stepTarget.getSprite().isKeyFrameDoneThisFrame()) {
            finish();
        }
    }
}
