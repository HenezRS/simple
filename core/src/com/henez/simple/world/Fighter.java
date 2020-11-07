package com.henez.simple.world;

import com.henez.simple.skills.SkillExecution;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.StatSheet;
import lombok.Getter;

@Getter
public class Fighter extends Actor {
    protected StatSheet statSheet;
    protected SkillExecution skillExecution;

    public Fighter(int gx, int gy, Sprite sprite, int depth) {
        super(gx, gy, sprite, depth);
        statSheet = new StatSheet();
        skillExecution = new SkillExecution();
    }

    public void battleStart() {
        statSheet.resetForBattle();
    }

    public void battleUpdate() {
        statSheet.tickAtb();
    }

    public void skillBegin() {
        skillExecution.begin();
    }

    public boolean skillUpdate() {
        skillExecution.update();
        if (skillExecution.isDone()) {
            skillExecution.finish();
        }
        return skillExecution.isDone();
    }

    public boolean readyToAct() {
        return statSheet.readyToAct();
    }

    public void turnEnd() {
        statSheet.turnEnd();
    }
}
