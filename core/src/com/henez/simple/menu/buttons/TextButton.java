package com.henez.simple.menu.buttons;

import com.henez.simple.Static;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.renderer.Batcher;

public class TextButton extends Button {

    public TextButton(String name, int x, int y) {
        super(name, x, y);
        Rect rect = Static.text.getTextRect(name);
        this.w = rect.w;
        this.h = rect.h;
    }

    @Override
    public void draw(Batcher batch) {
        if (clicked) {
            Static.text.draw(batch, name, x, y, Colors.text_click.color);
        } else if (hover) {
            Static.text.draw(batch, name, x, y, Colors.text_hover.color);
        } else {
            Static.text.draw(batch, name, x, y);
        }
    }
}
