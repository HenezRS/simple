package com.henez.simple.datastructures;

import com.badlogic.gdx.math.Rectangle;
import com.henez.simple.global.Global;

public class Rect extends Rectangle {
    public int xx;
    public int yy;
    public int w;
    public int h;

    public Rect(int x, int y) {
        this.x = x;
        this.y = y;
        width = Global.tilePixelSize;
        height = Global.tilePixelSize;
        snap();
    }

    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        snap();
    }

    public Rect(float x, float y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        snap();
    }

    public void snap() {
        xx = (int) x + (int) width;
        yy = (int) y + (int) height;
        w = (int) width;
        h = (int) height;
    }
}
