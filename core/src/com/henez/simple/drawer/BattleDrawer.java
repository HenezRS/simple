package com.henez.simple.drawer;

import com.henez.simple.Static;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.World;

import java.util.concurrent.atomic.AtomicInteger;

public class BattleDrawer {
    private World world;
    private FighterPanelDrawer fighterPanelDrawer;

    private int fighterPanelPlayerX = 240 + 95;
    private int fighterPanelEnemyX = 240 - 95 - 57;

    public BattleDrawer(World world) {
        this.world = world;
        fighterPanelDrawer = new FighterPanelDrawer();
    }

    public void drawBattle(Batcher batch) {
        world.getBattle().getBattleMembers().getFightersExecuting().forEach(f -> f.getSkillExecution().draw(batch));
    }

    public void drawPanelsBatch(Batcher batch) {
        AtomicInteger padding = new AtomicInteger();
        world.getBattle().getBattleMembers().getPlayerParty().forEach(f -> {
            fighterPanelDrawer.drawBatch(batch, Static.renderer.getX() + fighterPanelPlayerX, Static.renderer.getY() + 31 + (34 * padding.getAndIncrement()), f);
        });

        padding.set(0);
        world.getBattle().getBattleMembers().getEnemyParty().forEach(f -> {
            fighterPanelDrawer.drawBatch(batch, Static.renderer.getX() + fighterPanelEnemyX, Static.renderer.getY() + 31 + (34 * padding.getAndIncrement()), f);
        });
    }

    public void drawPanelsShape(Shaper shape) {
        AtomicInteger padding = new AtomicInteger();
        world.getBattle().getBattleMembers().getPlayerParty().forEach(f -> {
            fighterPanelDrawer.drawShape(shape, Static.renderer.getX() + fighterPanelPlayerX, Static.renderer.getY() + 31 + (34 * padding.getAndIncrement()), f);
        });

        padding.set(0);
        world.getBattle().getBattleMembers().getEnemyParty().forEach(f -> {
            fighterPanelDrawer.drawShape(shape, Static.renderer.getX() + fighterPanelEnemyX, Static.renderer.getY() + 31 + (34 * padding.getAndIncrement()), f);
        });
    }
}
