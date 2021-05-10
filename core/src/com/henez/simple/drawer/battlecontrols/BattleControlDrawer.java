package com.henez.simple.drawer.battlecontrols;

import com.badlogic.gdx.graphics.Color;
import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.Facing;
import com.henez.simple.input.In;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.ShapeFactory;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillSheet;
import com.henez.simple.stats.Cast;
import com.henez.simple.utils.RectUtils;
import com.henez.simple.world.World;
import com.henez.simple.world.battle.BattleControl;
import com.henez.simple.world.mapobjects.Fighter;
import com.henez.simple.world.mapobjects.FighterState;

public class BattleControlDrawer {
    private int skillX = 195;
    private int skillY = 228;
    private int skillWH = 18;
    private int skillWW = 24;

    private int skillNameX = 194;
    private int skillNameY = 214;
    private int skillNameH = 10;

    private int skillBarX = 194;
    private int skillBarY = 221;
    private int skillBarW = 90;
    private int skillBarH = 3;

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
        Fighter fighter = control.getControlledPlayer();
        SkillSheet skillSheet = fighter.getSkillSheet();
        SkillName selectedSkill = control.getSelectedSkill();
        Cast cast = fighter.getCast();

        if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
            batch.drawToCamera(cast.getSkillName().getTex(), selectedX + 2, selectedY + 2);
            batch.drawToCamera(ImgUi.arrow_right_grey.asTex(), arrowX, arrowY);
            batch.drawToCamera(cast.getSkillTarget().getTarget().getSprite().getTex(), selectedX + 2 + selectedWW, selectedY + 2, Facing.LEFT);
        } else {
            batch.drawToCamera(selectedSkill.getTex(), selectedX + 2, selectedY + 2);
            batch.drawToCamera(ImgUi.arrow_right.asTex(), arrowX, arrowY);
            batch.drawToCamera(control.getEnemyTarget().getSprite().getTex(), selectedX + 2 + selectedWW, selectedY + 2, Facing.LEFT);
        }
        for (int i = 0; i < 4; ++i) {
            SkillName skillName = skillSheet.getSkills().getOrNull(i);
            if (skillName != null) {
                batch.drawToCamera(skillName.getTex(), skillX + 1 + (i * skillWW), skillY + 1);
                Static.text.drawToCamera(batch, In.getSkillKeyNameByIndex(i), skillX + 1 + (i * skillWW) + 6, skillY + 20);
            }

            if (control.getNextSkillIndex() == i) {
                batch.drawToCamera(ImgUi.button_active.asTex(), skillX + (i * skillWW) - 1, skillY - 1);
            }
        }

        if (!fighter.isDead()) {
            if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
                Static.text.drawToCamera(batch, fighter.getCast().getName() + "", skillNameX, skillNameY);
            }
        }

    }

    public void drawShape(World world, Shaper shape) {
        BattleControl control = world.getBattle().getBattleControl();
        SkillName selectedSkill = control.getSelectedSkill();
        Fighter fighter = control.getControlledPlayer();

        Color colorBack = Colors.ui_back.color;
        Color colorFrame = Colors.ui_frame.color;

        if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
            colorBack = Colors.ui_back_grey.color;
            colorFrame = Colors.ui_frame_grey.color;
        }

        shape.addCameraTransform();

        shape.rect(selectedX + 1, selectedY + 1, selectedWH - 2, selectedWH - 2, colorBack);
        shape.rectOutline(new Rect(selectedX, selectedY, selectedWH, selectedWH), colorFrame);

        shape.rect(selectedX + 1 + (selectedWW), selectedY + 1, selectedWH - 2, selectedWH - 2, colorBack);
        shape.rectOutline(new Rect(selectedX + (selectedWW), selectedY, selectedWH, selectedWH), colorFrame);

        drawAtb(shape, control.getControlledPlayer());
        for (int i = 0; i < 4; ++i) {
            shape.rect(skillX + 1 + (skillWW * i), skillY + 1, skillWH - 2, skillWH - 2, Colors.ui_back.color);
            shape.rectOutline(new Rect(skillX + (skillWW * i), skillY, skillWH, skillWH), Colors.ui_frame.color);
        }
        shape.resetTransform();
    }

    private void drawAtb(Shaper shape, Fighter fighter) {
        shape.rect(skillBarX - 1, skillBarY - 1, skillBarW + 2, skillBarH + 2, Colors.ui_back.color);
        if (fighter.fighterStateOneOf(FighterState.CASTING, FighterState.EXECUTING, FighterState.CHANNELLING)) {
            shape.rect(skillBarX - 1, skillNameY - 1, skillBarW + 2, skillNameH, Colors.ui_back.color);
            if (fighter.fighterStateIs(FighterState.CASTING)) {
                float percent = fighter.getCast().getPercent();
                shape.barH(skillBarX,
                           skillBarY,
                           skillBarW, 3,
                           percent,
                           Colors.castbar.color, Colors.hp_bar_back.color);
            } else if (fighter.fighterStateIs(FighterState.CHANNELLING)) {
                float percent = 1 - fighter.getCast().getPercent();
                shape.barH(skillBarX,
                           skillBarY,
                           skillBarW, 3,
                           percent,
                           Colors.channelbar.color, Colors.hp_bar_back.color);

                int max = fighter.getCast().getChannelExecutionMaxCount();
                for (int i = 0; i < max; ++i) {
                    ShapeFactory.channellingDivider(shape, (i * (skillBarW / max)), skillBarY);
                }
            }
        } else {
            shape.barH(skillBarX, skillBarY, skillBarW, 3, fighter.getStatSheet().getAtb().getPercent(), Colors.ui_bar_front.color, Colors.hp_bar_back.color);
        }
    }
}
