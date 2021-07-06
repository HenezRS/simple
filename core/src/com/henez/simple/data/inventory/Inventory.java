package com.henez.simple.data.inventory;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.items.Item;
import com.henez.simple.items.ItemName;
import lombok.Getter;

@Getter
public class Inventory {
    private final static int INV_SLOTS = 24;
    private GameList<InventorySlot> slots;

    public Inventory() {
        slots = new GameList<>();
        for(int i=0; i<INV_SLOTS; ++i) {
            slots.add(new InventorySlot(i));
        }

        addItem(new Item(ItemName.sword));
        addItem(new Item(ItemName.berry));
    }

    public void update() {
        slots.forEach(InventorySlot::update);
    }

    public void addItem(Item item) {
        slots.stream().filter(InventorySlot::isEmpty).findFirst().ifPresent(slot -> slot.setItem(item));
    }
}
