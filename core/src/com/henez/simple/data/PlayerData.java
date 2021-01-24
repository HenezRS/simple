package com.henez.simple.data;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.mapobjects.ActorFactory;
import com.henez.simple.world.mapobjects.ControlledPlayer;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerData {
    private ControlledPlayer controlledPlayer;
    private GameList<Fighter> playerParty;
    private int stepsUntilEncounter;
    private int stepsUntilEncounterMax = 8;
    private int gold;

    public PlayerData() {
        gold = 0;
        int depth = 0;
        resetEncounterSteps();

        controlledPlayer = ActorFactory.createControlledPlayer(depth++, ImgSetFighters.class_kni);
        playerParty = new GameList<>();
        playerParty.add(controlledPlayer);
        playerParty.add(ActorFactory.createPlayer(depth++, ImgSetFighters.class_kni));
        playerParty.add(ActorFactory.createPlayer(depth++, ImgSetFighters.class_kni));
        playerParty.add(ActorFactory.createPlayer(depth, ImgSetFighters.class_kni));
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
