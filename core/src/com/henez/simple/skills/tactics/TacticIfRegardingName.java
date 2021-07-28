package com.henez.simple.skills.tactics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIconTactics;
import lombok.Getter;

@Getter
public enum TacticIfRegardingName {
    none(null), self(ImgIconTactics.if_self), they(ImgIconTactics.if_they);

    private TextureRegion tex;

    TacticIfRegardingName(ImgIconTactics img) {
        this.tex = img == null ? null : Atlas.toTex(img);
    }
}
