package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.renderer.Batcher;

public class ImageButton extends Button {
    private int texAddX;
    private int texAddY;
    private TextureRegion tex;
    private ImageButtonDefinitions group;

    public ImageButton(String name, int x, int y, TextureRegion tex, ImageButtonDefinitions group) {
        super(name, x, y);
        this.tex = tex;
        this.group = group;
        this.w = group.getBack().getRegionWidth();
        this.h = group.getBack().getRegionHeight();
        this.texAddX = (this.w - tex.getRegionWidth()) / 2;
        this.texAddY = (this.h - tex.getRegionHeight()) / 2;
    }

    @Override
    void draw(Batcher batch) {
        batch.draw(group.getBack(), x, y);
        if (hover) {
            batch.draw(group.getHover(), x, y);
        }
        batch.draw(tex, x + texAddX, y + texAddY);
        if (clicked) {
            batch.draw(group.getClicked(), x, y);
        }
    }
}
