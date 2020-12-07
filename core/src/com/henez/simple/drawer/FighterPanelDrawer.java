package com.henez.simple.drawer;

import com.henez.simple.Static;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.StatName;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.Fighter;

public class FighterPanelDrawer {
    private int w = 57;
    private int h = 22;

    private int iconHpX = 23;
    private int iconHpY = 1;
    private int iconMpX = iconHpX;
    private int iconMpY = 11;

    private int textHpX = 54;
    private int textHpY = 2;
    private int textMpX = textHpX;
    private int textMpY = 12;

    public FighterPanelDrawer() {
    }

    public void drawBatch(Batcher batch, int x, int y, Fighter fighter) {
        Static.text.drawRight(batch, fighter.getStatSheet().getStatCur(StatName.HP) + "", x + textHpX, y + textHpY);
    }

    public void drawShape(Shaper shape, int x, int y, Fighter fighter) {
        shape.rect(x, y, w, h, Colors.ui_back.color);
    }
}
