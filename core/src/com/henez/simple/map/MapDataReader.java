package com.henez.simple.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.henez.simple.global.Global;
import com.henez.simple.map.mapdata.MapData;
import com.henez.simple.misc.screenshotter.ScreenshotterSaver;

public class MapDataReader {
    public MapDataReader() {

    }

    public MapData read() {
        Gson gson = new Gson();
        FileHandle fileHandle = Gdx.files.local(Global.PATH_MAPS + "map_0.json");
        return gson.fromJson(fileHandle.readString(), MapData.class);
    }
}
