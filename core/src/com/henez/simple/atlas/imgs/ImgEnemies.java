package com.henez.simple.atlas.imgs;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public enum ImgEnemies {
    octo_idle_0(0, 0),
    octo_idle_1(1, 0),
    octo_attack_0(2, 0),
    octo_attack_1(3, 0),

    octo2_idle_0(0, 1),
    octo2_idle_1(1, 1),
    octo2_attack_0(2, 1),
    octo2_attack_1(3, 1),

    octo3_idle_0(0, 2),
    octo3_idle_1(1, 2),
    octo3_attack_0(2, 2),
    octo3_attack_1(3, 2),

    octo4_idle_0(0, 3),
    octo4_idle_1(1, 3),
    octo4_attack_0(2, 3),
    octo4_attack_1(3, 3),

    octoMinor_idle_0(0, 4),
    octoMinor_idle_1(1, 4),
    octoMinor_attack_0(2, 4),
    octoMinor_attack_1(3, 4),
    ;

    private final int x;
    private final int y;

    ImgEnemies(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionEnhanced asTex() {
        return Atlas.toTex(this);
    }
}
