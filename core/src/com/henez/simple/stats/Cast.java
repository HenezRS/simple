package com.henez.simple.stats;

import com.henez.simple.datastructures.Numbers;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import lombok.Getter;

@Getter
public class Cast {
    private float current;
    private float max;
    private float add;
    private SkillName skillName;
    private SkillTarget skillTarget;
    private boolean done;
    private boolean instant;
    private String name;
    private boolean channelled;
    private int channelExecutionMaxCount;
    private float channelExecutionTick;
    private float channelExecutionDelay;
    private boolean channelCastReady;

    public Cast() {
        resetForBattle();
    }

    public void begin(SkillName skillName, SkillTarget skillTarget, float speedMul) {
        this.skillName = skillName;
        this.skillTarget = skillTarget;
        this.name = skillName.getName();
        current = 0;
        add = 1 * speedMul;
        max = skillName.getCastDelay();
        done = max <= 0;
        instant = max <= 0;
        channelled = skillName.getChannelDelay() > 0;
    }

    public boolean update() {
        if (!done) {
            current += add;
            if (current >= max) {
                done = true;
            }
        }

        channelCastReady = false;
        if (!done && channelled) {
            //do not execution last cast... last execution handled by BattleMembers.java
            channelExecutionTick += add;
            if (channelExecutionTick >= channelExecutionDelay) {
                channelExecutionTick -= channelExecutionDelay;
                channelCastReady = true;
            }
        }

        return done;
    }

    public void beginChannel(float channelSpeedMul) {
        channelCastReady = false;
        current = 0;
        add = 1 * channelSpeedMul;
        max = skillName.getChannelDelay();
        done = false;

        channelExecutionTick = 0;
        channelExecutionMaxCount = skillName.getChannelExecutionCount();
        channelExecutionDelay = max / channelExecutionMaxCount;
    }

    public void resetForBattle() {
        current = 0;
    }

    public void turnEnd() {
        resetForBattle();
    }

    public boolean inProgress() {
        return !done;
    }

    public float getPercent() {
        return Numbers.percent(current, max);
    }
}
