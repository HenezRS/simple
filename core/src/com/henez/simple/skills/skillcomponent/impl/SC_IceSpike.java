package com.henez.simple.skills.skillcomponent.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillstep.impl.SS_ApplyDamage;
import com.henez.simple.skills.skillstep.impl.SS_PlayAnimUntilKeyFrame;
import com.henez.simple.skills.skillstep.impl.SS_PlayEffect;
import com.henez.simple.skills.skillstep.impl.SS_PlayEffectProjectileUntilCollision;
import com.henez.simple.sprite.animation.EffectAtlas;

public class SC_IceSpike extends SkillComponent {

    @Override
    public void buildSteps() {
        steps.addAll(new SS_PlayAnimUntilKeyFrame(source, Animation.attack, speedMulAnimation),
                     new SS_PlayEffectProjectileUntilCollision(source, target, EffectAtlas.ICE_MISSILE.toDynamicWithSpeed(speedMulEffect), 1),
                     new SS_PlayEffect(target, EffectAtlas.ICE.toDynamicWithSpeed(speedMulEffect)),
                     new SS_ApplyDamage(source, target, this, true));
    }
}
