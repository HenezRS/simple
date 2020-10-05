package com.henez.simple.world.map.tiles;

import com.henez.simple.enums.tiles.TileType;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public class TileDetail {
    boolean walkable;

    public TileDetail(Tile map, Tile deco, Tile obj) {
        walkable = Stream.of(map, deco, obj).allMatch(tile -> tile.getType().isWalkable());
        if (deco.getType() == TileType.bridge && obj.getType().isWalkable()) {
            walkable = true;
        }
    }
}
