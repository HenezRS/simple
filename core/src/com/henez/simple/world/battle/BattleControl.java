package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.input.In;
import com.henez.simple.input.Key;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public class BattleControl {
    private GameList<Key> skillKeys;
    private int nextSkillIndex;
    private Fighter enemyTarget;
    private Fighter playerTarget;
    private Fighter mouseOverTarget;

    public BattleControl(Fighter firstPlayerTarget, Fighter firstEnemyTarget) {
        skillKeys = new GameList<>();
        skillKeys.addAll(In.skill1, In.skill2, In.skill3, In.skill4);
        this.playerTarget = firstPlayerTarget;
        this.enemyTarget = firstEnemyTarget;
    }

    public void captureInput(GameList<Fighter> fighters) {
        nextSkillIndex = 0;

        for (int i = 0; i < skillKeys.size(); ++i) {
            if (skillKeys.get(i).isHeld()) {
                nextSkillIndex = i;
                i = skillKeys.size();
            }
        }

        mouseOverTarget = null;
        fighters.forEach(fighter -> {
            if (fighter.isMouseOver()) {
                mouseOverTarget = fighter;
            }
            if (fighter.isClickedOn()) {
                if (fighter.isEnemy()) {
                    enemyTarget = fighter;
                } else {
                    playerTarget = fighter;
                }
            }
        });
    }
}
