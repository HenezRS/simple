package com.henez.simple.skills;

import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.*;
import lombok.Getter;

@Getter
@SuppressWarnings("all")
public enum SkillComponentName {
    ATTACK,
    DO_NOTHING,
    ERROR,
    FLAME,
    BOLT,
    ICE_SPIKE,
    ;

    public SkillComponent create() {
        switch (this) {
        case ATTACK:
            return new SC_Attack();
        case DO_NOTHING:
            return new SC_DoNothing();
        case FLAME:
            return new SC_Flame();
        case BOLT:
            return new SC_Flame();
        case ICE_SPIKE:
            return new SC_IceSpike();
        default:
            return new SC_Error();
        }
    }
}
