package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Battle {
    private Timer turnTimer;
    private Timer battleTimer;
    private BattleMembers battleMembers;
    private boolean ended = false;

    public Battle(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        turnTimer = new Timer(1);
        battleTimer = new Timer();
        battleMembers = new BattleMembers(playerParty, enemyParty);

        seperateUpdateSpriteFrames(enemyParty);
    }

    public void update() {
        battleTimer.update();
        tickBattle();
    }

    private void tickBattle() {
        turnTimer.reset();
        battleMembers.processTurn();
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

    private void endBattle() {
        ended = true;
    }
}
