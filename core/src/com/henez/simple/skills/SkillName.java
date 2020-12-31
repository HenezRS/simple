package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.skills.impl.S_Attack;
import com.henez.simple.skills.impl.S_AttackFast;
import com.henez.simple.skills.impl.S_DoNothing;
import com.henez.simple.skills.impl.S_Error;
import lombok.Getter;

@Getter
public enum SkillName {
    ERROR("[error: missing skill]", 0, 0, 0, 0, 0),
    DO_NOTHING("do nothing", 0, 0, 0, 0, 0),
    ATTACK("attack", 1, 0, 0, 0, 0),
    ATTACK_CAST("attack casting", 3.2f, 2, Global.SEC * 2, 0, 0),
    ATTACK_CHANNEL("attack channel", 0.4f, 2, 0, Global.SEC * 4, 4),
    ATTACK_CAST_CHANNEL("attack cast channel", 0.4f, 2, Global.SEC * 2, Global.SEC * 4, 8),
    ;

    private String name;
    private float power;
    private int cost;
    private int castDelay;
    private int channelDelay;
    private int channelExecutionCount;

    SkillName(String name, float power, int cost, int castDelay, int channelDelay, int channelExecutionCount) {
        this.name = name;
        this.power = power * 10.0f;
        this.cost = cost;
        this.castDelay = castDelay;
        this.channelDelay = channelDelay;
        this.channelExecutionCount = channelExecutionCount;
    }

    public Skill create(SkillTarget skillTarget) {
        switch (this) {
        case DO_NOTHING:
            return new S_DoNothing(this, skillTarget);
        case ATTACK:
        case ATTACK_CAST:
            return new S_Attack(this, skillTarget);
        case ATTACK_CHANNEL:
        case ATTACK_CAST_CHANNEL:
            return new S_AttackFast(this, skillTarget);
        default:
            return new S_Error(this, skillTarget);
        }
    }
}
