package com.henez.simple.world;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import lombok.Getter;

@Getter
public class Battle {
    private Timer battleTimer;
    private GameList<Fighter> playerParty;
    private GameList<Fighter> enemyParty;
    private GameList<Fighter> fighters;
    private boolean ended = false;

    public Battle(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        battleTimer = new Timer(Global.SEC * 3);
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;

        fighters = new GameList<>();
        fighters.addAll(playerParty);
        fighters.addAll(enemyParty);

        setFighterFacing();
    }

    private void setFighterFacing() {
        int playerX = playerParty.last().getGx();
        int enemyX = enemyParty.last().getGx();

        playerParty.forEach(f -> f.setFacing2(Facing.byDir2(f.getGx(), enemyX)));
        enemyParty.forEach(f -> f.setFacing2(Facing.byDir2(f.getGx(), playerX)));
    }

    public void update() {
        if (battleTimer.update()) {
            endBattle();
        }
    }

    private void endBattle() {
        ended = true;
    }
}
