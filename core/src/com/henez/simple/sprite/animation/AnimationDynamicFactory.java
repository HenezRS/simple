package com.henez.simple.sprite.animation;

import com.henez.simple.atlas.imgs.ImgTiles;
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

    public static AnimationDynamic toActorHit(ImgSetFighters imgSetFighters) {
        if (imgSetFighters.getHit() == null) {
            return null;
        }
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getHit(), imgSetFighters.getHit2(), imgSetFighters.getHit(), imgSetFighters.getHit2());
    }

    public static AnimationDynamic toActorChannel(ImgSetFighters imgSetFighters) {
        if (imgSetFighters.getChannel() == null) {
            return null;
        }
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getChannel(), imgSetFighters.getChannel());
    }

    public static AnimationDynamic toActorCast(ImgSetFighters imgSetFighters) {
        if (imgSetFighters.getCast() == null) {
            return null;
        }
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getCast());
    }

    public static AnimationDynamic toActorDead(ImgSetFighters imgSetFighters) {
        if (imgSetFighters.getDead() == null) {
            return new AnimationDynamic(SEC2, 1, ImgTiles.grave.asTex());
        }
        return new AnimationDynamic(SEC2, 1, imgSetFighters.getDead());
    }
}
