package com.henez.simple;

import com.badlogic.gdx.ApplicationAdapter;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.shaders.ShaderLoader;

public class Game extends ApplicationAdapter {

    private Simple simple;

    @Override
    public void create() {
        Static.load();
        Atlas.load();
        ShaderLoader.load();

        simple = new Simple();
    }

    @Override
    public void render() {
        simple.input();
        simple.update();
        simple.draw();
        simple.post();
    }

    @Override
    public void dispose() {
    }
}
