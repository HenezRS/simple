package com.henez.simple.drawer;

import com.henez.simple.Static;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIcon7;
import com.henez.simple.datastructures.Numbers;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.Facing;
import com.henez.simple.enums.StatName;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.mapobjects.Fighter;

import static com.henez.simple.text.Text.TEXT_H;

public class FighterPanelDrawer {
    private int w = 57;
    private int h = 24;

    private int skillX = 0;
    private int skillY = h;
    private int skillW = w;
    private int skillH = TEXT_H + 4;

    private int playerX = 3;
    private int playerY = 3;

    private int iconHpX = 23;
    private int iconHpY = 1;
    private int iconMpX = iconHpX;
    private int iconMpY = 11;

    private int textHpX = 54;
    private int textHpY = 2;
    private int textMpX = textHpX;
    private int textMpY = 12;

    private int barHpX = 23;
    private int barHpY = 9;
    private int barMpX = barHpX;
    private int barMpY = 19;
    private int barW = 32;

    private int skillNameX = 1;
    private int skillNameY = 22;
    private int skillTextMaxW = 81;

    private int skillBarX = 1;
    private int skillBarY = 28;
    private int skillBarW = 54;

    private int atbBarX = skillNameX;
    private int atbBarY = skillNameY;
    private int atbBarW = skillBarW;

    public FighterPanelDrawer() {
    }

    public void drawBatch(Batcher batch, int x, int y, Fighter fighter) {
        Static.text.drawRight(batch, fighter.getStatSheet().getStatCur(StatName.HP) + "", x + textHpX, y + textHpY);
        Static.text.drawRight(batch, fighter.getStatSheet().getStatCur(StatName.MP) + "", x + textMpX, y + textMpY);

        batch.draw(Atlas.toTex(ImgIcon7.hp), x + iconHpX, y + iconHpY);
        batch.draw(Atlas.toTex(ImgIcon7.mp), x + iconMpX, y + iconMpY);

        batch.draw(fighter.getSprite().getTex(), x + playerX, y + playerY, Facing.LEFT);

        if (fighter.getCast().inProgress() || fighter.getSkillExecution().isExecuting()) {
            Static.text.draw(batch, fighter.getCast().getName() + "", x + skillNameX, y + skillNameY);
        }
    }

    public void drawShape(Shaper shape, int x, int y, Fighter fighter) {
        shape.rect(x, y, w, h, Colors.ui_back.color);
        shape.rectOutline(new Rect(x + 1, y + 1, 20, 20), Colors.ui_frame.color);
        shape.barH1(x + barHpX, y + barHpY, barW, fighter.getStatSheet().getStatPercent(StatName.HP), Colors.hp.color, Colors.hp_bar_back.color);
        shape.barH1(x + barMpX, y + barMpY, barW, fighter.getStatSheet().getStatPercent(StatName.MP), Colors.mp.color, Colors.hp_bar_back.color);

        if (fighter.getCast().inProgress() || fighter.getSkillExecution().isExecuting()) {
            int skillVarW = Numbers.clamp((int) Static.text.getTextRect(fighter.getCast().getName()).width + 2, skillW, 999);
            shape.rect(x + skillX, y + skillNameY - 1, skillVarW, skillH, Colors.ui_back.color);

            float percent = fighter.getCast().getPercent();
            shape.barH1(x + skillBarX,
                        y + skillBarY,
                        skillVarW - 2,
                        percent,
                        fighter.getSkillExecution().isExecuting() ? Colors.castbar.mul(2f, 1) : Colors.castbar.color, Colors.hp_bar_back.color);
        } else {
            shape.barH1(x + atbBarX, y + atbBarY, atbBarW, fighter.getStatSheet().getAtb().getPercent(), Colors.ui_bar_front.color, Colors.hp_bar_back.color);
        }
    }
}
