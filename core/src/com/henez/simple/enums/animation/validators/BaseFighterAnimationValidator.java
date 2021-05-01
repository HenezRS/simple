package com.henez.simple.enums.animation.validators;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.world.mapobjects.Fighter;

public abstract class BaseFighterAnimationValidator implements FighterAnimationValidator {

    protected Animation animation;

    public void validate(Fighter fighter) {
        if (fighter.getSprite().isPlaying(animation)) {
            isPlaying(fighter);
        } else {
            isNotPlaying(fighter);
        }
    }
}
