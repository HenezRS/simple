package com.henez.simple.data.inventory;

import com.henez.simple.Static;
import com.henez.simple.items.Item;
import com.henez.simple.menu.buttons.Button;
import com.henez.simple.menu.buttons.ImageButton;
import com.henez.simple.menu.buttons.ImageButtonDefinitions;
import com.henez.simple.utils.InventoryUtils;
import lombok.Getter;

import java.util.Optional;

@Getter
public class InventorySlot {
    private int pos;
    private Item item;
    private ImageButton button;

    public InventorySlot(int pos) {
        this.pos = pos;
    }

    public void update() {
        Optional.ofNullable(button).ifPresent(Button::update);
    }

    public Optional<Item> getItem() {
        return Optional.ofNullable(item);
    }

    public void setItem(Item item) {
        this.item = item;
        refreshButton();
    }

    public void removeItem() {
        item = null;
        button = null;
    }

    public boolean isEmpty() {
        return getItem().isEmpty();
    }

    private void refreshButton() {
        button = new ImageButton("",InventoryUtils.getSlotX(this),InventoryUtils.getSlotY(this),item.getItemName().getTex(),ImageButtonDefinitions.inv_slot);
    }
}
