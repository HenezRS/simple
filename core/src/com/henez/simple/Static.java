package com.henez.simple;

import com.henez.simple.effect.EffectsManager;
import com.henez.simple.renderer.Renderer;
import com.henez.simple.text.Text;

public class Static {
    public static Renderer renderer;
    public static Text text;
    public static EffectsManager effects;

    private Static() {
    }

    public static void load() {
        renderer = new Renderer();
        text = new Text();
        text.load();
        effects = new EffectsManager();

    }
}
