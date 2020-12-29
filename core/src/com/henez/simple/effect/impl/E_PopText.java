package com.henez.simple.effect.impl;

import com.badlogic.gdx.graphics.Color;
import com.henez.simple.Static;
import com.henez.simple.effect.Effect;
import com.henez.simple.enums.Colors;
import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.text.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class E_PopText extends Effect {
    private Color color = Colors.text_default.color;
    private String text;

    public E_PopText() {

    }

    @Override
    public void update() {
        if (firstUpdate) {
            timer = new Timer((int) (Global.SEC * 0.75));
        } else {
            if (timer.update()) {
                finish();
            }
            y -= 0.25;
        }
        firstUpdate = false;
    }

    @Override
    public void draw(Batcher batcher) {
        Static.text.draw(batcher, text, x, y, timer.getPercentRemaining(), color, Text.TextStyle.shadow);
    }
}
