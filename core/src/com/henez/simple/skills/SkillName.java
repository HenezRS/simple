package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.*;
import com.henez.simple.skills.skillgroup.SkillGroup;
import com.henez.simple.skills.skillgroup.impl.SG_All;
import com.henez.simple.skills.skillgroup.impl.SG_Single;
import lombok.Getter;

@Getter
@SuppressWarnings("all")
public enum SkillName {
    ERROR("[error: missing skill]", 0, 0, 0, 0, 0),
    DO_NOTHING("do nothing", 0, 0, 0, 0, 0),
    ATTACK("attack", 1, 0, 0, 0, 0),
    ATTACK_ALL("attack all", 3.2f, 2, Global.SEC * 2, 0, 0),
    ATTACK_CAST("attack casting", 3.2f, 2, Global.SEC * 2, 0, 0),
    ATTACK_CHANNEL("attack channel", 0.4f, 2, 0, Global.SEC * 4, 4),
    ATTACK_CAST_CHANNEL("attack cast channel", 0.4f, 2, Global.SEC * 2, Global.SEC * 4, 8),
    ATTACK_CAST_CHANNEL_RAPID("attack rapid", 0.3f, 2, Global.SEC * 1.2f, Global.SEC * 4, 24),
    MISSILE_CAST("missile", 3.2f, 2, Global.SEC * 1, 0, 0),
    ;

    private String name;
    private float power;
    private int cost;
    private int castDelay;
    private int channelDelay;
    private int channelExecutionCount;

    SkillName(String name, float power, int cost, float castDelay, float channelDelay, int channelExecutionCount) {
        this.name = name;
        this.power = power * 10.0f;
        this.cost = cost;
        this.castDelay = (int) castDelay;
        this.channelDelay = (int) channelDelay;
        this.channelExecutionCount = channelExecutionCount;
    }

    public SkillGroup create(SkillTarget skillTarget) {
        switch (this) {
        case DO_NOTHING:
            return new SG_Single(this, skillTarget, SkillComponentName.DO_NOTHING);
        case ATTACK:
        case ATTACK_CAST:
            return new SG_Single(this, skillTarget, SkillComponentName.ATTACK);
        case ATTACK_ALL:
            return new SG_All(this, skillTarget, SkillComponentName.ATTACK);
        case ATTACK_CHANNEL:
        case ATTACK_CAST_CHANNEL:
        case ATTACK_CAST_CHANNEL_RAPID:
            return new SG_Single(this, skillTarget, SkillComponentName.ATTACK_FAST);
        case MISSILE_CAST:
            return new SG_Single(this, skillTarget, SkillComponentName.MISSILE);
        default:
            return new SG_Single(this, skillTarget, SkillComponentName.ERROR);
        }
    }
}
