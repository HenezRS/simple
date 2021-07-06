package com.henez.simple.utils;

import com.henez.simple.skills.skillInventory.SkillInventorySlot;

public final class SkillInventoryUtils {
    public final static int INV_X = 41;
    public final static int INV_Y = 69;
    private final static int slotWW = 17;

    public static int getSlotX(SkillInventorySlot slot) {
        return INV_X + (slotWW * (slot.getPos() % 5));
    }

    public static int getSlotY(SkillInventorySlot slot) {
        return INV_Y + (slotWW * (slot.getPos() / 5));
    }
}
