package com.henez.simple.sprite.spriteeffects;

import com.badlogic.gdx.graphics.Color;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.global.Global;
import com.henez.simple.sprite.spriteeffects.impl.SE_BlackWhite;
import com.henez.simple.sprite.spriteeffects.impl.SE_Blink;

import java.util.stream.Collectors;

public class SpriteEffectManager {
    private GameList<SpriteEffect> spriteEffects;

    public SpriteEffectManager() {
        spriteEffects = new GameList<>();
    }

    public void update() {
        spriteEffects.forEach(SpriteEffect::update);
    }

    public void applyShaderUniforms() {
        spriteEffects.forEach(SpriteEffect::applyShaderUniforms);
    }

    private void removeFinished() {
        spriteEffects = spriteEffects.stream().filter(e -> !e.isDone()).collect(Collectors.toCollection(GameList::new));
    }

    public void createBlackWhite() {
        SE_BlackWhite se = new SE_BlackWhite(Global.SEC);
        spriteEffects.add(se);
    }

    public void createBlink(Color color) {
        SE_Blink se = new SE_Blink(Global.SEC3);
        se.setColor(color);
        spriteEffects.add(se);
    }
}
