package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgIconTactics {
    on_self, on_enemy_any, on_player_any, on_enemy_focus, on_player_focus,
    if_hp, is, if_mp, if_buff, if_stacks, if_always;

    private final int x;
    private final int y;

    ImgIconTactics() {
        this.x = this.ordinal() % 36; //todo: revise
        this.y = this.ordinal() / 36;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
