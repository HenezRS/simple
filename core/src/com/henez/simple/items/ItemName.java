package com.henez.simple.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.items.definition.ItemNameDefinition;
import com.henez.simple.items.definition.ItemNameDefinitionFactory;

public enum ItemName {
    berry(ItemNameDefinitionFactory.createBerry()),
    sword(ItemNameDefinitionFactory.createSword());

    private ItemNameDefinition definition;

    ItemName(ItemNameDefinition definition) {
        this.definition = definition;
    }

    public String getName() {
        return definition.getName();
    }

    public TextureRegion getTex() {
        return definition.getTex();
    }

    public int getCost() {
        return definition.getCost();
    }
}
