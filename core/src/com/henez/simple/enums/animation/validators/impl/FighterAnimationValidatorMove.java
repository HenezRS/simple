package com.henez.simple.enums.animation.validators.impl;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.animation.validators.BaseFighterAnimationValidator;
import com.henez.simple.enums.animation.validators.FighterAnimationValidator;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.world.mapobjects.Fighter;

public class FighterAnimationValidatorMove extends BaseFighterAnimationValidator implements FighterAnimationValidator {

    protected Animation animation = Animation.move;

    @Override
    public void isPlaying(Fighter fighter) {
        Sprite sprite = fighter.getSprite();
        if (!fighter.getMovement().isMoving()) {
            sprite.playAnimationSync(Animation.idle, Animation.move);
            sprite.stopAnimation(Animation.move);
        }
    }

    @Override
    public void isNotPlaying(Fighter fighter) {
        Sprite sprite = fighter.getSprite();
        if (fighter.getMovement().isMoving()) {
            sprite.playAnimationSync(Animation.move, Animation.idle);
        }
    }
}
