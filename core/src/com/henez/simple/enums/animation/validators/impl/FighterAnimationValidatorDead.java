package com.henez.simple.enums.animation.validators.impl;

import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.animation.validators.BaseFighterAnimationValidator;
import com.henez.simple.enums.animation.validators.FighterAnimationValidator;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.StatSheet;
import com.henez.simple.world.mapobjects.Fighter;

public class FighterAnimationValidatorDead extends BaseFighterAnimationValidator implements FighterAnimationValidator {

    @Override
    public void isPlaying(Fighter fighter, Animation animation) {
        Sprite sprite = fighter.getSprite();
        StatSheet statSheet = fighter.getStatSheet();
        if (!statSheet.isDead()) {
            sprite.stopAnimation(animation);
        }
    }

    @Override
    public void isNotPlaying(Fighter fighter, Animation animation) {
        Sprite sprite = fighter.getSprite();
        StatSheet statSheet = fighter.getStatSheet();
        if (statSheet.isDead()) {
            sprite.playAnimation(animation);
        }
    }
}
