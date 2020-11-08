package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.world.Fighter;
import lombok.Getter;

@Getter
public class Battle {
    private Timer turnTimer;
    private Timer battleTimer;
    private BattleMembers battleMembers;
    private boolean ended = false;

    public Battle(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        turnTimer = new Timer(Global.SEC);
        battleTimer = new Timer();
        battleMembers = new BattleMembers(playerParty, enemyParty);
    }

    public void update() {
        battleTimer.update();
        battleMembers.getFighterActing().ifPresentOrElse(this::resolveFighterActing, this::tickBattle);
    }

    public void tickBattle() {
        if (turnTimer.update()) {
            turnTimer.reset();
            battleMembers.processTurn();
        }
    }

    private void resolveFighterActing(Fighter fighterActing) {
        if (fighterActing.skillUpdate()) {
            fighterActing.turnEnd();
            battleMembers.updateMembers();
        }
    }

    private void endBattle() {
        ended = true;
    }
}
