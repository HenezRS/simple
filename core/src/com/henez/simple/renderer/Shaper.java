package com.henez.simple.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.henez.simple.datastructures.Numbers;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;

public class Shaper extends ShapeRenderer {
    private float last = 0;
    private int tx;
    private int ty;

    public Shaper() {
        super();
        resetTransform();
    }

    public void addTransform(int x, int y) {
        tx = x;
        ty = y;
    }

    public void resetTransform() {
        tx = 0;
        ty = 0;
    }

    public void rect(Rectangle rect) {
        rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
    }

    public void rect(Rect rect, Color color) {
        setColor(color);
        rect(rect);
    }

    public void bar(Rectangle rect, float percent, Color color, Color colorUnder) {
        setColor(colorUnder);
        rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
        setColor(Colors.black.color);
        rect.width = Numbers.floor(rect.width * percent);
        float w = rect.width;
        Numbers.clamp((int) rect.width++, 0, (int) w);
        if (rect.width > 1) {
            rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
        }
        setColor(color);
        Numbers.clamp((int) rect.width--, 0, (int) w);
        rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
        /*
        if(percent != last) {
            FNM.logger.log(rect.width+" * "+percent+ " = "+(rect.width * percent));
            last = percent;
        }
         */
    }

    public void bar2(Rectangle rect, float percent, Color color, Color color2, Color colorUnder) {
        float w = rect.width;
        bar(rect, percent, color, colorUnder);
        rect.width = w;
        rect.y++;
        bar(rect, percent, color2, colorUnder);
    }
}
