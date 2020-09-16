package com.henez.simple;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.debug.DebugDrawer;
import com.henez.simple.enums.Colors;
import com.henez.simple.input.In;
import com.henez.simple.map.Map;
import com.henez.simple.map.MapDataReader;
import com.henez.simple.misc.Framerate;
import com.henez.simple.misc.screenshotter.Screenshotter;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;

class Simple {

    private boolean firstDrawComplete = false;

    private In in;
    private DebugDrawer debugDrawer;
    private Framerate framerate;
    private Map map;
    private MapDataReader mapDataReader;

    Simple() {
        in = new In();
        debugDrawer = new DebugDrawer();
        framerate = new Framerate();
        map = new Map();
        mapDataReader = new MapDataReader();

        init();
    }

    private void init() {
        mapDataReader.read();
    }

    public void input() {
        in.capture();
    }

    public void update() {
        framerate.update();
    }

    public void draw() {
        Batcher batch = Static.renderer.batcher;
        Shaper shape = Static.renderer.shaper;
        Gdx.gl.glClearColor(Colors.black.color.r, Colors.black.color.g, Colors.black.color.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //batch.draw(Atlas.getImgTiles(ImgTiles.GRASS), 16, 16);
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);

        shape.end();

        batch.begin();
        debugDrawer.draw(batch, framerate);
        batch.end();
    }

    public void post() {
        if (!firstDrawComplete && framerate.getSecondsSinceGameStart() > 4) {
            firstDrawComplete = true;
            Screenshotter.saveScreenshot();
        }
    }
}
