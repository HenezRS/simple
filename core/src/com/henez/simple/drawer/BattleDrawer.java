package com.henez.simple.drawer;

import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.global.Global;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.Fighter;
import com.henez.simple.world.World;

import java.util.List;

public class BattleDrawer {
    World world;

    public BattleDrawer(World world) {
        this.world = world;
    }

    public void drawBars(Shaper shape) {
        List<Fighter> fighters = world.getBattle().getFighters();
        fighters.forEach(f -> {
            if (f.getSkillExecution().isExecuting()) {
                shape.rectGrid(f.getGx(), f.getGy(), Colors.red.color);
            }
            shape.bar(new Rect(f.getX(), f.getY(), Global.tilePixelSize, 1), f.getStatSheet().getAtb().getPercent(), Colors.text_default.color, Colors.black.color);
            System.out.println(f.getStatSheet().getAtb().getPercent());
        });
    }
}
