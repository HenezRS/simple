package com.henez.simple.world.map.tiles;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.tiles.TileType;
import com.henez.simple.utils.MapObjectUtils;
import com.henez.simple.world.mapobjects.MapObject;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class TileDetail {
    boolean walkable;
    int gx, gy;
    Tile map;
    Tile deco;
    Tile obj;
    GameList<Tile> tiles;

    public TileDetail(Tile map, Tile deco, Tile obj) {
        this.gx = map.getGx();
        this.gy = map.getGy();
        this.map = map;
        this.deco = deco;
        this.obj = obj;
        this.tiles = Stream.of(map, deco, obj).collect(Collectors.toCollection(GameList::new));
        walkable = this.tiles.stream().allMatch(tile -> tile.getType().isWalkable());
        if (deco.getType() == TileType.bridge && obj.getType().isWalkable()) {
            walkable = true;
        }
    }

    public boolean isExit() {
        return tiles.stream().anyMatch(tile -> tile.getType() == TileType.down);
    }

    public boolean canEncounter() {
        return walkable;
    }

    public boolean isWalkableWithObjects(GameList<MapObject> objects) {
        return walkable && MapObjectUtils.positionIsEmpty(gx, gy, objects);
    }
}
