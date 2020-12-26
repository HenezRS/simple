package com.henez.simple.skills.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.skills.Skill;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillstep.impl.SS_ApplyDamage;
import com.henez.simple.skills.skillstep.impl.SS_PlayAnimUntilKeyFrame;
import com.henez.simple.skills.skillstep.impl.SS_PlayEffect;
import com.henez.simple.sprite.animation.AnimationDynamicFactory;

public class S_Attack extends Skill {

    public S_Attack(SkillName skillName, SkillTarget skillTarget) {
        super(skillName, skillTarget);
        steps.addAll(new SS_PlayAnimUntilKeyFrame(source, Animation.attack),
                     new SS_PlayEffect(target, AnimationDynamicFactory.toActorAttack(source.getImgSetFighters())),
                     new SS_ApplyDamage(source, target, this));
    }
}
