package com.henez.simple.drawer.playerdata;

import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgBackground;
import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.data.inventory.Inventory;
import com.henez.simple.data.inventory.InventorySlot;
import com.henez.simple.items.Item;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.utils.InventoryUtils;
import com.henez.simple.world.World;

public class InventoryDrawer {
    public void drawBatch(Inventory inventory, Batcher batch) {
        batch.drawToCamera(ImgUi.bag.asTex(), InventoryUtils.INV_X,InventoryUtils.INV_Y);
        inventory.getSlots().forEach(slot -> {
            if(!slot.isEmpty()) {
                slot.getButton().draw(batch);
            }
        });
    }

    public void drawShape(Inventory inventory, Batcher batch) {

    }
}
