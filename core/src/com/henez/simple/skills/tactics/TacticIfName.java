package com.henez.simple.skills.tactics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIconTactics;
import lombok.Getter;

@Getter
public enum TacticIfName {
    always("always", ImgIconTactics.if_always, TacticIfRegardingName.none, TacticIsName.always),
    canHitAtleast("can hit at least", ImgIconTactics.if_can_hit_atleast, TacticIfRegardingName.none, TacticIsName.selectValues),
    hpPercent("hp percent", ImgIconTactics.if_hp, TacticIfRegardingName.they, TacticIsName.selectValuesPercent),
    mpPercent("mp percent", ImgIconTactics.if_mp, TacticIfRegardingName.they, TacticIsName.selectValuesPercent),
    hasBuff("has buff", ImgIconTactics.if_buff, TacticIfRegardingName.they, TacticIsName.selectBuffs),
    hasStacks("has stacks", ImgIconTactics.if_stacks, TacticIfRegardingName.they, TacticIsName.selectBuffsValues);

    private String text;
    private TextureRegion tex;
    private TacticIfRegardingName regardingDefault;
    private TacticIsName tacticIsName;
    private boolean chooseRegarding;

    TacticIfName(String text, ImgIconTactics img, TacticIfRegardingName regardingDefault, TacticIsName tacticIsName) {
        this.text = text;
        this.tex = Atlas.toTex(img);
        this.regardingDefault = regardingDefault;
        this.tacticIsName = tacticIsName;
        chooseRegarding = regardingDefault != TacticIfRegardingName.none;
    }
}
