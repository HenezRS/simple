package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.effect.EffectFactory;
import com.henez.simple.enums.Colors;
import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Battle {
    private Timer turnTimer;
    private Timer battleTimer;
    private Timer victoryTimer;
    private Timer xpTimer;
    private Timer goldTimer;
    private Timer endTimer;
    private BattleControl battleControl;
    private BattleMembers battleMembers;
    private Fighter player;
    private GameList<Fighter> playerParty;
    private boolean resultScreen;
    private boolean ended;
    private boolean playerWin;

    public Battle(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        ended = false;
        resultScreen = false;
        playerWin = false;
        turnTimer = new Timer(1);
        battleTimer = new Timer();
        battleControl = new BattleControl(playerParty.first(), enemyParty.first());
        battleMembers = new BattleMembers(playerParty, enemyParty);
        this.playerParty = playerParty;
        player = playerParty.first();

        victoryTimer = new Timer(Global.SEC2);
        xpTimer = new Timer(Global.SEC + Global.SEC2);
        goldTimer = new Timer((Global.SEC * 2) + Global.SEC2);
        endTimer = new Timer((Global.SEC * 3) + Global.SEC2);

        seperateUpdateSpriteFrames(enemyParty);
    }

    public void update() {
        if (resultScreen) {
            if (victoryTimer.update()) {
                victoryTimer.setDisabled(true);
                EffectFactory.createPopText(player, "victory!", Colors.text_default.color);
            }
            if (xpTimer.update()) {
                xpTimer.setDisabled(true);
                playerParty.forEach(fighter -> {
                    if (!fighter.isDead()) {
                        EffectFactory.createXpText(fighter, "123");
                    }
                });
            }
            if (goldTimer.update()) {
                goldTimer.setDisabled(true);
                EffectFactory.createGoldText(player, "500");
            }
            if (endTimer.update()) {
                endBattle();
            }
        } else if (!ended) {
            battleControl.captureInput(battleMembers.getFighters());
            battleTimer.update();
            tickBattle();
        }
    }

    private void tickBattle() {
        turnTimer.reset();
        battleMembers.processTurn();
        if (battleMembers.enemyWin()) {
            playerWin = false;
            showResults();
        } else if (battleMembers.playerWin()) {
            playerWin = true;
            showResults();
        }
    }

    private void seperateUpdateSpriteFrames(GameList<Fighter> fighters) {
        AtomicInteger index = new AtomicInteger();
        fighters.forEach(fighter -> {
            for (int i = 0; i < index.get() * 8; ++i) {
                fighter.getSprite().update();
            }
            index.getAndIncrement();
        });
    }

    private void showResults() {
        resultScreen = true;
    }

    private void endBattle() {
        ended = true;
    }
}
