package com.henez.simple.items.definition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.ImgIcon14;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNameDefinition {
    public static class Builder {
        private String name;
        private TextureRegion tex;
        private int cost = 0;

        public Builder(String name, ImgIcon14 icon) {
            this.name = name;
            this.tex = icon.asTex();
        }

        public Builder withCost(int cost) {
            this.cost = cost;
            return this;
        }

        public ItemNameDefinition build() {
            ItemNameDefinition itemNameDefinition = new ItemNameDefinition();
            itemNameDefinition.setName(name);
            itemNameDefinition.setTex(tex);
            itemNameDefinition.setCost(cost);
            return itemNameDefinition;
        }

    }

    private String name;
    private TextureRegion tex;
    private int cost;

    private ItemNameDefinition() {
    }
}
