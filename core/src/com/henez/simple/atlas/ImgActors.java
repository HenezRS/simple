package com.henez.simple.atlas;

import lombok.Getter;

@Getter
public enum ImgActors {
    knight_idle_0(0,0),
    knight_idle_1(1,0),
    knight_attack_0(2,0),
    knight_attack_1(3,0),
    ;

    private final int x;
    private final int y;

    ImgActors(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
