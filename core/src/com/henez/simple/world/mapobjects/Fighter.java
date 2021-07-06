package com.henez.simple.world.mapobjects;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import com.henez.simple.debug.DebugFlags;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.EnemyRank;
import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.global.Global;
import com.henez.simple.input.In;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.SkillExecution;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillSheet;
import com.henez.simple.skills.SkillTargetBuilder;
import com.henez.simple.skills.skillInventory.SkillInventory;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.Cast;
import com.henez.simple.stats.StatSheet;
import com.henez.simple.stats.classes.ClassName;
import com.henez.simple.stats.damage.Damage;
import com.henez.simple.world.map.gamemap.GameMap;
import lombok.Getter;

import java.util.Arrays;

import static com.henez.simple.debug.DebugFlags.canNonLeadersAct;

@Getter
public class Fighter extends Actor {
    protected String name;
    protected StatSheet statSheet;
    protected SkillSheet skillSheet;
    protected SkillInventory skillInventory;
    protected SkillExecution skillExecution;
    protected Cast cast;
    protected FighterState fighterState;
    protected ImgSetFighters imgSetFighters;
    protected ClassName className;
    protected boolean dead = false;
    protected boolean isPlayer = false;
    protected boolean isLeader = false;
    protected EnemyRank enemyRank;
    protected int turn;
    protected boolean isBattleControlled = false;
    protected boolean isLarge;

    public Fighter(int gx, int gy, ClassName className, int depth) {
        super(gx, gy, new Sprite(), depth);
        this.className = className;
        this.imgSetFighters = className.getImgSet();
        this.isLarge = this.imgSetFighters.isLarge();
        this.size = this.isLarge ? Global.tilePixelSize * 2 : Global.tilePixelSize;
        statSheet = new StatSheet();
        skillSheet = new SkillSheet();
        skillInventory = new SkillInventory();
        skillExecution = new SkillExecution();
        cast = new Cast();
        this.enemyRank = className.getEnemyRank();
    }

    @Override
    public void update(WorldState state, GameMap map) {
        Animation.updateFighterAnimationState(this);
        super.update(state, map);
    }

    @Override
    public void draw(Batcher batch) {
        sprite.draw(batch, x, y, facing2, size);
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

    @Override
    public boolean isMouseOver() {
        return isLarge() ? In.mouse.isMouseWithinGridLarge(gx, gy) : In.mouse.isMouseWithinGrid(gx, gy);
    }

    public void determineSkillCast(SkillTargetBuilder targetBuilder) {
        SkillName chosenSkill = null;
        if ((canNonLeadersAct || isLeader) && (DebugFlags.canEnemiesAct || (DebugFlags.canPlayersAct && isPlayer))) {
            chosenSkill = SkillName.ATTACK;
            if (turn == 1) {
                chosenSkill = SkillName.ATTACK;
            }
            if (turn == 2) {
                chosenSkill = SkillName.ATTACK;
            }
        }

        if (chosenSkill != null) {
            targetBuilder.createTargetIntelligent(chosenSkill);
            if (targetBuilder.isTargetsAvailable()) {
                cast.begin(chosenSkill, targetBuilder.getPreparedTargets(), 1);
                sprite.getSpriteEffectManager().createBlink(Colors.white.color);

                if (cast.isInstant()) {
                    skillBeginCastExecution();
                } else {
                    fighterState = FighterState.CASTING;
                }
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
        sprite.stopAnimation(Animation.cast);
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

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnemy() {
        return !isPlayer;
    }

    public boolean isMinor() {
        return enemyRank == EnemyRank.MINOR;
    }

    public boolean isMajor() {
        return enemyRank == EnemyRank.MAJOR;
    }

    public TextureRegionEnhanced getPortrait() {
        return isLarge ? imgSetFighters.getPortrait() : sprite.getTex();
    }
}
