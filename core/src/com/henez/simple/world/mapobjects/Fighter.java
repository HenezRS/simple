package com.henez.simple.world.mapobjects;

import com.henez.simple.datastructures.Numbers;
import com.henez.simple.enums.Animation;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.SkillExecution;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTargetBuilder;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.Cast;
import com.henez.simple.stats.StatSheet;
import com.henez.simple.stats.damage.Damage;
import lombok.Getter;

@Getter
public class Fighter extends Actor {
    protected StatSheet statSheet;
    protected SkillExecution skillExecution;
    protected Cast cast;
    protected boolean dead;
    protected boolean isPlayer;

    public Fighter(int gx, int gy, Sprite sprite, int depth) {
        super(gx, gy, sprite, depth);
        statSheet = new StatSheet();
        skillExecution = new SkillExecution();
        cast = new Cast();
    }

    @Override
    public void draw(Batcher batch) {
        if (!dead) {
            super.draw(batch);
        }
    }

    public void battleStart() {
        statSheet.resetForBattle();
        cast.resetForBattle();
    }

    public void battleUpdate() {
        if (cast.inProgress()) {
            cast.update();
        } else {
            statSheet.tickAtb();
        }
    }

    public void determineSkillCast(SkillTargetBuilder targetBuilder) {
        SkillName chosenSkill = SkillName.ATTACK;
        if (true || Numbers.flip() && Numbers.flip()) {
            chosenSkill = SkillName.ATTACK_CAST;
        }

        cast.begin(chosenSkill, targetBuilder.singleRandomEnemy(), 1);
        if (cast.isDone()) {
            skillBegin(cast);
        }
    }

    public void skillBegin(Cast cast) {
        skillExecution.executeSkill(cast.getSkillName(), cast.getSkillTarget());
    }

    public boolean skillUpdate() {
        skillExecution.update();
        if (skillExecution.isDone()) {
            skillExecution.finish();
        }
        return skillExecution.isDone();
    }

    public void resetSpriteState() {
        sprite.setAnimationAndReset(Animation.idle);
    }

    public boolean readyToAct() {
        return statSheet.readyToAct() && cast.isDone();
    }

    public void turnEnd() {
        statSheet.turnEnd();
        cast.turnEnd();
    }

    public void applyDamage(Damage damage) {
        statSheet.applyDamage(damage);
        dead = statSheet.isDead();
    }

    public void setIsPlayer() {
        isPlayer = true;
    }

    public boolean isEnemy() {
        return !isPlayer;
    }
}
