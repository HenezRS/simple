package com.henez.simple.drawer;

import com.henez.simple.Static;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.World;
import com.henez.simple.world.mapobjects.Fighter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BattleDrawer {
    private World world;
    private FighterPanelDrawer fighterPanelDrawer;

    public BattleDrawer(World world) {
        this.world = world;
        fighterPanelDrawer = new FighterPanelDrawer();
    }

    public void drawBattle(Batcher batch) {
        world.getBattle().getBattleMembers().getFightersExecuting().forEach(f -> f.getSkillExecution().draw(batch));
    }

    public void drawPanelsBatch(Batcher batch) {
        List<Fighter> fighters = world.getBattle().getBattleMembers().getPlayerParty();
        AtomicInteger padding = new AtomicInteger();
        fighters.forEach(f -> {
            fighterPanelDrawer.drawBatch(batch, Static.renderer.getX() + 303, Static.renderer.getY() + 31 + (34 * padding.getAndIncrement()), f);
        });
    }

    public void drawPanelsShape(Shaper shape) {
        List<Fighter> fighters = world.getBattle().getBattleMembers().getPlayerParty();
        AtomicInteger padding = new AtomicInteger();
        fighters.forEach(f -> {
            /*if (f.getSkillExecution().isExecuting()) {
                shape.bar(new Rect(f.getX(), f.getY(), Global.tilePixelSize, 1), 1.0f, Colors.red.color, Colors.black.color);
            } else {
                shape.bar(new Rect(f.getX(), f.getY(), Global.tilePixelSize, 1), f.getStatSheet().getAtb().getPercent(), Colors.text_default.color, Colors.black.withAlpha(0.5f));
            }*/
            fighterPanelDrawer.drawShape(shape, Static.renderer.getX() + 303, Static.renderer.getY() + 31 + (34 * padding.getAndIncrement()), f);
        });
    }
}
