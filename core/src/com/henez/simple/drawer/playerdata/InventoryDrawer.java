package com.henez.simple.drawer.playerdata;

import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.data.inventory.Inventory;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.utils.InventoryUtils;

public class InventoryDrawer {
    public void drawBatch(Inventory inventory, Batcher batch) {
        batch.drawToCamera(ImgUi.bag.asTex(), InventoryUtils.INV_X, InventoryUtils.INV_Y);
        inventory.getSlots().forEach(slot -> {
            slot.getButton().draw(batch);
        });
    }

    public void drawShape(Inventory inventory, Batcher batch) {

    }
}
