package com.henez.simple.sprite.animation;

import com.henez.simple.atlas.imgset.ImgSetFighters;

import static com.henez.simple.global.Global.SEC2;

public final class AnimationDynamicFactory {
    private AnimationDynamicFactory() {
    }

    public static AnimationDynamic toActorIdle(ImgSetFighters imgSetFighters) {
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getIdle(), imgSetFighters.getIdle2());
    }

    public static AnimationDynamic toActorMove(ImgSetFighters imgSetFighters) {
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getIdle(), imgSetFighters.getIdle2()).withSpeed(3.0f);
    }

    public static AnimationDynamic toActorAttack(ImgSetFighters imgSetFighters) {
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getAttack(), imgSetFighters.getAttack2(), imgSetFighters.getAttack2());
    }

    public static AnimationDynamic toActorAttackFast(ImgSetFighters imgSetFighters) {
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getAttack(), imgSetFighters.getAttack2(), imgSetFighters.getAttack2()).withSpeed(16.0f);
    }
}
