package com.henez.simple.skills.skillcomponent.impl;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.global.Global;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillstep.impl.*;
import com.henez.simple.sprite.animation.EffectAtlas;

public class SC_Flame extends SkillComponent {

    @Override
    public void buildSteps() {
        steps.addAll(new SS_PlayAnimAndContinue(source, Animation.cast, speedMulAnimation),
                     new SS_Pause(Global.SEC2),
                     new SS_PlayEffectProjectileUntilCollision(source, target, EffectAtlas.MISSILE.toDynamicWithSpeed(speedMulEffect), 1),
                     new SS_PlayEffect(target, EffectAtlas.FLAME.toDynamicWithSpeed(speedMulEffect)),
                     new SS_ApplyDamage(source, target, this, true),
                     new SS_Pause(Global.SEC2));
    }
}
