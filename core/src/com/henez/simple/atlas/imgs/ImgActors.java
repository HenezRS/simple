package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgActors {
    knight_idle_0(0, 0),
    knight_idle_1(1, 0),
    knight_attack_0(2, 0),
    knight_attack_1(3, 0),

    knight2_idle_0(0, 1),
    knight2_idle_1(1, 1),
    knight2_attack_0(2, 1),
    knight2_attack_1(3, 1),

    knight3_idle_0(0, 2),
    knight3_idle_1(1, 2),
    knight3_attack_0(2, 2),
    knight3_attack_1(3, 2),

    knight4_idle_0(0, 3),
    knight4_idle_1(1, 3),
    knight4_attack_0(2, 3),
    knight4_attack_1(3, 3),
    ;

    private final int x;
    private final int y;

    ImgActors(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
