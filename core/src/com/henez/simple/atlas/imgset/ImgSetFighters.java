package com.henez.simple.atlas.imgset;

import com.henez.simple.atlas.imgs.ImgActors;
import com.henez.simple.atlas.imgs.ImgEnemies;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import lombok.Getter;

@Getter
public enum ImgSetFighters {
    class_kni(ImgActors.kni_idle_0.asTex(), ImgActors.kni_idle_1.asTex(), ImgActors.kni_attack_0.asTex(), ImgActors.kni_attack_1.asTex(), ImgActors.kni_hit.asTex(),
              ImgActors.kni_hit.asTexWith(-1, 0), ImgActors.kni_channel_0.asTex(), ImgActors.kni_channel_1.asTex(), ImgActors.kni_cast.asTex(), ImgActors.kni_dead.asTex(), ImgActors.kni_low.asTex()),
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
    enemy_octoMinor(ImgEnemies.octoMinor_idle_0, ImgEnemies.octoMinor_idle_1, ImgEnemies.octoMinor_attack_0, ImgEnemies.octoMinor_attack_1),
    ;

    TextureRegionEnhanced idle;
    TextureRegionEnhanced idle2;
    TextureRegionEnhanced attack;
    TextureRegionEnhanced attack2;
    TextureRegionEnhanced hit;
    TextureRegionEnhanced hit2;
    TextureRegionEnhanced channel;
    TextureRegionEnhanced channel2;
    TextureRegionEnhanced cast;
    TextureRegionEnhanced dead;
    TextureRegionEnhanced low;

    ImgSetFighters(TextureRegionEnhanced idle, TextureRegionEnhanced idle2, TextureRegionEnhanced attack, TextureRegionEnhanced attack2) {
        this.idle = idle;
        this.idle2 = idle2;
        this.attack = attack;
        this.attack2 = attack2;
    }

    ImgSetFighters(ImgActors idle, ImgActors idle2, ImgActors attack, ImgActors attack2) {
        this.idle = idle.asTex();
        this.idle2 = idle2.asTex();
        this.attack = attack.asTex();
        this.attack2 = attack2.asTex();
    }

    ImgSetFighters(ImgEnemies idle, ImgEnemies idle2, ImgEnemies attack, ImgEnemies attack2) {
        this.idle = idle.asTex();
        this.idle2 = idle2.asTex();
        this.attack = attack.asTex();
        this.attack2 = attack2.asTex();
    }

    ImgSetFighters(TextureRegionEnhanced idle, TextureRegionEnhanced idle2, TextureRegionEnhanced attack, TextureRegionEnhanced attack2, TextureRegionEnhanced hit,
            TextureRegionEnhanced hit2, TextureRegionEnhanced channel, TextureRegionEnhanced channel2, TextureRegionEnhanced cast, TextureRegionEnhanced dead, TextureRegionEnhanced low) {
        this.idle = idle;
        this.idle2 = idle2;
        this.attack = attack;
        this.attack2 = attack2;
        this.hit = hit;
        this.hit2 = hit2;
        this.channel = channel;
        this.channel2 = channel2;
        this.cast = cast;
        this.dead = dead;
        this.low = low;
    }
}
