package com.henez.simple.enums.animation.validators;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.world.mapobjects.Fighter;

public interface FighterAnimationValidator {
    void isPlaying(Fighter fighter, Animation animation);

    void isNotPlaying(Fighter fighter, Animation animation);
}
