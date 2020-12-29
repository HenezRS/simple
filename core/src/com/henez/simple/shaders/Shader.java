package com.henez.simple.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public enum Shader {
    BW("Shader_BW.glsl"),
    blink("Shader_blink.glsl"),
    sprite("Shader_sprite.glsl"),
    ;

    public String path;
    public ShaderProgram shader;
    public String vert;
    public String frag;

    Shader(String path) {
        this.path = path;
    }
}
