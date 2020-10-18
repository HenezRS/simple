package com.henez.simple.world;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.misc.XY;
import com.henez.simple.world.map.gamemap.GameMap;
import lombok.Getter;

@Getter
public class EncounterService {

    private int encounterX;
    private int encounterY;
    private Facing encounterFacing;
    private GameList<XY> encounterPositions;

    public EncounterService() {
    }

    public void reset() {

    }

    public boolean canEncounter(int gx, int gy, Facing facing, GameMap map) {
        if (getNextEncounterTile(gx, gy, facing, map)) {
            return true;
        }

        if (getNextEncounterTile(gx, gy, facing.getNextRight(), map)) {
            return true;
        }

        return getNextEncounterTile(gx, gy, facing.getNextLeft(), map);
    }

    public boolean setEncounterPositionsAndReturnValid(GameMap map) {
        encounterPositions = new GameList<>();
        encounterPositions.add(new XY(encounterX, encounterY));

        for (int i = 0; i < 3; ++i) {
            if (canEncounter(encounterX, encounterY, encounterFacing, map)) {
                encounterPositions.add(new XY(encounterX, encounterY));
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean getNextEncounterTile(int gx, int gy, Facing facing, GameMap map) {
        encounterFacing = facing;
        encounterX = gx + encounterFacing.tx;
        encounterY = gy + encounterFacing.ty;

        return map.getTileDetail(encounterX, encounterY).canEncounter();
    }

}
