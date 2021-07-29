package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.renderer.Batcher;

public class TacticWideButton extends Button {
    private int texAddX;
    private int texAddY;
    private TextureRegion tex;
    private TextureRegion tex2;
    private ImageButtonDefinitions group;

    public TacticWideButton(String name, int x, int y, TextureRegion tex) {
        super(name, x, y);
        this.tex = tex;
        this.group = ImageButtonDefinitions.wide_slot;
        this.w = group.getBack().getRegionWidth();
        this.h = group.getBack().getRegionHeight();
        this.texAddX = (this.w - tex.getRegionWidth()) / 2;
        this.texAddY = (this.h - tex.getRegionHeight()) / 2;
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
        batch.drawToCamera(tex, x + texAddX, y + texAddY);
        if (tex2 != null) {
            batch.drawToCamera(tex2, x + texAddX, y + texAddY);
        }
        if (clicked && group.getClicked() != null) {
            batch.drawToCamera(group.getClicked(), x, y);
        }
    }

    public void setTex(TextureRegion tex) {
        this.tex = tex;
    }

    public void setTex2(TextureRegion tex2) {
        this.tex2 = tex2;
    }
}
