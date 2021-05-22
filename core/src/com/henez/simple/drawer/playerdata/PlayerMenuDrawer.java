package com.henez.simple.drawer.playerdata;

import com.henez.simple.atlas.imgs.ImgBackground;
import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.data.PlayerMenu;
import com.henez.simple.data.inventory.Inventory;
import com.henez.simple.enums.state.PlayerMenuState;
import com.henez.simple.menu.buttons.TabButton;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.utils.InventoryUtils;

public class PlayerMenuDrawer {
    public void drawBatch(PlayerMenu menu, Batcher batch) {
        if(menu.isShowPlayerMenu()) {
            if(menu.getPlayerMenuState() == PlayerMenuState.gear) {
                batch.drawToCamera(ImgBackground.menu_gear.asTex(), 0,0);
            } else if(menu.getPlayerMenuState() == PlayerMenuState.skills) {
                batch.drawToCamera(ImgBackground.menu_skills.asTex(), 0,0);
            } else if(menu.getPlayerMenuState() == PlayerMenuState.tree) {
                batch.drawToCamera(ImgBackground.menu_tree.asTex(), 0,0);
            }

            menu.getTabs().getButtons().stream().filter(b -> !((TabButton)b).isActive() && !((TabButton)b).isHover()).forEach(b -> ((TabButton)b).draw(batch));
            if(menu.getTabHover()!=null) {
                menu.getTabHover().draw(batch);
            }
            menu.getTabSelected().draw(batch);

            menu.getExit().draw(batch);
        }
    }

    public void drawShape(PlayerMenu menu, Batcher batch) {

    }
}
