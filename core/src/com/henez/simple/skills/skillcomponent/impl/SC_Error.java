package com.henez.simple.skills.skillcomponent.impl;

import com.henez.simple.enums.Animation;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillstep.impl.SS_ApplyDamage;
import com.henez.simple.skills.skillstep.impl.SS_PlayAnimUntilKeyFrame;
import com.henez.simple.skills.skillstep.impl.SS_PlayEffect;
import com.henez.simple.sprite.animation.AnimationAtlas;
import com.henez.simple.world.mapobjects.Fighter;

public class SC_Error extends SkillComponent {

    @Override
    public void buildSteps() {
        System.out.println("Error: skill missing cast");
    }
}
