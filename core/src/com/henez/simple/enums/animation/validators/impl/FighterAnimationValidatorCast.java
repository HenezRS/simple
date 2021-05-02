package com.henez.simple.enums.animation.validators.impl;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.animation.validators.BaseFighterAnimationValidator;
import com.henez.simple.enums.animation.validators.FighterAnimationValidator;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.world.mapobjects.Fighter;

public class FighterAnimationValidatorCast extends BaseFighterAnimationValidator implements FighterAnimationValidator {

    @Override
    public void isPlaying(Fighter fighter, Animation animation) {
        Sprite sprite = fighter.getSprite();

        if (fighter.getSkillExecution().isDone()) {
            sprite.stopAnimation(animation);
        }
    }

    @Override
    public void isNotPlaying(Fighter fighter, Animation animation) {
        //Cast animation should start in a skillExecution and never disabled here.
    }
}
