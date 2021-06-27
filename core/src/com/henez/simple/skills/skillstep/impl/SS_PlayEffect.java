package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.sprite.animation.AnimationDynamic;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_PlayEffect extends SkillStep {
    Fighter stepTarget;
    SpriteAnimation effect;

    public SS_PlayEffect(Fighter stepTarget, AnimationDynamic animation) {
        this.stepTarget = stepTarget;
        this.effect = new SpriteAnimation(animation);
        this.effect.resetAndPlayOnce();
    }

    @Override
    public void update() {
        effect.update();
        if (effect.isDonePlaying()) {
            finish();
        }
    }

    @Override
    public void draw(Batcher batch) {
        if (!effect.isDonePlaying()) {
            batch.setAlpha(effect.getCurrent().getAlpha());
            batch.draw(effect.getCurrent().getTex(), stepTarget.getTargetingX(), stepTarget.getTargetingYLow());
            batch.resetAlpha();
        }
    }
}
