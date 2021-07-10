package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.Static;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;

public class TacticOptionButton extends Button {
    private int texAddX;
    private int texAddY;
    private int textX;
    private int textY;
    private TextureRegion tex;
    private ImageButtonDefinitions group;

    public TacticOptionButton(String name, int x, int y, TextureRegion tex) {
        super(name, x, y);
        this.tex = tex;
        this.group = ImageButtonDefinitions.tactic_option;
        this.w = group.getBack().getRegionWidth();
        this.h = group.getBack().getRegionHeight();
        this.texAddX = 1;
        this.texAddY = (this.h - tex.getRegionHeight()) / 2;
        this.textX = 20;
        this.textY = 6;
    }

    @Override
    public void draw(Batcher batch) {
        drawTo(batch, x, y);
    }

    public void drawTo(Batcher batch, int x, int y) {
        Color c = Colors.text_subtle.color;
        batch.drawToCamera(group.getBack(), x, y);
        if (hover) {
            batch.drawToCamera(group.getHover(), x, y);
        }

        if (isActive) {
            batch.drawToCamera(group.getClicked(), x, y);
            c = Colors.text_default.color;
        }

        batch.drawToCamera(tex, x + texAddX, y + texAddY);
        Static.text.drawToCamera(batch, name, x + textX, y + textY, c);
    }
}
