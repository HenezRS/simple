package com.henez.simple.atlas;

import lombok.Getter;

@Getter
public enum ImgEnemies {
    octo_idle_0(0,0),
    octo_idle_1(1,0),
    ;

    private final int x;
    private final int y;

    ImgEnemies(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
