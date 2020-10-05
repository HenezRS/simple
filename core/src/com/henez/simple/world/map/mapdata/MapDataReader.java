package com.henez.simple.world.map.mapdata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.henez.simple.global.Global;

public class MapDataReader {
    public MapDataReader() {
    }

    public MapData read(String mapFileName) {
        Gson gson = new Gson();
        FileHandle fileHandle = Gdx.files.local(Global.PATH_MAPS + String.format("%s.json",mapFileName));
        MapData mapData = gson.fromJson(fileHandle.readString(), MapData.class);
        mapData.loadTileLayers();
        return mapData;
    }
}
