package com.henez.simple.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.henez.simple.renderer.Batcher;

public final class ShaderFactory {
    private ShaderFactory() {
    }

    public static void resetShader(Batcher batch) {
        batch.setShader(null);
    }

    public static void applyShaderBW(Batcher batch) {
        batch.setShader(Shader.BW.shader);
    }

    public static void applyShaderBlink(Batcher batch, float alpha) {
        ShaderProgram shader = Shader.blink.shader;
        shader.begin();
        shader.setUniformf("alpha", alpha);
        shader.end();
        batch.setShader(shader);
    }
}
