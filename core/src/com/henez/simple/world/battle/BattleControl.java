package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.input.In;
import com.henez.simple.input.Key;
import com.henez.simple.misc.XY;
import com.henez.simple.skills.SkillName;
import com.henez.simple.utils.XYUtils;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

import java.util.Map;

@Getter
public class BattleControl {
    private GameList<Key> skillKeys;
    private int nextSkillIndex;
    private Fighter controlledPlayer;
    private Fighter enemyTarget;
    private Fighter playerTarget;
    private XY enemyTargetPos;
    private XY playerTargetPos;
    private Fighter mouseOverTarget;
    private XY mouseOverPos;
    private SkillName selectedSkill;
    private boolean paused;

    public BattleControl(Fighter controlledPlayer, Fighter firstPlayerTarget, Fighter firstEnemyTarget) {
        skillKeys = new GameList<>();
        skillKeys.addAll(In.skill1, In.skill2, In.skill3, In.skill4);
        this.controlledPlayer = controlledPlayer;
        this.playerTarget = firstPlayerTarget;
        this.enemyTarget = firstEnemyTarget;
        this.enemyTargetPos = XYUtils.from(firstEnemyTarget);
        this.playerTargetPos = XYUtils.from(firstPlayerTarget);

        nextSkillIndex = 0;
        selectedSkill = controlledPlayer.getSkillSheet().getSkills().get(nextSkillIndex);
    }

    public void captureInput(Map<XY, Fighter> enemyMap) {
        for (int i = 0; i < skillKeys.size(); ++i) {
            if (skillKeys.get(i).isHeld() && controlledPlayer.getSkillSheet().hasValidSkillInSlotIndex(i)) {
                setSelectedSkill(i);
                i = skillKeys.size();
            }
        }

        mouseOverTarget = null;
        mouseOverPos = null;
        enemyMap.forEach((xy, fighter) -> {
            if (In.mouse.isMouseWithinGrid(xy)) {
                mouseOverTarget = fighter;
                mouseOverPos = xy;
                if (In.mouse.isClicked()) {
                    if (fighter.isEnemy()) {
                        enemyTarget = fighter;
                        enemyTargetPos = xy;
                    } else {
                        playerTarget = fighter;
                        playerTargetPos = xy;
                    }
                }
            }
        });
    }

    public void setSelectedSkill(int i) {
        nextSkillIndex = i;
        selectedSkill = controlledPlayer.getSkillSheet().getSkills().get(i);
    }

    public void capturePausingInput() {
        if (In.getPauseKeyPressed()) {
            paused = !paused;
        }
    }

    private void pause() {
        paused = true;
    }

    private void unpause() {
        paused = false;
    }
}
