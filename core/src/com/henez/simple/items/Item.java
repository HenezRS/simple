package com.henez.simple.items;

import lombok.Getter;

@Getter
public class Item {
    private ItemName itemName;

    public Item(ItemName itemName) {
        this.itemName = itemName;
    }
}
