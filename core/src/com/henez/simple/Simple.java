package com.henez.simple;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.henez.simple.debug.DebugDrawer;
import com.henez.simple.drawer.BattleDrawer;
import com.henez.simple.drawer.playerdata.InventoryDrawer;
import com.henez.simple.drawer.playerdata.PlayerMenuDrawer;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.state.GameState;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.input.In;
import com.henez.simple.menu.MenuTitle;
import com.henez.simple.misc.Framerate;
import com.henez.simple.misc.screenshotter.Screenshotter;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.shaders.Shader;
import com.henez.simple.text.Text;
import com.henez.simple.world.World;
import com.henez.simple.world.map.gamemap.impl.TestMap;

class Simple {

    private GameState gameState;
    private boolean firstDrawComplete = false;

    private In in;
    private DebugDrawer debugDrawer;
    private Framerate framerate;
    private World world;
    private BattleDrawer battleDrawer;
    private InventoryDrawer inventoryDrawer;
    private PlayerMenuDrawer playerMenuDrawer;
    private MenuTitle menuTitle;

    Simple() {
        in = new In();
        debugDrawer = new DebugDrawer();
        framerate = new Framerate();
        world = new World();
        battleDrawer = new BattleDrawer(world);
        inventoryDrawer = new InventoryDrawer();
        playerMenuDrawer = new PlayerMenuDrawer();
        menuTitle = new MenuTitle();

        init();
    }

    private void init() {
        gameState = GameState.TITLE;
    }

    public void input() {
        in.capture();
    }

    public void update() {
        framerate.update();

        if (gameState == GameState.PLAY) {
            updatePlay();
        } else {
            updateTitle();
        }
    }

    private void updatePlay() {
        world.update();

        Static.effects.update();
        Static.renderer.positionCameraOnMapObject(world.getPlayer());
    }

    private void updateTitle() {
        menuTitle.update();
        if (menuTitle.isDone()) {
            world.getPlayerData().beginNewGame(menuTitle.getNewGameData());
            world.beginNewWorld(new TestMap());
            gameState = GameState.PLAY;
        }
    }

    public void draw() {
        Batcher batch = Static.renderer.batcher;
        Shaper shape = Static.renderer.shaper;
        Gdx.gl.glClearColor(Colors.black.color.r, Colors.black.color.g, Colors.black.color.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameState == GameState.PLAY) {
            drawPlay(batch, shape);
        } else {
            drawTitle(batch, shape);
        }
    }

    private void drawPlay(Batcher batch, Shaper shape) {
        //batch 0 map ---
        batch.begin();
        batch.draw(world.getCurrentMap().getMapTex(), 0, 0);
        if (world.getState() == WorldState.BATTLE) {
            battleDrawer.drawUnderActorsBatch(batch);
        }
        batch.end();
        // ---

        //shape 0 map ---
        shape.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        if (world.getState() == WorldState.BATTLE) {
            battleDrawer.drawUnderActorsShape(shape);
        }
        shape.end();
        // ---

        batch.setShader(Shader.sprite.shader);
        Shader.sprite.shader.begin();
        Shader.sprite.shader.setUniformf("blinkAlpha", 0.0f);
        Shader.sprite.shader.end();

        //batch 1 world objects ---
        world.draw(batch);
        world.drawIcons(batch);
        // ---

        batch.setShader(null);

        //batch 2 effects ---
        batch.begin();
        if (world.getState() == WorldState.BATTLE) {
            battleDrawer.drawBattleActorsExecuting(batch);
        }
        Static.effects.draw(batch);
        batch.end();
        // ---

        //shape 1 ---
        shape.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);

        if (world.getState() == WorldState.BATTLE && world.getBattle().getBattleControl().isPaused()) {
            shape.rect(0, 0, Static.renderer.getXX(), Static.renderer.getYY(), Colors.black_50.color);
        }

        debugDrawer.drawShapeWorld(shape, world);
        if (world.getState() == WorldState.BATTLE) {
            battleDrawer.drawUiShape(shape);
        }
        shape.end();
        // ---

        //batch 3 ui ---
        batch.begin();
        if (world.getState() == WorldState.BATTLE) {
            battleDrawer.drawUiBatch(batch);

            if (world.getBattle().getBattleControl().isPaused()) {
                Static.text.drawCenter(batch, "paused", Static.renderer.getCenterX(), Static.renderer.getCenterY() - Text.TEXT_H);
            }
        } else {
            playerMenuDrawer.drawBatch(world.getPlayerData().getPlayerMenu(),batch);
            if(world.getPlayerData().getPlayerMenu().isShowBag()) {
                inventoryDrawer.drawBatch(world.getPlayerData().getInventory(), batch);
            }
        }

        debugDrawer.drawBatch(batch, world, framerate);
        batch.end();
        // ---

        //shape 2 ---
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.end();
        // ---
    }

    private void drawTitle(Batcher batch, Shaper shape) {
        batch.begin();
        batch.addTransformCamera();
        menuTitle.draw(batch, shape);
        batch.resetTransform();
        batch.end();
    }

    public void post() {
        if (!firstDrawComplete && framerate.getSecondsSinceGameStart() > 4) {
            firstDrawComplete = true;
            Screenshotter.saveScreenshot();
        }
    }
}
