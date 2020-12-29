package com.henez.simple.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.Arrays;

public final class ShaderLoader {
    private ShaderLoader() {
    }

    public static void load() {
        ShaderProgram.pedantic = false;
        Arrays.stream(Shader.values()).forEach(shader -> {
            FileHandle file = Gdx.files.local("shaders/" + shader.path);
            shader.vert = readShader(file)[0];
            shader.frag = readShader(file)[1];
            shader.shader = new ShaderProgram(shader.vert, shader.frag);
            System.out.println(shader.shader.getLog());
        });
    }

    private static String[] readShader(FileHandle file) {
        return file.readString().split("//-!@#frag");
    }
}
