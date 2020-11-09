package com.henez.simple.drawer;

import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.global.Global;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.Fighter;
import com.henez.simple.world.World;

import java.util.List;

public class BattleDrawer {
    World world;

    public BattleDrawer(World world) {
        this.world = world;
    }

    public void drawBattle(Batcher batch) {
        world.getBattle().getBattleMembers().getFighterActing().ifPresent(f -> f.getSkillExecution().draw(batch));
    }

    public void drawBars(Shaper shape) {
        List<Fighter> fighters = world.getBattle().getBattleMembers().getFighters();
        fighters.forEach(f -> {
            if (f.getSkillExecution().isExecuting()) {
                shape.bar(new Rect(f.getX(), f.getY(), Global.tilePixelSize, 1), 1.0f, Colors.red.color, Colors.black.color);
            } else {
                shape.bar(new Rect(f.getX(), f.getY(), Global.tilePixelSize, 1), f.getStatSheet().getAtb().getPercent(), Colors.text_default.color, Colors.black.withAlpha(0.5f));
            }
        });
    }
}
