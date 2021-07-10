package com.henez.simple.skills.tactics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgIconTactics;
import lombok.Getter;

@Getter
public enum TacticOnName {
    self(ImgIconTactics.on_self),
    enemy_any(ImgIconTactics.on_enemy_any),
    player_any(ImgIconTactics.on_player_any),
    enemy_focus(ImgIconTactics.on_enemy_focus),
    player_focus(ImgIconTactics.on_player_focus);

    private TextureRegion tex;

    TacticOnName(ImgIconTactics img) {
        this.tex = Atlas.toTex(img);
    }
}
