package com.henez.simple;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.henez.simple.debug.DebugDrawer;
import com.henez.simple.enums.Colors;
import com.henez.simple.input.In;
import com.henez.simple.misc.Framerate;
import com.henez.simple.misc.screenshotter.Screenshotter;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.World;

class Simple {

    private boolean firstDrawComplete = false;

    private In in;
    private DebugDrawer debugDrawer;
    private Framerate framerate;
    private World world;

    Simple() {
        in = new In();
        debugDrawer = new DebugDrawer();
        framerate = new Framerate();
        world = new World();

        init();
    }

    private void init() {
    }

    public void input() {
        in.capture();
    }

    public void update() {
        framerate.update();
        world.update();
        Static.renderer.positionCameraOnMapObject(world.getPlayer());
    }

    public void draw() {
        Batcher batch = Static.renderer.batcher;
        Shaper shape = Static.renderer.shaper;
        Gdx.gl.glClearColor(Colors.black.color.r, Colors.black.color.g, Colors.black.color.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(world.getCurrentMap().getMapTex(), 0, 0);
        world.getDrawables().forEach(batch::draw);
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        debugDrawer.draw(shape, world);
        shape.end();

        batch.begin();
        debugDrawer.draw(batch, world, framerate);
        batch.end();
    }

    public void post() {
        if (!firstDrawComplete && framerate.getSecondsSinceGameStart() > 4) {
            firstDrawComplete = true;
            Screenshotter.saveScreenshot();
        }
    }
}
