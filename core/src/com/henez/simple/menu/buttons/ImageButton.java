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
    public void draw(Batcher batch) {
        drawTo(batch,x,y);
    }

    public void drawTo(Batcher batch, int x, int y) {
        batch.drawToCamera(group.getBack(), x, y);
        if (hover) {
            batch.drawToCamera(group.getHover(), x, y);
        }
        batch.drawToCamera(tex, x + texAddX, y + texAddY);
        if (clicked && group.getClicked()!=null) {
            batch.drawToCamera(group.getClicked(), x, y);
        }
    }
}
