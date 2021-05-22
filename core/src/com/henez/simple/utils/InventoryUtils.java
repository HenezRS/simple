package com.henez.simple.utils;

import com.henez.simple.Static;
import com.henez.simple.data.inventory.InventorySlot;

public final class InventoryUtils {
    public final static int INV_X = 165;
    public final static int INV_Y = 214;
    private final static int slotWW = 17;

    public static int getSlotX(InventorySlot slot) {
        return INV_X+2+(slotWW*(slot.getPos()%8));
    }

    public static int getSlotY(InventorySlot slot) {
        return INV_Y+2+(slotWW*(slot.getPos()/8));
    }
}
