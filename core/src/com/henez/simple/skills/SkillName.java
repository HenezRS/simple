package com.henez.simple.skills;

import com.henez.simple.skills.impl.S_Attack;
import com.henez.simple.skills.impl.S_DoNothing;
import com.henez.simple.skills.impl.S_Error;
import lombok.Getter;

@Getter
public enum SkillName {
    ERROR("[error: missing skill]", 0, 0),
    DO_NOTHING("do nothing", 0, 0),
    ATTACK("attack", 1, 0),
    ;

    private String name;
    private int power;
    private int cost;

    SkillName(String name, int power, int cost) {
        this.name = name;
        this.power = power;
        this.cost = cost;
    }

    public Skill create(SkillTarget skillTarget) {
        switch (this) {
        case DO_NOTHING:
            return new S_DoNothing(this, skillTarget);
        case ATTACK:
            return new S_Attack(this, skillTarget);
        default:
            return new S_Error(this, skillTarget);
        }
    }
}
