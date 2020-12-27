package com.henez.simple.effect.impl;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.effect.Effect;
import com.henez.simple.global.Global;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.renderer.Batcher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class E_PopImg extends Effect {
    private TextureRegion tex;

    public E_PopImg() {

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
        batcher.setAlpha(timer.getPercentRemaining());
        batcher.draw(tex, x, y);
        batcher.resetAlpha();
    }
}
