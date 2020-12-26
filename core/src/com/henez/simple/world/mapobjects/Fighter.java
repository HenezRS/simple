package com.henez.simple.world.mapobjects;

import com.henez.simple.atlas.imgset.ImgSetFighters;
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

import java.util.Arrays;

@Getter
public class Fighter extends Actor {
    protected StatSheet statSheet;
    protected SkillExecution skillExecution;
    protected Cast cast;
    protected FighterState fighterState;
    protected ImgSetFighters imgSetFighters;
    protected boolean dead;
    protected boolean isPlayer;

    public Fighter(int gx, int gy, ImgSetFighters imgSetFighters, int depth) {
        super(gx, gy, new Sprite(), depth);
        this.imgSetFighters = imgSetFighters;
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
        fighterState = FighterState.WAITING;
        statSheet.resetForBattle();
        cast.resetForBattle();
    }

    public void battleUpdate() {
        statSheet.tickAtb();
    }

    public void castingUpdate() {
        cast.update();
    }

    public void determineSkillCast(SkillTargetBuilder targetBuilder) {
        SkillName chosenSkill = SkillName.ATTACK;
        if (true || Numbers.flip() && Numbers.flip()) {
            chosenSkill = SkillName.ATTACK_CAST;
        }

        cast.begin(chosenSkill, targetBuilder.singleRandomEnemy(), 1);

        if (cast.isInstant()) {
            skillBeginCastExecution();
        } else {
            fighterState = FighterState.CASTING;
        }
    }

    public void skillBeginCastExecution() {
        fighterState = FighterState.EXECUTING;
        skillExecution.executeSkill(cast.getSkillName(), cast.getSkillTarget());
    }

    public boolean fighterStateOneOf(FighterState... fighterStates) {
        return Arrays.asList(fighterStates).contains(fighterState);
    }

    public void resetSpriteState() {
        sprite.setAnimationAndReset(Animation.idle);
    }

    public boolean isWaiting() {
        return !statSheet.readyToAct() && cast.isDone() && !skillExecution.isExecuting();
    }

    public boolean readyToAct() {
        return statSheet.readyToAct() && isPlayer;
    }

    public void turnEnd() {
        fighterState = FighterState.WAITING;
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
