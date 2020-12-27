package com.henez.simple.effect;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.renderer.Batcher;

import java.util.stream.Collectors;

public class EffectsManager {
    GameList<Effect> effects;

    public EffectsManager() {
        effects = new GameList<>();
    }

    public void update() {
        effects.forEach(Effect::update);
    }

    public void draw(Batcher batch) {
        effects.forEach(e -> e.draw(batch));
        removeFinished();
    }

    private void removeFinished() {
        effects = effects.stream().filter(e -> !e.isDone()).collect(Collectors.toCollection(GameList::new));
    }

    public void add(Effect effect) {
        effects.add(effect);
    }
}
