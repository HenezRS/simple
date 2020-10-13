package com.henez.simple.world.map.tiles;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.tiles.TileType;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class TileDetail {
    boolean walkable;
    Tile map;
    Tile deco;
    Tile obj;
    GameList<Tile> tiles;

    public TileDetail(Tile map, Tile deco, Tile obj) {
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
        return tiles.stream().anyMatch(tile -> tile.getType()==TileType.down);
    }
}
