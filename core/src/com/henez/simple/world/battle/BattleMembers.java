package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.skills.SkillTargetBuilder;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class BattleMembers {
    private GameList<Fighter> playerParty;
    private GameList<Fighter> enemyParty;
    private GameList<Fighter> fighters;
    private GameList<Fighter> fightersWaiting;
    private GameList<Fighter> fightersReady;
    private Fighter fighterActing;

    public BattleMembers(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;

        fighters = new GameList<>();
        fighters.addAll(playerParty);
        fighters.addAll(enemyParty);

        fightersWaiting = new GameList<>();
        fightersWaiting.addAll(fighters);

        fightersReady = new GameList<>();

        fightersWaiting.forEach(Fighter::battleStart);

        setFighterFacing();
    }

    public void processTurn() {
        tickFightersWaiting();
        updateMembers();
    }

    public void updateMembers() {
        do {
            populateFightersReady();
            populateFighterActing();
            processFighterActing();
        }
        while (getFighterActing().isEmpty() && !fightersReady.isEmpty());
    }

    public void tickFightersWaiting() {
        getFightersWaiting().forEach(Fighter::battleUpdate);
    }

    public void populateFightersReady() {
        fightersReady = fightersWaiting.stream().filter(Fighter::readyToAct).collect(Collectors.toCollection(GameList::new));
    }

    private void populateFighterActing() {
        fighterActing = fightersReady.stream().findAny().orElse(null);
    }

    private void processFighterActing() {
        Optional<Fighter> acting = getFighterActing();
        acting.ifPresent(f -> f.determineSkillCast(target()));
        if (acting.isPresent() && !acting.get().getCast().isDone()) {
            fighterActing = null;
        }
    }

    private void setFighterFacing() {
        int playerX = playerParty.last().getGx();
        int enemyX = enemyParty.last().getGx();

        playerParty.forEach(f -> f.setFacing2(Facing.byDir2(f.getGx(), enemyX)));
        enemyParty.forEach(f -> f.setFacing2(Facing.byDir2(f.getGx(), playerX)));
    }

    public Optional<Fighter> getFighterActing() {
        return Optional.ofNullable(fighterActing);
    }

    private SkillTargetBuilder target() {
        return new SkillTargetBuilder(fighterActing, fighters);
    }
}
