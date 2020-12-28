package com.henez.simple.shaders;

import com.henez.simple.renderer.Batcher;

public final class ShaderFactory {
    private ShaderFactory() {
    }

    public static void applyShaderBW(Batcher batch) {
        batch.setShader(Shader.BW.shader);
    }

    public static void resetShader(Batcher batch) {
        batch.setShader(null);
    }

    /*public void setShader(ShaderClass shaderClass) {
        batch.setShader(shaderClass.shader);
    }

    public void setShaderSpell(Colors colorPrimary, Colors colorSecondary) {

        ShaderProgram shader = ShaderClass.SPELL.shader;
        shader.begin();
        shader.setUniform3fv("colorPrimary", colorPrimary.getAsFloats3(),0,3);
        shader.setUniform3fv("colorSecondary", colorSecondary.getAsFloats3(),0,3);
        shader.setUniform3fv("colorBlack", Colors.ui_blue_darkest.getAsFloats3(),0,3);
        shader.end();
        batch.setShader(shader);
    }
    */
}
