package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.tactics.TacticIfName;

public class TacticOptionButton extends Button {
    private int texAddX;
    private int texAddY;
    private TacticIfName tacticIfName;
    private TextureRegion tex;
    private ImageButtonDefinitions group;

    public TacticOptionButton(int x, int y, TacticIfName tacticIfName) {
        super(tacticIfName.getText(), x, y);
        this.tex = tacticIfName.getTex();
        this.tacticIfName = tacticIfName;
        this.group = ImageButtonDefinitions.tactic_option;
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
        if (isActive) {
            batch.drawToCamera(group.getClicked(), x, y);
        }
    }
}
