package com.henez.simple.enums.animation.validators;

import com.henez.simple.world.mapobjects.Fighter;

public interface FighterAnimationValidator {
    void isPlaying(Fighter fighter);

    void isNotPlaying(Fighter fighter);
}
