package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgBackground {
    menu_gear("menu_equip"),
    menu_skills("menu_skills"),
    menu_tree("menu_tree"),

    ;

    private final String pathName;

    ImgBackground(String pathName) {
        this.pathName = pathName;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
