package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgIcon7 {
    hp,
    mp,
    str,
    def,
    dex,
    mag,
    res,
    spi,
    aspd,
    cspd,
    greenheart,
    greenstr,
    question,
    fire,
    cold,
    bolt,
    dark,
    poison,
    silence,
    stone,
    sleep,
    wepbreak,
    charm,
    coin,
    stairs,
    dot,
    arrowdown,
    attack,
    mattack,
    misc,
    rank,
    holy,
    sword,
    fangs,
    claw,
    ranged,
    staff,
    soul,
    heavy,
    light,
    cloth,
    ring,
    sword2,
    aspd2,
    crit,
    arm,
    marm,
    cspd2,
    blueheart,
    cancel,
    crown,
    rankdown,
    ;

    private final int x;
    private final int y;

    ImgIcon7() {
        this.x = this.ordinal() % 36;
        this.y = this.ordinal() / 36;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
