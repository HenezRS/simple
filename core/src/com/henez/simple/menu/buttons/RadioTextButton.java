package com.henez.simple.menu.buttons;

import com.henez.simple.Static;
import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;

public class RadioTextButton extends Button {

    public RadioTextButton(String name, int x, int y) {
        super(name, x, y);
        Rect rect = Static.text.getTextRect(name);
        this.w = rect.w + 14;
        this.h = 10;
    }

    @Override
    public void draw(Batcher batch) {
        if (isActive) {
            batch.drawToCamera(ImgUi.but_radio_on.asTex(), x, y);
            Static.text.drawToCamera(batch, name, x + 14, y + 2);
        } else {
            batch.drawToCamera(ImgUi.but_radio.asTex(), x, y);
            Static.text.drawToCamera(batch, name, x + 14, y + 2, Colors.text_subtle.color);
        }
    }
}
