package com.henez.simple.sprite.spriteeffects.impl;

import com.badlogic.gdx.graphics.Color;
import com.henez.simple.shaders.Shader;
import com.henez.simple.sprite.spriteeffects.SpriteEffect;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SE_Blink extends SpriteEffect {
    private Color color;

    public SE_Blink(int duration) {
        super();
        timer.reset(duration);
    }

    @Override
    public void update() {
        if (timer.update()) {
            finish();
        }
    }

    @Override
    public void applyShaderUniforms() {
        Shader.sprite.shader.setUniformf("blinkAlpha", timer.getPercentRemaining());
    }
}
