package com.henez.simple.world;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.misc.XY;
import com.henez.simple.stats.classes.ClassName;
import com.henez.simple.utils.XYUtils;
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
    private GameList<XY> encounterPositionsFinal;

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
        boolean valid = bigTilesRequires < 1;
        encounterPositions = new GameList<>();
        encounterBigPositions = new GameList<>();
        usedPositions = new GameList<>();
        encounterPositionsFinal = new GameList<>();
        encounterPositions.addAll(map.getWalkableTileCluster(encounterX, encounterY, objects, 10)
                                     .stream()
                                     .map(tile -> new XY(tile.getGx(), tile.getGy()))
                                     .collect(Collectors.toCollection(GameList::new)));

        int bigTilesFound = 0;
        if (bigTilesRequires > 0) {
            for (XY pos : encounterPositions) {
                if (hasFreeNeighbours(pos, encounterPositions)) {
                    encounterBigPositions.add(pos);
                    addUsedPositions(pos, encounterPositions);
                    bigTilesFound++;
                }

                if (bigTilesFound >= bigTilesRequires) {
                    valid = true;
                    break;
                }
            }
        }
        System.out.println("big tiles found " + bigTilesFound);

        for (int i = 0; i < bigTilesRequires; ++i) {
            encounterPositionsFinal.add(encounterBigPositions.get(i));
        }
        encounterPositionsFinal.addAll(encounterPositions.stream().filter(this::notExcluded).collect(Collectors.toList()));

        return valid;
    }

    private boolean hasFreeNeighbours(XY pos, GameList<XY> positions) {
        return (int) positions.stream().filter(this::notExcluded).filter(p -> XYUtils.isWithinSquare(pos, p)).count() == 4;
    }

    private boolean notExcluded(XY pos) {
        return usedPositions.stream().noneMatch(p -> XYUtils.equal(p, pos));
    }

    private void addUsedPositions(XY pos, GameList<XY> positions) {
        usedPositions.addAll(positions.stream().filter(p -> XYUtils.isWithinSquare(pos, p)).collect(Collectors.toList()));
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

    public Optional<GameList<XY>> getEncounterPositionsFinalOptional() {
        return Optional.ofNullable(encounterPositionsFinal);
    }

}
