package com.henez.simple.enums.animation;

import com.henez.simple.enums.animation.validators.BaseFighterAnimationValidator;
import com.henez.simple.enums.animation.validators.FighterAnimationValidator;
import com.henez.simple.enums.animation.validators.impl.*;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

//ordered by priority
@Getter
public enum Animation {
    dead(new FighterAnimationValidatorDead()),
    cast(new FighterAnimationValidatorCast()),
    attack(),
    hit(),
    channel(new FighterAnimationValidatorChannel()),
    move(new FighterAnimationValidatorMove()),
    low(new FighterAnimationValidatorLow()),
    idle(),
    none(),
    ;

    FighterAnimationValidator validator;
    boolean isPlayOnce;

    Animation() {
        isPlayOnce = true;
    }

    Animation(FighterAnimationValidator validator) {
        this.validator = validator;
        this.isPlayOnce = false;
    }

    public static void updateFighterAnimationState(Fighter fighter) {
        fighter.getSprite().getExistingAnimationsThatRepeat().forEach(anim -> ((BaseFighterAnimationValidator) anim.getValidator()).validate(fighter, anim));
    }
}
