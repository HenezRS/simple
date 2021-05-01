package com.henez.simple.enums.animation.validators.impl;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.animation.validators.BaseFighterAnimationValidator;
import com.henez.simple.enums.animation.validators.FighterAnimationValidator;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.world.mapobjects.Fighter;
import com.henez.simple.world.mapobjects.FighterState;

public class FighterAnimationValidatorChannel extends BaseFighterAnimationValidator implements FighterAnimationValidator {

    protected Animation animation = Animation.channel;

    @Override
    public void isPlaying(Fighter fighter) {
        Sprite sprite = fighter.getSprite();
        if (!fighter.fighterStateOneOf(FighterState.CHANNELLING, FighterState.CASTING)) {
            sprite.stopAnimation(animation);
        }
    }

    @Override
    public void isNotPlaying(Fighter fighter) {
        Sprite sprite = fighter.getSprite();
        if (fighter.fighterStateOneOf(FighterState.CHANNELLING, FighterState.CASTING)) {
            sprite.playAnimation(animation);
        }
    }
}
