package com.henez.simple.atlas.imgset;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.ImgActors;
import com.henez.simple.atlas.imgs.ImgEnemies;
import lombok.Getter;

@Getter
public enum ImgSetFighters {
    class_knight(ImgActors.knight_idle_0, ImgActors.knight_idle_1, ImgActors.knight_attack_0, ImgActors.knight_attack_1),
    class_knight2(ImgActors.knight2_idle_0, ImgActors.knight2_idle_1, ImgActors.knight2_attack_0, ImgActors.knight2_attack_1),
    class_knight3(ImgActors.knight3_idle_0, ImgActors.knight3_idle_1, ImgActors.knight3_attack_0, ImgActors.knight3_attack_1),
    class_knight4(ImgActors.knight4_idle_0, ImgActors.knight4_idle_1, ImgActors.knight4_attack_0, ImgActors.knight4_attack_1),

    enemy_octo(ImgEnemies.octo_idle_0, ImgEnemies.octo_idle_1, ImgEnemies.octo_attack_0, ImgEnemies.octo_attack_1),
    enemy_octo2(ImgEnemies.octo2_idle_0, ImgEnemies.octo2_idle_1, ImgEnemies.octo2_attack_0, ImgEnemies.octo2_attack_1),
    enemy_octo3(ImgEnemies.octo3_idle_0, ImgEnemies.octo3_idle_1, ImgEnemies.octo3_attack_0, ImgEnemies.octo3_attack_1),
    enemy_octo4(ImgEnemies.octo4_idle_0, ImgEnemies.octo4_idle_1, ImgEnemies.octo4_attack_0, ImgEnemies.octo4_attack_1),
    ;

    TextureRegion idle;
    TextureRegion idle2;
    TextureRegion attack;
    TextureRegion attack2;

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
