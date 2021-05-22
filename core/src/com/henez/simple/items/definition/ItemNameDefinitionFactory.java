package com.henez.simple.items.definition;

import com.henez.simple.atlas.imgs.ImgIcon14;

public final class ItemNameDefinitionFactory {
    public static ItemNameDefinition createBerry() {
        return new ItemNameDefinition.Builder("berry", ImgIcon14.berry)
                       .build();
    }

    public static ItemNameDefinition createSword() {
        return new ItemNameDefinition.Builder("sword", ImgIcon14.sword)
                       .withCost(10)
                       .build();
    }
}
