package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.skills.impl.S_Attack;
import com.henez.simple.skills.impl.S_DoNothing;
import com.henez.simple.skills.impl.S_Error;
import lombok.Getter;

@Getter
public enum SkillName {
    ERROR("[error: missing skill]", 0, 0, 0),
    DO_NOTHING("do nothing", 0, 0, 0),
    ATTACK("attack", 1, 0, 0),
    ATTACK_CAST("attack casting", 4, 2, Global.SEC * 2),
    ;

    private String name;
    private int power;
    private int cost;
    private int castDelay;

    SkillName(String name, int power, int cost, int castDelay) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.castDelay = castDelay;
    }

    public Skill create(SkillTarget skillTarget) {
        switch (this) {
        case DO_NOTHING:
            return new S_DoNothing(this, skillTarget);
        case ATTACK:
        case ATTACK_CAST:
            return new S_Attack(this, skillTarget);
        default:
            return new S_Error(this, skillTarget);
        }
    }
}
