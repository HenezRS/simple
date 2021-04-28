package com.henez.simple.drawer;

import com.badlogic.gdx.graphics.Color;
import com.henez.simple.Static;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIcon7;
import com.henez.simple.atlas.imgs.ImgTiles;
import com.henez.simple.datastructures.Numbers;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.Facing;
import com.henez.simple.enums.StatName;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.ShapeFactory;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.mapobjects.Fighter;
import com.henez.simple.world.mapobjects.FighterState;

import static com.henez.simple.text.Text.TEXT_H;

public class FighterPanelDrawer {
    private int minorW = 22;
    private int minorH = 24;

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

    private Color backColor;
    private Color frameColor;

    public FighterPanelDrawer() {
    }

    public void drawBatchPlayer(Batcher batch, int x, int y, Fighter fighter) {
        Static.text.drawRight(batch, fighter.getStatSheet().getStatCur(StatName.HP) + "", x + textHpX, y + textHpY);
        Static.text.drawRight(batch, fighter.getStatSheet().getStatCur(StatName.MP) + "", x + textMpX, y + textMpY);

        batch.draw(Atlas.toTex(ImgIcon7.hp), x + iconHpX, y + iconHpY);
        batch.draw(Atlas.toTex(ImgIcon7.mp), x + iconMpX, y + iconMpY);

        if (!fighter.isDead()) {
            batch.draw(fighter.getSprite().getTex(), x + playerX, y + playerY, Facing.RIGHT);

            if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
                Static.text.draw(batch, fighter.getCast().getName() + "", x + skillNameX, y + skillNameY);
            }
        } else {
            batch.draw(ImgTiles.grave.asTex().getTex(), x + playerX, y + playerY, Facing.RIGHT);
        }
    }

    public void drawShapePlayer(Shaper shape, int x, int y, Fighter fighter) {
        backColor = Colors.ui_back_blue.color;
        frameColor = Colors.ui_frame_blue.color;

        shape.rect(x, y, w, h, backColor);
        shape.rectOutline(new Rect(x + 1, y + 1, 20, 20), frameColor);
        shape.barH1(x + barHpX, y + barHpY, barW, fighter.getStatSheet().getStatPercent(StatName.HP), Colors.hp.color, Colors.hp_bar_back.color);
        shape.barH1(x + barMpX, y + barMpY, barW, fighter.getStatSheet().getStatPercent(StatName.MP), Colors.mp.color, Colors.hp_bar_back.color);

        if (!fighter.isDead()) {
            if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
                int skillVarW = Numbers.clamp((int) Static.text.getTextRect(fighter.getCast().getName()).width + 2, skillW, 999);
                int skillBarVarW = skillVarW - 2;
                shape.rect(x + skillX, y + skillNameY - 1, skillVarW, skillH - (fighter.fighterStateIs(FighterState.CASTING) ? 0 : 2), backColor);

                if (fighter.fighterStateIs(FighterState.CASTING)) {
                    float percent = fighter.getCast().getPercent();
                    shape.barH1(x + skillBarX,
                                y + skillBarY,
                                skillBarVarW,
                                percent,
                                Colors.castbar.color, Colors.hp_bar_back.color);
                } else if (fighter.fighterStateIs(FighterState.CHANNELLING)) {
                    float percent = 1 - fighter.getCast().getPercent();
                    shape.barH1(x + skillBarX,
                                y + skillBarY,
                                skillBarVarW,
                                percent,
                                Colors.channelbar.color, Colors.hp_bar_back.color);

                    int max = fighter.getCast().getChannelExecutionMaxCount();
                    for (int i = 0; i < max; ++i) {
                        ShapeFactory.channellingDivider(shape, x + (i * (skillBarVarW / max)), y + skillBarY);
                    }
                }
            } else {
                shape.barH1(x + atbBarX, y + atbBarY, atbBarW, fighter.getStatSheet().getAtb().getPercent(), Colors.ui_bar_front.color, Colors.hp_bar_back.color);
            }
        }
    }

    public void drawBatchEnemy(Batcher batch, int x, int y, Fighter fighter) {
        Static.text.drawRight(batch, fighter.getStatSheet().getStatCur(StatName.HP) + "", x + textHpX, y + textHpY);

        batch.draw(Atlas.toTex(ImgIcon7.hp), x + iconHpX, y + iconHpY);

        if (!fighter.isDead()) {
            batch.draw(fighter.getSprite().getTex(), x + playerX, y + playerY, Facing.LEFT);

            if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
                Static.text.draw(batch, fighter.getCast().getName() + "", x + skillNameX, y + skillNameY);
            }
        } else {
            batch.draw(ImgTiles.grave.asTex().getTex(), x + playerX, y + playerY, Facing.LEFT);
        }
    }

    public void drawShapeEnemy(Shaper shape, int x, int y, Fighter fighter) {
        backColor = Colors.ui_back_red.color;
        frameColor = Colors.ui_frame_red.color;

        shape.rect(x, y, w, h, backColor);
        shape.rectOutline(new Rect(x + 1, y + 1, 20, 20), frameColor);
        shape.barH1(x + barHpX, y + barHpY, barW, fighter.getStatSheet().getStatPercent(StatName.HP), Colors.hp.color, Colors.hp_bar_back.color);
        //shape.barH1(x + barMpX, y + barMpY, barW, fighter.getStatSheet().getStatPercent(StatName.MP), Colors.mp.color, Colors.hp_bar_back.color);

        if (!fighter.isDead()) {
            if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
                int skillVarW = Numbers.clamp((int) Static.text.getTextRect(fighter.getCast().getName()).width + 2, skillW, 999);
                int skillBarVarW = skillVarW - 2;
                shape.rect(x + skillX, y + skillNameY - 1, skillVarW, skillH - (fighter.fighterStateIs(FighterState.CASTING) ? 0 : 2), backColor);

                if (fighter.fighterStateIs(FighterState.CASTING)) {
                    float percent = fighter.getCast().getPercent();
                    shape.barH1(x + skillBarX, y + skillBarY,
                                skillBarVarW,
                                percent,
                                Colors.castbar.color, Colors.hp_bar_back.color);
                } else if (fighter.fighterStateIs(FighterState.CHANNELLING)) {
                    float percent = 1 - fighter.getCast().getPercent();
                    shape.barH1(x + skillBarX, y + skillBarY,
                                skillBarVarW,
                                percent,
                                Colors.channelbar.color, Colors.hp_bar_back.color);

                    int max = fighter.getCast().getChannelExecutionMaxCount();
                    for (int i = 0; i < max; ++i) {
                        ShapeFactory.channellingDivider(shape, x + (i * (skillBarVarW / max)), y + skillBarY);
                    }
                }
            } else {
                shape.barH1(x + atbBarX, y + atbBarY, atbBarW, fighter.getStatSheet().getAtb().getPercent(), Colors.ui_bar_front.color, Colors.hp_bar_back.color);
            }
        }
    }

    public void drawBatchEnemyMinor(Batcher batch, int x, int y, Fighter fighter) {

    }

    public void drawShapeEnemyMinor(Shaper shape, int x, int y, Fighter fighter) {

    }
}
