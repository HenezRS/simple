package com.henez.simple.world;

import com.henez.simple.skills.SkillExecution;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.StatSheet;
import com.henez.simple.stats.damage.Damage;
import lombok.Getter;

@Getter
public class Fighter extends Actor {
    protected StatSheet statSheet;
    protected SkillExecution skillExecution;
    protected boolean dead;

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

    public void applyDamage(Damage damage) {
        statSheet.applyDamage(damage);
        dead = statSheet.isDead();
    }
}
