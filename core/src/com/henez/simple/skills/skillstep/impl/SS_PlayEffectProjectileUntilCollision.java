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
    float curX;
    float curY;
    float goalX;
    float goalY;
    float speed;

    public SS_PlayEffectProjectileUntilCollision(Fighter stepSource, Fighter stepTarget, AnimationDynamic animation, float speed) {
        this.stepSource = stepSource;
        this.stepTarget = stepTarget;
        this.effect = new SpriteAnimation(animation);
        this.speed = speed;

        curX = stepSource.getX() + 8;
        curY = stepSource.getY() + 8;
        goalX = stepTarget.getX() + 8;
        goalY = stepTarget.getY() + 8;
    }

    @Override
    public void update() {
        effect.update();
        /*goalX = In.mouse.getWx();
        goalY = In.mouse.getWy();*/
        double dir = Math.atan2(goalY - curY, goalX - curX);
        curX += speed * Math.cos(dir);
        curY += speed * Math.sin(dir);
        double dist = Math.sqrt((goalX - curX) * (goalX - curX) + (goalY - curY) * (goalY - curY));
        System.out.println(dist);
        //if hit target
        if (dist <= speed) {
            finish();
        }
    }

    @Override
    public void draw(Batcher batch) {
        if (!done) {
            batch.setAlpha(effect.getCurrent().getAlpha());
            batch.draw(effect.getCurrent().getTex(), curX - 8, curY - 8);
            batch.resetAlpha();
        }
    }
}
