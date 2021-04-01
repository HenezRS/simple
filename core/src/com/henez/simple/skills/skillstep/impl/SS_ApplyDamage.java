package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.effect.EffectFactory;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.stats.damage.Damage;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_ApplyDamage extends SkillStep {
    Fighter stepSource;
    Fighter stepTarget;
    SkillComponent skillComponent;

    public SS_ApplyDamage(Fighter stepSource, Fighter stepTarget, SkillComponent skillComponent) {
        this.stepSource = stepSource;
        this.stepTarget = stepTarget;
        this.skillComponent = skillComponent;
    }

    @Override
    public void update() {
        Damage damage = new Damage(stepSource, stepTarget, skillComponent);
        stepTarget.applyDamage(damage);
        EffectFactory.createDamageText(damage);
        finish();
    }
}
