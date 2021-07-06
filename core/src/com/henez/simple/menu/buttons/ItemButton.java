package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.items.Item;
import com.henez.simple.renderer.Batcher;
import lombok.Getter;

@Getter
public class ItemButton extends Button {
    private int texAddX = 1;
    private int texAddY = 1;
    private Item item;
    private TextureRegion tex;
    private ImageButtonDefinitions group;

    public ItemButton(int x, int y, Item item) {
        super("", x, y);
        updateItem(item);
        this.group = ImageButtonDefinitions.inv_slot;
        this.w = group.getBack().getRegionWidth();
        this.h = group.getBack().getRegionHeight();
    }

    public void updateItem(Item item) {
        this.item = item;
        this.tex = item != null ? item.getItemName().getTex() : null;
    }

    public void removeItem() {
        item = null;
    }

    @Override
    public void draw(Batcher batch) {
        drawTo(batch, x, y);
    }

    public void drawTo(Batcher batch, int x, int y) {
        batch.drawToCamera(group.getBack(), x, y);
        if (hover) {
            batch.drawToCamera(group.getHover(), x, y);
        }

        if (tex != null) {
            batch.drawToCamera(tex, x + texAddX, y + texAddY);
        }

        if (clicked && group.getClicked() != null) {
            batch.drawToCamera(group.getClicked(), x, y);
        }
    }
}
