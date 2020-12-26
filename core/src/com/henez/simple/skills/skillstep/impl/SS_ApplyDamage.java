package com.henez.simple.skills.skillstep.impl;

import com.henez.simple.skills.Skill;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.stats.damage.Damage;
import com.henez.simple.world.mapobjects.Fighter;

public class SS_ApplyDamage extends SkillStep {
    Fighter stepSource;
    Fighter stepTarget;
    Skill skill;

    public SS_ApplyDamage(Fighter stepSource, Fighter stepTarget, Skill skill) {
        this.stepSource = stepSource;
        this.stepTarget = stepTarget;
        this.skill = skill;
    }
    
    @Override
    public void update() {
        stepTarget.applyDamage(new Damage(stepSource, stepTarget, skill));
        finish();
    }
}
