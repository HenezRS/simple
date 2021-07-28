package com.henez.simple.skills.tactics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIconTactics;
import lombok.Getter;

@Getter
public enum TacticIfName {
    always("always", ImgIconTactics.if_always, TacticIfRegardingName.none),
    canHitAtleast("can hit at least", ImgIconTactics.if_can_hit_atleast, TacticIfRegardingName.none),
    hpPercent("hp percent", ImgIconTactics.if_hp, TacticIfRegardingName.they),
    mpPercent("mp percent", ImgIconTactics.if_mp, TacticIfRegardingName.they),
    hasBuff("has buff", ImgIconTactics.if_buff, TacticIfRegardingName.they),
    hasStacks("has stacks", ImgIconTactics.if_stacks, TacticIfRegardingName.they);

    private String text;
    private TextureRegion tex;
    private TacticIfRegardingName regardingDefault;
    private boolean chooseRegarding;

    TacticIfName(String text, ImgIconTactics img, TacticIfRegardingName regardingDefault) {
        this.text = text;
        this.tex = Atlas.toTex(img);
        this.regardingDefault = regardingDefault;

        chooseRegarding = regardingDefault != TacticIfRegardingName.none;
    }
}
