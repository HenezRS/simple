package com.henez.simple.debug;

import com.henez.simple.Static;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.global.Global;
import com.henez.simple.input.In;
import com.henez.simple.misc.Framerate;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.text.Text;
import com.henez.simple.world.World;

import java.util.concurrent.atomic.AtomicInteger;

public class DebugDrawer {
    private GameList<String> lines;
    private int ts = Global.tilePixelSize;

    public DebugDrawer() {
        lines = new GameList<>();
    }

    public void draw(Batcher batch, World world, Framerate framerate) {
        lines.clear();

        if (world.getState() == WorldState.BATTLE) {
            lines.add(String.format("BATTLE: %ss", world.getBattle().getBattleTimer().getSeconds()));
        } else {
            lines.add(String.format("ENC: %s steps", world.getStepsUntilEncounter()));
        }

        lines.add(In.showHeld());
        lines.add(String.format("FPS: %s - Time: %s", framerate.getFrameRate(), framerate.getSecondsSinceGameStart()));
        lines.add(String.format("camera: %s,%s", Static.renderer.getX(), Static.renderer.getY()));
        lines.add(String.format("mouse: %s,%s [%s,%s] {%s,%s}",
                                In.mouse.getWx(),
                                In.mouse.getWy(),
                                In.mouse.getGx(),
                                In.mouse.getGy(),
                                In.mouse.getX(),
                                In.mouse.getY()));
        lines.add(String.format("player: %s,%s [%s,%s]",
                                world.getPlayer().getX(),
                                world.getPlayer().getY(),
                                world.getPlayer().getGx(),
                                world.getPlayer().getGy()));

        int height = lines.size() * Text.TEXT_LINE_H;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        lines.forEach(line -> {
            atomicInteger.getAndIncrement();
            Static.text.draw(batch, line, Static.renderer.getX() + 4, Static.renderer.getYY() - (height + 4 + Text.TEXT_H) + (atomicInteger.get() * Text.TEXT_LINE_H));
        });
    }

    public void drawShapes(Shaper shape, World world) {
        //drawMouseSquare(shape);
        //drawEncounterSquares(shape, world);
    }

    private void drawMouseSquare(Shaper shape) {
        shape.rect(new Rect(In.mouse.getGx() * ts, In.mouse.getGy() * ts), Colors.text_default.mul(0.75f, 0.35f));
    }

    private void drawEncounterSquares(Shaper shape, World world) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        world.getEncounterService()
             .getEncounterPositionsOptional()
             .ifPresent(positions -> positions.forEach(xy -> shape.rect(new Rect(xy.getX() * ts, xy.getY() * ts),
                                                                        Colors.red.mul(0.75f - (0.10f * atomicInteger.getAndIncrement()), 0.70f - (0.10f * atomicInteger.get())))));

        shape.rectOutline(new Rect(world.getEncounterService().getEncounterX() * ts, world.getEncounterService().getEncounterY() * ts), Colors.blue.color);
    }
}
