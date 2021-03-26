package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.sprite.animation.AnimationDynamic;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_PlayEffectProjectileUntilCollision extends SkillStep {
    Fighter stepSource;
    Fighter stepTarget;
    SpriteAnimation effect;

    public SS_PlayEffectProjectileUntilCollision(Fighter stepSource, Fighter stepTarget, AnimationDynamic animation) {
        this.stepSource = stepSource;
        this.stepTarget = stepTarget;
        this.effect = new SpriteAnimation(animation);
    }

    @Override
    public void update() {
        //move from source to target
        effect.update();
        //if hit target
        if (false) {
            finish();
        }
    }

    @Override
    public void draw(Batcher batch) {
        if (!done) {
            batch.draw(effect.getCurrent(), stepTarget.getX(), stepTarget.getY());
        }
    }
}
