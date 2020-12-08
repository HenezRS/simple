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
    private String name;

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

        this.name = "a";
        int l = Numbers.nextIntBetween(1, 20);
        for (int i = 0; i < l; ++i) {
            this.name += (char) ('a' + Math.random() * ('z' - 'a'));
        }
    }

    public boolean update() {
        if (!done) {
            current += add;
            if (current >= max) {
                done = true;
            }
        }

        return done;
    }

    public void resetForBattle() {
        current = 0;
        done = true;
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
