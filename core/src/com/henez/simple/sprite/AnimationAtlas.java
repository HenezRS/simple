package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;

import static com.henez.simple.atlas.imgs.ImgActors.knight_attack_0;
import static com.henez.simple.atlas.imgs.ImgActors.knight_attack_1;
import static com.henez.simple.atlas.imgs.ImgEffects.*;
import static com.henez.simple.global.Global.SEC16;
import static com.henez.simple.global.Global.SEC4;

@Getter
public enum AnimationAtlas {
    KNIGHT_ATTACK(SEC4, 1, knight_attack_0.asTex(), knight_attack_1.asTex(), knight_attack_1.asTex()),
    SLASH(SEC16 * 16, 3, slash_0.asTex(), slash_1.asTex(), slash_2.asTex(), slash_3.asTex(), slash_4.asTex(), slash_5.asTex()),
    ;

    private float delay;
    private float speed;
    private int keyFrame;
    private TextureRegion[] textureRegions;

    AnimationAtlas(float delay, int keyFrame, TextureRegion... textureRegions) {
        speed = 1.0f;
        this.delay = delay;
        this.keyFrame = keyFrame;
        this.textureRegions = textureRegions;
    }
}
