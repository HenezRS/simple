package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_PlayAnimAndContinue extends SkillStep {
    private Fighter stepTarget;
    private Animation animation;
    private float speedMulAnimation = 1.0f;

    public SS_PlayAnimAndContinue(Fighter stepTarget, Animation animation) {
        this.stepTarget = stepTarget;
        this.animation = animation;
    }

    public SS_PlayAnimAndContinue(Fighter stepTarget, Animation animation, float mul) {
        this.stepTarget = stepTarget;
        this.animation = animation;
        this.speedMulAnimation = mul;
    }

    @Override
    public void init() {
        stepTarget.getSprite().playAnimation(animation);
        stepTarget.getSprite().setAnimationSpeedMul(animation, speedMulAnimation);
        finish();
    }

    @Override
    public void update() {
    }
}
