package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;
import lombok.Getter;

@Getter
public class TabButton extends Button {
    private int textAddX;
    private int textAddY;
    private TextureRegion inactive;
    private TextureRegion inactiveHover;
    private TextureRegion active;
    private TextureRegion activeHover;

    public TabButton(String name, int x, int y) {
        super(name, x, y);
        this.inactive = ImgUi.tab_inactive.asTex();
        this.active = ImgUi.tab_active.asTex();
        this.inactiveHover = ImgUi.tab_inactive_hover.asTex();
        this.activeHover = ImgUi.tab_active_hover.asTex();
        this.w = inactive.getRegionWidth();
        this.h = inactive.getRegionHeight();
        this.textAddX = 24;
        this.textAddY = 5;
    }

    @Override
    public void draw(Batcher batch) {
        drawTo(batch,x,y);
    }

    public void drawTo(Batcher batch, int x, int y) {
        if(isActive) {
            if (hover) {
                batch.drawToCamera(activeHover, x-1, y-1);
            } else {
                batch.drawToCamera(active, x-1, y-1);
            }
            Static.text.drawToCameraCenter(batch, name, x+textAddX, y+textAddY);
        } else {
            if (hover) {
                batch.drawToCamera(inactiveHover, x, y);
            } else {
                batch.drawToCamera(inactive, x, y);
            }
            Static.text.drawToCameraCenter(batch, name, x+textAddX, y+textAddY, Colors.text_faded.color);
        }
    }
}
