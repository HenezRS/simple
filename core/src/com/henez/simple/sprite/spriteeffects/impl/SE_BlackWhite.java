package com.henez.simple.sprite.spriteeffects.impl;

import com.henez.simple.sprite.spriteeffects.SpriteEffect;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SE_BlackWhite extends SpriteEffect {
    public SE_BlackWhite(int duration) {
        super();
        timer.reset(duration);
    }

    @Override
    public void update() {
        if (timer.update()) {
            finish();
        }
    }
}
