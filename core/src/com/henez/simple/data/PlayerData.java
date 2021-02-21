package com.henez.simple.data;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.mapobjects.ActorFactory;
import com.henez.simple.world.mapobjects.ControlledPlayer;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class PlayerData {
    private NewGameData newGameData;
    private ControlledPlayer controlledPlayer;
    private GameList<Fighter> playerParty;
    private int stepsUntilEncounter;
    private int stepsUntilEncounterMax = 8;
    private int gold;

    public PlayerData() {
    }

    public void beginNewGame(NewGameData newGameData) {
        this.newGameData = newGameData;
        gold = 0;
        AtomicInteger depth = new AtomicInteger();
        resetEncounterSteps();

        controlledPlayer = ActorFactory.createControlledPlayer(depth.getAndIncrement(), newGameData.getClasses().get(0));
        playerParty = new GameList<>();
        playerParty.add(controlledPlayer);

        newGameData.getClasses().subList(1, newGameData.getClasses().size()).forEach(className -> {
            playerParty.add(ActorFactory.createPlayer(depth.getAndIncrement(), className));
        });
        controlledPlayer.setParty(playerParty);

    }

    public void setPartyPosition(int gx, int gy) {
        playerParty.forEach(p -> p.setPosition(gx, gy));
    }

    public void deductEncounterStep() {
        stepsUntilEncounter--;
    }

    public void resetEncounterSteps() {
        stepsUntilEncounter = stepsUntilEncounterMax;
    }

    public boolean readyForEncounter() {
        return stepsUntilEncounter < 0 && controlledPlayer.canEncounter();
    }
}
