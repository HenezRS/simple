package com.henez.simple.skills.impl;

import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.Skill;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.sprite.AnimationAtlas;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.stats.damage.Damage;

public class S_Attack extends Skill {

    SpriteAnimation effect;

    public S_Attack(SkillName skillName, SkillTarget skillTarget) {
        super(skillName, skillTarget);
        effect = new SpriteAnimation(AnimationAtlas.SLASH);
    }

    @Override
    public boolean update() {
        effect.update();
        if (effect.isDone()) {
            target.applyDamage(new Damage(source, target, this));
            done = true;
        }
        return done;
    }

    @Override
    public void draw(Batcher batch) {
        batch.draw(effect.getCurrent(), target.getX(), target.getY());
    }
}
