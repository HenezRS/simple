package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.SC_Attack;
import com.henez.simple.skills.skillcomponent.impl.SC_AttackFast;
import com.henez.simple.skills.skillcomponent.impl.SC_DoNothing;
import com.henez.simple.skills.skillcomponent.impl.SC_Error;
import com.henez.simple.skills.skillcomponent.impl.SC_Missile;
import com.henez.simple.skills.skillgroup.SkillGroup;
import com.henez.simple.skills.skillgroup.impl.SG_All;
import lombok.Getter;

@Getter
@SuppressWarnings("all")
public enum SkillComponentName {
    ATTACK,
    ATTACK_FAST,
    DO_NOTHING,
    MISSILE,
    ;

    public SkillComponent create() {
        switch (this) {
        case ATTACK:
            return new SC_Attack();
        case ATTACK_FAST:
            return new SC_AttackFast();
        case DO_NOTHING:
            return new SC_DoNothing();
        case MISSILE:
            return new SC_Missile();
        default:
            return new SC_Error();
        }
    }
}
