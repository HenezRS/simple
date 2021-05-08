package com.henez.simple.drawer.battlecontrols;

import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.utils.RectUtils;
import com.henez.simple.world.World;
import com.henez.simple.world.battle.BattleControl;

public class BattleControlDrawer {
    private int skillX = 195;
    private int skillY = 228;
    private int skillWH = 18;
    private int skillWW = 24;

    private int skillNameX = 194;
    private int skillNameY = 214;

    private int skillBarX = 193;
    private int skillBarY = 220;
    private int skillBarW = 94;
    private int skillBarH = 5;

    private int selectedX = 207;
    private int selectedY = 190;
    private int selectedWH = 22;
    private int selectedWW = 44;
    private int arrowX = 233;
    private int arrowY = 197;

    public void drawTargetBatch(World world, Batcher batch) {
        BattleControl control = world.getBattle().getBattleControl();
    }

    public void drawTargetShape(World world, Shaper shape) {
        BattleControl control = world.getBattle().getBattleControl();
        shape.rectOutline(RectUtils.get(control.getEnemyTarget()), Colors.red_50.color);
        shape.rectOutline(RectUtils.get(control.getPlayerTarget()), Colors.green_50.color);
        if (control.getMouseOverTarget() != null) {
            shape.rectOutline(RectUtils.get(control.getMouseOverTarget()), Colors.white_30.color);
        }

    }

    public void drawBatch(World world, Batcher batch) {
        BattleControl control = world.getBattle().getBattleControl();

    }

    public void drawShape(World world, Shaper shape) {
        BattleControl control = world.getBattle().getBattleControl();

    }
}
