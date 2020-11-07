package com.henez.simple.world;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class Battle {
    private Timer battleTimer;
    private GameList<Fighter> playerParty;
    private GameList<Fighter> enemyParty;
    private GameList<Fighter> fighters;
    private GameList<Fighter> fightersWaiting;
    private GameList<Fighter> fightersReady;
    private Fighter fighterActing;
    private boolean ended = false;

    public Battle(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        battleTimer = new Timer(Global.SEC * 3);
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;

        fighters = new GameList<>();
        fighters.addAll(playerParty);
        fighters.addAll(enemyParty);

        fightersWaiting = new GameList<>();
        fightersWaiting.addAll(fighters);

        fightersReady = new GameList<>();

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
        } else {
            tickBattle();
        }
    }

    public void tickBattle() {
        if (getFighterActing().isPresent()) {
            resolveFighterActing();
        } else {
            fightersWaiting.forEach(Fighter::battleUpdate);
            populateFightersWaiting();
        }
    }

    private void resolveFighterActing() {
        if (fighterActing.skillUpdate()) {
            fighterActing.turnEnd();
            populateFightersWaiting();
        }
    }

    private void populateFightersWaiting() {
        fightersReady = fightersWaiting.stream().filter(Fighter::readyToAct).collect(Collectors.toCollection(GameList::new));
        fighterActing = fightersReady.stream().findAny().orElse(null);
        getFighterActing().ifPresent(Fighter::skillBegin);
    }

    private void endBattle() {
        ended = true;
    }

    private Optional<Fighter> getFighterActing() {
        return Optional.ofNullable(fighterActing);
    }
}
