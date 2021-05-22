package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgIcon14 {
    berry, sword
    ;

    private final int x;
    private final int y;

    ImgIcon14() {
        this.x = this.ordinal() % 36; //todo: revise
        this.y = this.ordinal() / 36;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
