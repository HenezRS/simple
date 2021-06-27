package com.henez.simple.world;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.misc.XY;
import com.henez.simple.stats.classes.ClassName;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.mapobjects.MapObject;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class EncounterService {

    private int encounterX;
    private int encounterY;
    private Facing encounterFacing;
    private GameList<XY> encounterPositions;
    private GameList<XY> encounterBigPositions;
    private GameList<XY> usedPositions;

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

    public boolean setEncounterPositionsAndReturnValid(GameMap map, GameList<MapObject> objects, GameList<ClassName> enemies) {
        int bigTilesRequires = (int) enemies.stream().filter(ClassName::isLarge).count();
        encounterPositions = new GameList<>();
        encounterBigPositions = new GameList<>();
        usedPositions = new GameList<>();
        encounterPositions.addAll(map.getWalkableTileCluster(encounterX, encounterY, objects, 10)
                                     .stream()
                                     .map(tile -> new XY(tile.getGx(), tile.getGy()))
                                     .collect(Collectors.toCollection(GameList::new)));

        int bigTilesFound = 0;
        for (XY pos : encounterPositions) {
            if (hasFreeNeighbours(pos, encounterPositions)) {
                encounterBigPositions.add(pos);
                addUsedPositions(pos, encounterPositions);
                bigTilesFound++;
            }
        }
        System.out.println("big tiles found " + bigTilesFound);
        return true;
    }

    private boolean hasFreeNeighbours(XY pos, GameList<XY> positions) {
        return (int) positions.stream().filter(this::notExcluded).filter(p -> posMatch(pos, p)).count() == 3;
    }

    private boolean posMatch(XY pos, XY pos2) {
        return (pos.getX() == pos2.getX() - 1 && pos.getY() == pos2.getY()) ||
                (pos.getX() == pos2.getX() - 1 && pos.getY() == pos2.getY() - 1) ||
                (pos.getX() == pos2.getX() && pos.getY() == pos2.getY() - 1);
    }

    private boolean notExcluded(XY pos) {
        return usedPositions.stream().noneMatch(p -> p.getX() == pos.getX() && p.getY() == pos.getY());
    }

    private void addUsedPositions(XY pos, GameList<XY> positions) {
        usedPositions.addAll(positions.stream().filter(p -> posMatch(pos, p)).collect(Collectors.toList()));
        usedPositions.add(pos);
    }

    private boolean getNextEncounterTile(int gx, int gy, Facing facing, GameMap map) {
        encounterFacing = facing;
        encounterX = gx + encounterFacing.tx;
        encounterY = gy + encounterFacing.ty;

        return map.getTileDetail(encounterX, encounterY).canEncounter();
    }

    public Optional<GameList<XY>> getEncounterPositionsOptional() {
        return Optional.ofNullable(encounterPositions);
    }

    public Optional<GameList<XY>> getEncounterBigPositionsOptional() {
        return Optional.ofNullable(encounterBigPositions);
    }

}
