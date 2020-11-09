package com.henez.simple.skills.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.Skill;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.sprite.AnimationAtlas;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.stats.damage.Damage;

public class S_Attack extends Skill {

    SpriteAnimation effect;
    boolean drawEffect = false;

    public S_Attack(SkillName skillName, SkillTarget skillTarget) {
        super(skillName, skillTarget);
        effect = new SpriteAnimation(AnimationAtlas.SLASH);
        source.getSprite().setAnimationAndResetPlayOnce(Animation.attack);
    }

    @Override
    public boolean update() {
        switch (state) {
        case 0:
            swingWep();
            break;
        case 1:
            slash();
            break;
        }
        return done;
    }

    private void swingWep() {
        if (source.getSprite().isKeyFrameDoneThisFrame()) {
            drawEffect = true;
            nextState();
        }
    }

    private void slash() {
        effect.update();
        if (effect.isDone()) {
            target.applyDamage(new Damage(source, target, this));
            finish();
        }
    }

    @Override
    public void draw(Batcher batch) {
        if (drawEffect) {
            batch.draw(effect.getCurrent(), target.getX(), target.getY());
        }
    }
}
