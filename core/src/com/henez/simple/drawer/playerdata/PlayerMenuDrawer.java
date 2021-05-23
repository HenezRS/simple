package com.henez.simple.drawer.playerdata;

import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgBackground;
import com.henez.simple.data.playermenu.PlayerMenu;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.mapobjects.Fighter;

public class PlayerMenuDrawer {
    int x = 42;
    int y = 77;
    Rect rectPicture = new Rect(x, y + 7, 20, 20);

    public void drawBatch(PlayerMenu menu, Batcher batch) {
        if (menu.isShowPlayerMenu()) {
            if (menu.isShowPlayerMenu()) {
                switch (menu.getPlayerMenuState()) {
                case gear:
                    drawBatchGear(menu, batch);
                    break;
                case skills:
                    drawBatchSkills(menu, batch);
                    break;
                case tree:
                    drawBatchTree(menu, batch);
                    break;
                }
            }

            menu.getTabs().getButtons().stream().filter(b -> !b.isActive() && !b.isHover()).forEach(b -> b.draw(batch));
            if (menu.getTabHover() != null) {
                menu.getTabHover().draw(batch);
            }
            menu.getTabSelected().draw(batch);

            menu.getCards().draw(batch);
            menu.getExit().draw(batch);
        }
    }

    private void drawBatchGear(PlayerMenu menu, Batcher batch) {
        Fighter fighter = menu.getFighterSelected();

        batch.drawToCamera(ImgBackground.menu_gear.asTex(), 0, 0);
        Static.text.drawToCamera(batch, fighter.getName(), x, y);
    }

    private void drawBatchSkills(PlayerMenu menu, Batcher batch) {
        Fighter fighter = menu.getFighterSelected();

        batch.drawToCamera(ImgBackground.menu_skills.asTex(), 0, 0);
    }

    private void drawBatchTree(PlayerMenu menu, Batcher batch) {
        Fighter fighter = menu.getFighterSelected();

        batch.drawToCamera(ImgBackground.menu_tree.asTex(), 0, 0);
    }

    public void drawShape(PlayerMenu menu, Shaper shape) {
        Fighter fighter = menu.getFighterSelected();

        shape.addCameraTransform();
        if (menu.isShowPlayerMenu()) {
            switch (menu.getPlayerMenuState()) {
            case gear:
                drawShapeGear(menu,shape);
                break;
            case skills:
                drawShapeSkills(menu,shape);
                break;
            case tree:
                drawShapeTree(menu,shape);
                break;
            }

            menu.getCards().draw(shape);
        }
        shape.resetTransform();
    }

    private void drawShapeGear(PlayerMenu menu, Shaper shape) {
        shape.rectOutlineCornered(rectPicture, Colors.ui_frame.color);
    }

    private void drawShapeSkills(PlayerMenu menu, Shaper shape) {
    }

    private void drawShapeTree(PlayerMenu menu, Shaper shape) {
    }
}
