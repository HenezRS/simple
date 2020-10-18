package com.henez.simple.misc;

import lombok.Getter;

@Getter
public class XY {
    private int x;
    private int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
