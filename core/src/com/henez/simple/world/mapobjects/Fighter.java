package com.henez.simple.world.mapobjects;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.enums.Animation;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.SkillExecution;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTargetBuilder;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.Cast;
import com.henez.simple.stats.StatSheet;
import com.henez.simple.stats.classes.ClassName;
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
    protected ClassName className;
    protected boolean dead = false;
    protected boolean isPlayer = false;
    protected boolean isLeader = false;
    protected int turn;

    public Fighter(int gx, int gy, ClassName className, int depth) {
        super(gx, gy, new Sprite(), depth);
        this.className = className;
        this.imgSetFighters = className.getImgSet();
        statSheet = new StatSheet();
        skillExecution = new SkillExecution();
        cast = new Cast();
    }

    @Override
    public void draw(Batcher batch) {
        sprite.draw(batch, x, y, facing2, dead);
    }

    public void battleStart(int pos, int fighterCount) {
        fighterState = FighterState.WAITING;
        statSheet.resetForBattle(pos, fighterCount);
        cast.resetForBattle();
        turn = 0;
    }

    public void battleUpdate() {
        statSheet.tickAtb();
    }

    public void castingUpdate() {
        cast.update();
    }

    public void executionUpdate() {
        if (fighterState == FighterState.CHANNELLING) {
            if (skillExecution != null && skillExecution.getSkillGroup() != null && !skillExecution.isDone()) {
                skillExecution.update();
            }
        } else {
            skillExecution.update();
        }
    }

    public void determineSkillCast(SkillTargetBuilder targetBuilder) {
        SkillName chosenSkill = null;
        if (isLeader) {
            chosenSkill = SkillName.ATTACK_CAST;
            if(turn==1) {
                chosenSkill = SkillName.ATTACK_ALL;
            }
        }

        if (chosenSkill != null && targetBuilder.isTargetsAvailable()) {
            cast.begin(chosenSkill, targetBuilder.singleRandomEnemy(), 1);
            sprite.getSpriteEffectManager().createBlink(Colors.white.color);

            if (cast.isInstant()) {
                skillBeginCastExecution();
            } else {
                fighterState = FighterState.CASTING;
            }
        }
    }

    public void skillBeginCastExecution() {
        if (cast.isChannelled() && !cast.isChannelStarted()) {
            cast.beginChannel(1);
            fighterState = FighterState.CHANNELLING;
        } else {
            fighterState = FighterState.EXECUTING;
            skillExecution.executeSkill(cast.getSkillName(), cast.getSkillTarget());
        }
    }

    public void skillBeginChannelExecution() {
        skillExecution.executeSkill(cast.getSkillName(), cast.getSkillTarget());
    }

    public void cancelChannelIfTargetNoLongerValid() {
        if (cast.getSkillTarget().targetsNoLongerValid()) {
            turnEnd();
        }
    }

    public boolean fighterStateIs(FighterState fighterState) {
        return this.fighterState == fighterState;
    }

    public boolean fighterStateOneOf(FighterState... fighterStates) {
        return Arrays.asList(fighterStates).contains(fighterState);
    }

    public void resetSpriteState() {
        sprite.setAnimationAndReset(Animation.idle);
    }

    public boolean canAct() {
        return !dead;
    }

    public boolean canBeTarget() {
        return !dead;
    }

    public boolean readyToAct() {
        return statSheet.readyToAct();
    }

    public void turnEnd() {
        fighterState = FighterState.WAITING;
        statSheet.turnEnd();
        cast.turnEnd();
        turn++;
    }

    public void applyDamage(Damage damage) {
        statSheet.applyDamage(damage);
        dead = statSheet.isDead();
    }

    public void setIsPlayer() {
        isPlayer = true;
    }

    public void setIsLeader() {
        isLeader = true;
    }

    public boolean isEnemy() {
        return !isPlayer;
    }
}
