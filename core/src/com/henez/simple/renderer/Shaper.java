package com.henez.simple.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.henez.simple.datastructures.Numbers;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.global.Global;
import com.henez.simple.utils.RectUtils;

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

    public void rect(Rect rect) {
        rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
    }

    public void rect(Rect rect, Color color) {
        setColor(color);
        rect(rect);
    }

    public void rect(int x, int y, int w, int h, Color color) {
        setColor(color);
        rect(x, y, w, h);
    }

    public void rectGrid(int gx, int gy, Color color) {
        rectOutline(new Rect(gx * Global.tilePixelSize, gy * Global.tilePixelSize, Global.tilePixelSize, Global.tilePixelSize), color);
    }

    public void rectOutline(Rect rect) {
        rect(RectUtils.borderRight(rect));
        rect(RectUtils.borderUp(rect));
        rect(RectUtils.borderLeft(rect));
        rect(RectUtils.borderDown(rect));
    }

    public void rectOutlineFull(Rect rect) {
        rect(RectUtils.borderRightFull(rect));
        rect(RectUtils.borderUpFull(rect));
        rect(RectUtils.borderLeftFull(rect));
        rect(RectUtils.borderDownFull(rect));
    }

    public void rectOutline(Rect rect, Color color) {
        setColor(color);
        rectOutline(rect);
    }

    public void rectOutline(Rect rect, Color color, int tx, int ty) {
        setColor(color);
        rectOutline(rect);
    }

    public void rectOutlineFull(Rect rect, Color color) {
        setColor(color);
        rectOutlineFull(rect);
    }

    public void bar(Rectangle rect, float percent, Color color, Color colorUnder) {
        setColor(colorUnder);
        rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
        setColor(Colors.ui_back.color);
        rect.width = Numbers.floor(rect.width * percent);
        float w = rect.width;
        Numbers.clamp((int) rect.width++, 0, (int) w);
        if (rect.width > 1) {
            rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
        }
        setColor(color);
        Numbers.clamp((int) rect.width--, 0, (int) w);
        rect(rect.x + tx, rect.y + ty, rect.width, rect.height);
    }

    public void barH1(int x, int y, int w, float percent, Color color, Color colorUnder) {
        bar(new Rect(x, y, w, 1), percent, color, colorUnder);
    }

    public void bar2(Rectangle rect, float percent, Color color, Color color2, Color colorUnder) {
        float w = rect.width;
        bar(rect, percent, color, colorUnder);
        rect.width = w;
        rect.y++;
        bar(rect, percent, color2, colorUnder);
    }
}
