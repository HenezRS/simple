package com.henez.simple.skills.tactics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIconTactics;
import lombok.Getter;

@Getter
public enum TacticIfName {
    always("always", ImgIconTactics.if_always),
    hpPercent("hp percent", ImgIconTactics.if_hp),
    mpPercent("mp percent", ImgIconTactics.if_mp),
    hasBuff("has buff", ImgIconTactics.if_buff),
    hasStacks("has stacks", ImgIconTactics.if_stacks);

    private String text;
    private TextureRegion tex;

    TacticIfName(String text, ImgIconTactics img) {
        this.text = text;
        this.tex = Atlas.toTex(img);
    }
}
