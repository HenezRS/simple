package com.henez.simple.map.mapdata;

import com.henez.simple.datastructures.GameList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapData {
    private int width;
    private int height;
    private GameList<MapLayer> layers;
}
