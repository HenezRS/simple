package com.henez.simple.map.mapdata;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.global.Global;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MapData {
    private int width;
    private int height;
    private GameList<MapLayer> layers;
    private Map<String,int[]> namedLayer;

    public void loadTileLayers() {
        namedLayer = new HashMap<>();
        namedLayer.put(Global.LAYER_MAP, layers.stream().filter(layer -> layer.getName().equals(Global.LAYER_MAP)).findFirst().orElse(new MapLayer()).getData());
        namedLayer.put(Global.LAYER_OBJ, layers.stream().filter(layer -> layer.getName().equals(Global.LAYER_OBJ)).findFirst().orElse(new MapLayer()).getData());
    }
    public int getTileIndex(int gx, int gy, String layerName) {
        return namedLayer.get(layerName)[(gx)+(gy*width)];
    }
}
