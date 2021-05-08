package com.henez.simple.drawer.fighterpanel;

import com.henez.simple.Static;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.text.Text;
import com.henez.simple.world.World;

import java.util.concurrent.atomic.AtomicInteger;

public class FighterPanelDrawer {
    private World world;
    private FighterPanelDrawn fighterPanelDrawn;

    private int fighterPanelPlayerX = 240 - 95 - 57;
    private int fighterPanelStartY = 31;
    private int fighterPanelEnemyX = 240 + 95;

    public FighterPanelDrawer(World world) {
        this.world = world;
        fighterPanelDrawn = new FighterPanelDrawn();
    }

    public void drawPanelsBatch(Batcher batch) {
        AtomicInteger padding = new AtomicInteger();
        AtomicInteger paddingX = new AtomicInteger();
        Static.text.draw(batch, "players:", fighterPanelPlayerX, fighterPanelStartY - Text.TEXT_LINE_H, Colors.text_player.color);
        world.getBattle().getBattleMembers().getPlayerParty().forEach(f -> {
            fighterPanelDrawn.drawBatchPlayer(batch, Static.renderer.getX() + fighterPanelPlayerX, Static.renderer.getY() + fighterPanelStartY + (34 * padding.getAndIncrement()), f);
        });

        Static.text.draw(batch, "enemies:", fighterPanelEnemyX, fighterPanelStartY - Text.TEXT_LINE_H, Colors.text_enemy.color);
        padding.set(0);
        world.getBattle().getBattleMembers().getEnemyParty().forEach(f -> {
            if (f.isMinor()) {
                fighterPanelDrawn.drawBatchEnemyMinor(batch,
                                                      Static.renderer.getX() + fighterPanelEnemyX + (26 * paddingX.getAndIncrement()),
                                                      Static.renderer.getY() + fighterPanelStartY + (34 * padding.get()),
                                                      f);
                if (paddingX.get() > 3) {
                    paddingX.set(0);
                    padding.getAndIncrement();
                }
            } else {
                fighterPanelDrawn.drawBatchEnemy(batch, Static.renderer.getX() + fighterPanelEnemyX, Static.renderer.getY() + fighterPanelStartY + (34 * padding.getAndIncrement()), f);
            }
        });
    }

    public void drawPanelsShape(Shaper shape) {
        AtomicInteger padding = new AtomicInteger();
        AtomicInteger paddingX = new AtomicInteger();
        world.getBattle().getBattleMembers().getPlayerParty().forEach(f -> {
            fighterPanelDrawn.drawShapePlayer(shape, Static.renderer.getX() + fighterPanelPlayerX, Static.renderer.getY() + fighterPanelStartY + (34 * padding.getAndIncrement()), f);
        });

        padding.set(0);
        world.getBattle().getBattleMembers().getEnemyParty().forEach(f -> {
            if (f.isMinor()) {
                fighterPanelDrawn.drawShapeEnemyMinor(shape,
                                                      Static.renderer.getX() + fighterPanelEnemyX + (26 * paddingX.getAndIncrement()),
                                                      Static.renderer.getY() + fighterPanelStartY + (34 * padding.get()),
                                                      f);
                if (paddingX.get() > 3) {
                    paddingX.set(0);
                    padding.getAndIncrement();
                }
            } else {
                fighterPanelDrawn.drawShapeEnemy(shape, Static.renderer.getX() + fighterPanelEnemyX, Static.renderer.getY() + fighterPanelStartY + (34 * padding.getAndIncrement()), f);
            }
        });
    }
}
