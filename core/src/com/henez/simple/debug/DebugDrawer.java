package com.henez.simple.debug;

import com.henez.simple.Static;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.datastructures.Numbers;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.Facing;
import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.global.Global;
import com.henez.simple.input.In;
import com.henez.simple.misc.Framerate;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.text.Text;
import com.henez.simple.world.World;
import com.henez.simple.world.battle.BattleMembers;
import com.henez.simple.world.mapobjects.ControlledPlayer;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DebugDrawer {
    private GameList<String> lines;
    private int ts = Global.tilePixelSize;

    public DebugDrawer() {
        lines = new GameList<>();
    }

    public void drawBatch(Batcher batch, World world, Framerate framerate) {

        if (world.getState() == WorldState.BATTLE) {
            if (DebugFlags.drawBattleQueues) {
                drawBattleBatch(batch, world);
            }
        }

        drawBatchWorld(batch, world);
        if (DebugFlags.drawBasicInfo) {
            drawBasicInfo(batch, world, framerate);
        }
    }

    private void drawBasicInfo(Batcher batch, World world, Framerate framerate) {
        lines.clear();
        if (world.getState() == WorldState.BATTLE) {
            lines.add(String.format("BATTLE: %ss", world.getBattle().getBattleTimer().getSeconds()));
        } else {
            lines.add(String.format("ENC: %s steps", world.getPlayerData().getStepsUntilEncounter()));
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

    private void drawBattleBatch(Batcher batch, World world) {
        BattleMembers battleMembers = world.getBattle().getBattleMembers();

        AtomicInteger x = new AtomicInteger(50);
        int y = 160;
        AtomicInteger index = new AtomicInteger();
        Static.text.drawToCamera(batch, "waiting", x.get(), y);
        battleMembers.getFightersWaiting().forEach(fighter -> {
            batch.drawToCamera(fighter.getSprite().getTex().getTex(), x.get() + (index.getAndIncrement() * 16), y + Text.TEXT_LINE_H, Facing.LEFT, fighter.getSize());
        });

        x.set(x.get() + 80);
        index.set(0);
        Static.text.drawToCamera(batch, "casting", x.get(), y);
        battleMembers.getFightersCasting().forEach(fighter -> {
            batch.drawToCamera(fighter.getSprite().getTex().getTex(), x.get() + (index.getAndIncrement() * 16), y + Text.TEXT_LINE_H, Facing.LEFT, fighter.getSize());
        });

        x.set(x.get() + 80);
        index.set(0);
        Static.text.drawToCamera(batch, "executing", x.get(), y);
        battleMembers.getFightersExecuting().forEach(fighter -> {
            batch.drawToCamera(fighter.getSprite().getTex().getTex(), x.get() + (index.getAndIncrement() * 16), y + Text.TEXT_LINE_H, Facing.LEFT, fighter.getSize());
        });
    }

    public void drawShapeWorld(Shaper shape, World world) {
        //drawMouseSquare(shape);
        drawEncounterSquares(shape, world);
    }

    public void drawBatchWorld(Batcher batch, World world) {
        if (DebugFlags.drawPlayerAnimationInfo) {
            drawControlledPlayerAnimationsList(batch, world.getPlayer());
        }
    }

    private void drawControlledPlayerAnimationsList(Batcher batch, ControlledPlayer player) {
        Map<Animation, SpriteAnimation> playerAnimations = player.getSprite().getAnimations();
        AtomicInteger y = new AtomicInteger();
        Arrays.stream(Animation.values()).filter(animation -> player.getSprite().hasAnimation(animation)).forEach(animation -> {

            Static.text.drawToCamera(batch,
                                     String.format("%s: F%s %s/%s *%s",
                                                   animation.name(),
                                                   playerAnimations.get(animation).getCurrentFrame(),
                                                   playerAnimations.get(animation).getTick(),
                                                   playerAnimations.get(animation).getDelay(),
                                                   playerAnimations.get(animation).getSpeed()),
                                     20,
                                     20 + y.getAndIncrement() * Text.TEXT_LINE_H);

        });
    }

    private void drawMouseSquare(Shaper shape) {
        shape.rect(new Rect(In.mouse.getGx() * ts, In.mouse.getGy() * ts), Colors.text_default.mul(0.75f, 0.35f));
    }

    private void drawEncounterSquares(Shaper shape, World world) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        world.getEncounterService()
             .getEncounterPositionsOptional()
             .ifPresent(positions -> positions.forEach(xy -> shape.rect(new Rect(xy.getX() * ts, xy.getY() * ts),
                                                                        Colors.red.mul(0.75f - (0.10f * Numbers.clamp(atomicInteger.getAndIncrement(), 0, 4)),
                                                                                       0.70f - (0.10f * Numbers.clamp(atomicInteger.get(), 0, 4))))));

        world.getEncounterService()
             .getEncounterBigPositionsOptional()
             .ifPresent(positions -> positions.forEach(xy -> shape.rectOutline(new Rect(xy.getX() * ts, xy.getY() * ts, 32, 32), Colors.purple_raw.color)));

        //shape.rectOutline(new Rect(world.getEncounterService().getEncounterX() * ts, world.getEncounterService().getEncounterY() * ts), Colors.blue.color);
    }
}
