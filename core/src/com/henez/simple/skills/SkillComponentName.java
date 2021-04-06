package com.henez.simple.skills;

import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.SC_Attack;
import com.henez.simple.skills.skillcomponent.impl.SC_DoNothing;
import com.henez.simple.skills.skillcomponent.impl.SC_Error;
import com.henez.simple.skills.skillcomponent.impl.SC_Missile;
import lombok.Getter;

@Getter
@SuppressWarnings("all")
public enum SkillComponentName {
    ATTACK,
    DO_NOTHING,
    ERROR,
    MISSILE,
    ;

    public SkillComponent create() {
        switch (this) {
        case ATTACK:
            return new SC_Attack();
        case DO_NOTHING:
            return new SC_DoNothing();
        case MISSILE:
            return new SC_Missile();
        default:
            return new SC_Error();
        }
    }
}
