package com.henez.simple.data.inventory;

import com.henez.simple.items.Item;
import com.henez.simple.menu.buttons.ItemButton;
import com.henez.simple.utils.InventoryUtils;
import lombok.Getter;

import java.util.Optional;

@Getter
public class InventorySlot {
    private int pos;
    private ItemButton button;

    public InventorySlot(int pos) {
        this.pos = pos;
        button = new ItemButton(InventoryUtils.getSlotX(this), InventoryUtils.getSlotY(this), null);
    }

    public void update() {
        button.update();
    }

    public Optional<Item> getItem() {
        return Optional.ofNullable(button.getItem());
    }

    public void setItem(Item item) {
        button.updateItem(item);
    }

    public void removeItem() {
        button.removeItem();
    }

    public boolean isEmpty() {
        return getItem().isEmpty();
    }
}
