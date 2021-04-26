package com.henez.simple.atlas.imgset;

import com.henez.simple.atlas.imgs.ImgActors;
import com.henez.simple.atlas.imgs.ImgEnemies;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public enum ImgSetFighters {
    class_kni(ImgActors.kni_idle_0, ImgActors.kni_idle_1, ImgActors.kni_attack_0, ImgActors.kni_attack_1),
    class_ran(ImgActors.ran_idle_0, ImgActors.ran_idle_1, ImgActors.ran_attack_0, ImgActors.ran_attack_1),
    class_mag(ImgActors.mag_idle_0, ImgActors.mag_idle_1, ImgActors.mag_attack_0, ImgActors.mag_attack_1),
    class_cle(ImgActors.cle_idle_0, ImgActors.cle_idle_1, ImgActors.cle_attack_0, ImgActors.cle_attack_1),
    class_rog(ImgActors.rog_idle_0, ImgActors.rog_idle_1, ImgActors.rog_attack_0, ImgActors.rog_attack_1),
    class_tri(ImgActors.tri_idle_0, ImgActors.tri_idle_1, ImgActors.tri_attack_0, ImgActors.tri_attack_1),
    class_bru(ImgActors.bru_idle_0, ImgActors.bru_idle_1, ImgActors.bru_attack_0, ImgActors.bru_attack_1),
    class_foo(ImgActors.foo_idle_0, ImgActors.foo_idle_1, ImgActors.foo_attack_0, ImgActors.foo_attack_1),
    class_dru(ImgActors.dru_idle_0, ImgActors.dru_idle_1, ImgActors.dru_attack_0, ImgActors.dru_attack_1),
    class_cul(ImgActors.cul_idle_0, ImgActors.cul_idle_1, ImgActors.cul_attack_0, ImgActors.cul_attack_1),

    enemy_octo(ImgEnemies.octo_idle_0, ImgEnemies.octo_idle_1, ImgEnemies.octo_attack_0, ImgEnemies.octo_attack_1),
    enemy_octo2(ImgEnemies.octo2_idle_0, ImgEnemies.octo2_idle_1, ImgEnemies.octo2_attack_0, ImgEnemies.octo2_attack_1),
    enemy_octo3(ImgEnemies.octo3_idle_0, ImgEnemies.octo3_idle_1, ImgEnemies.octo3_attack_0, ImgEnemies.octo3_attack_1),
    enemy_octo4(ImgEnemies.octo4_idle_0, ImgEnemies.octo4_idle_1, ImgEnemies.octo4_attack_0, ImgEnemies.octo4_attack_1),
    ;

    TextureRegionEnhanced idle;
    TextureRegionEnhanced idle2;
    TextureRegionEnhanced attack;
    TextureRegionEnhanced attack2;

    ImgSetFighters(ImgActors... imgActors) {
        this.idle = imgActors[0].asTex();
        this.idle2 = imgActors[1].asTex();
        this.attack = imgActors[2].asTex();
        this.attack2 = imgActors[3].asTex();
    }

    ImgSetFighters(ImgEnemies... imgActors) {
        this.idle = imgActors[0].asTex();
        this.idle2 = imgActors[1].asTex();
        this.attack = imgActors[2].asTex();
        this.attack2 = imgActors[3].asTex();
    }
}
