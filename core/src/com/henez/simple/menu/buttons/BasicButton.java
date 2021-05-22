package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.renderer.Batcher;

public class BasicButton extends Button {
    private TextureRegion tex;
    private TextureRegion texHover;

    public BasicButton(int x, int y, TextureRegion tex, TextureRegion texHover) {
        super("", x, y);
        this.tex = tex;
        this.texHover = texHover;
        this.w = tex.getRegionWidth();
        this.h = tex.getRegionHeight();
    }

    @Override
    public void draw(Batcher batch) {
        drawTo(batch,x,y);
    }

    public void drawTo(Batcher batch, int x, int y) {
        if (hover) {
            batch.drawToCamera(texHover, x, y);
        } else {
            batch.drawToCamera(tex, x, y);
        }
    }
}
