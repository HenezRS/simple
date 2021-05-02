package com.henez.simple.enums.animation.validators;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.world.mapobjects.Fighter;

public abstract class BaseFighterAnimationValidator implements FighterAnimationValidator {

    public void validate(Fighter fighter, Animation animation) {
        if (fighter.getSprite().isPlaying(animation)) {
            isPlaying(fighter, animation);
        } else {
            isNotPlaying(fighter, animation);
        }
    }
}
