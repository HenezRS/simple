package com.henez.simple.world.map.tiles;

import com.henez.simple.atlas.imgs.ImgTiles;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.datastructures.Numbers;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public class TilePool {
    private GameList<ImgTiles> tiles;
    private float chance = 0;
    private float rotation = 0;
    private boolean drawable = true;

    public TilePool(ImgTiles... tiles) {
        this.tiles = Arrays.stream(tiles).collect(Collectors.toCollection(GameList::new));
    }

    public ImgTiles getRandom() {
        if (chance > 0) {
            return tiles.stream().filter(tile -> Numbers.rollPercent(chance)).findFirst().orElse(tiles.last());
        }
        return tiles.random();
    }

    public TilePool withBasicChance(float chance) {
        this.chance = chance;
        return this;
    }

    public TilePool withRotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public TilePool dontDraw() {
        drawable = false;
        return this;
    }
}
