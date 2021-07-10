package com.henez.simple.skills.tactics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIconTactics;
import lombok.Getter;

@Getter
public enum TacticOnName {
    self("self", ImgIconTactics.on_self),
    enemy_any("enemy: any", ImgIconTactics.on_enemy_any),
    player_any("ally: any", ImgIconTactics.on_player_any),
    enemy_focus("enemy: focus", ImgIconTactics.on_enemy_focus),
    player_focus("ally: focus", ImgIconTactics.on_player_focus);

    private String text;
    private TextureRegion tex;

    TacticOnName(String text, ImgIconTactics img) {
        this.text = text;
        this.tex = Atlas.toTex(img);
    }
}
