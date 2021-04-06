package com.henez.simple.skills;

import com.henez.simple.global.Global;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillcomponent.impl.*;
import com.henez.simple.skills.skillgroup.SkillGroup;
import com.henez.simple.skills.skillgroup.impl.SG_All;
import com.henez.simple.skills.skillgroup.impl.SG_AllSequence;
import com.henez.simple.skills.skillgroup.impl.SG_Single;
import lombok.Getter;

@Getter
@SuppressWarnings("all")
public enum SkillName {
    ERROR("[error: missing skill]", 0, 0, 0, 0, 0, SkillTargetName.SINGLE),
    DO_NOTHING("do nothing", 0, 0, 0, 0, 0, SkillTargetName.SINGLE),
    ATTACK("attack", 1, 0, 0, 0, 0, SkillTargetName.SINGLE),
    ATTACK_ALL("attack all", 3.2f, 2, Global.SEC * 2, 0, 0, SkillTargetName.ALL),
    ATTACK_CAST_SLOW("attack cast slow", 3.2f, 2, Global.SEC * 2, 0, 0, SkillTargetName.SINGLE),
    ATTACK_CAST("attack cast", 3.2f, 2, Global.SEC * 2, 0, 0, SkillTargetName.SINGLE),
    ATTACK_CAST_FAST("attack cast fast", 3.2f, 2, Global.SEC * 2, 0, 0, SkillTargetName.SINGLE),
    ATTACK_CHANNEL("attack channel", 0.4f, 2, 0, Global.SEC * 4, 4, SkillTargetName.SINGLE),
    ATTACK_CAST_CHANNEL("attack cast channel", 0.4f, 2, Global.SEC * 2, Global.SEC * 4, 8, SkillTargetName.SINGLE),
    ATTACK_CAST_CHANNEL_RAPID("attack rapid", 0.3f, 2, Global.SEC * 1.2f, Global.SEC * 4, 24, SkillTargetName.SINGLE),
    MISSILE_CAST("missile", 3.2f, 2, Global.SEC * 1, 0, 0, SkillTargetName.SINGLE),
    MISSILE_CAST_ALL("missile all", 3.2f, 2, Global.SEC * 1, 0, 0, SkillTargetName.ALL),
    MISSILE_CAST_ALLSEQ("missile all", 3.2f, 2, Global.SEC * 1, 0, 0, SkillTargetName.ALL),
    ;

    private String name;
    private float power;
    private int cost;
    private int castDelay;
    private int channelDelay;
    private int channelExecutionCount;
    private SkillTargetName target;

    SkillName(String name, float power, int cost, float castDelay, float channelDelay, int channelExecutionCount, SkillTargetName target) {
        this.name = name;
        this.power = power * 10.0f;
        this.cost = cost;
        this.castDelay = (int) castDelay;
        this.channelDelay = (int) channelDelay;
        this.channelExecutionCount = channelExecutionCount;
        this.target = target;
    }

    public SkillGroup create(SkillTarget skillTarget) {
        switch (this) {
        case DO_NOTHING:
            return new SG_Single(this, skillTarget, SkillComponentName.DO_NOTHING);
        case ATTACK:
        case ATTACK_CAST:
            return new SG_Single(this, skillTarget, SkillComponentName.ATTACK);
        case ATTACK_CAST_SLOW:
            return new SG_Single(this, skillTarget, SkillComponentName.ATTACK,0.5f,0.5f);
        case ATTACK_CAST_FAST:
            return new SG_Single(this, skillTarget, SkillComponentName.ATTACK,2.0f,2.0f);
        case ATTACK_ALL:
            return new SG_AllSequence(this, skillTarget, SkillComponentName.ATTACK);
        case ATTACK_CHANNEL:
        case ATTACK_CAST_CHANNEL:
        case ATTACK_CAST_CHANNEL_RAPID:
            return new SG_Single(this, skillTarget, SkillComponentName.ATTACK);
        case MISSILE_CAST:
            return new SG_Single(this, skillTarget, SkillComponentName.MISSILE);
        case MISSILE_CAST_ALL:
            return new SG_AllSequence(this, skillTarget, SkillComponentName.MISSILE);
        case MISSILE_CAST_ALLSEQ:
            return new SG_All(this, skillTarget, SkillComponentName.MISSILE,Global.SEC2);
        default:
            return new SG_Single(this, skillTarget, SkillComponentName.ERROR);
        }
    }
}
