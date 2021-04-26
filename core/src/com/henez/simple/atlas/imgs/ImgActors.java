package com.henez.simple.atlas.imgs;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public enum ImgActors {
    kni_idle_0(0, 0),
    kni_idle_1(1, 0),
    kni_attack_0(2, 0),
    kni_attack_1(3, 0),

    ran_idle_0(0, 1),
    ran_idle_1(1, 1),
    ran_attack_0(2, 1),
    ran_attack_1(3, 1),

    mag_idle_0(0, 2),
    mag_idle_1(1, 2),
    mag_attack_0(2, 2),
    mag_attack_1(3, 2),

    cle_idle_0(0, 3),
    cle_idle_1(1, 3),
    cle_attack_0(2, 3),
    cle_attack_1(3, 3),

    rog_idle_0(0, 4),
    rog_idle_1(1, 4),
    rog_attack_0(2, 4),
    rog_attack_1(3, 4),

    tri_idle_0(0, 5),
    tri_idle_1(1, 5),
    tri_attack_0(2, 5),
    tri_attack_1(3, 5),

    bru_idle_0(0, 6),
    bru_idle_1(1, 6),
    bru_attack_0(2, 6),
    bru_attack_1(3, 6),

    foo_idle_0(0, 7),
    foo_idle_1(1, 7),
    foo_attack_0(2, 7),
    foo_attack_1(3, 7),

    dru_idle_0(0, 8),
    dru_idle_1(1, 8),
    dru_attack_0(2, 8),
    dru_attack_1(3, 8),

    cul_idle_0(0, 9),
    cul_idle_1(1, 9),
    cul_attack_0(2, 9),
    cul_attack_1(3, 9),
    ;

    private final int x;
    private final int y;

    ImgActors(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionEnhanced asTex() {
        return Atlas.toTex(this);
    }
}
