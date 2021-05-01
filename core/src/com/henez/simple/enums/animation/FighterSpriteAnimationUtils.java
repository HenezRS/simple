package com.henez.simple.enums.animation;

import com.henez.simple.sprite.Sprite;
import com.henez.simple.stats.StatSheet;
import com.henez.simple.world.mapobjects.Fighter;

public class FighterSpriteAnimationUtils {
    private FighterSpriteAnimationUtils() {
    }

    public static void updateFighterSpriteAnimationState(Fighter fighter) {
        Sprite sprite = fighter.getSprite();

        sprite.getAnimations().forEach((key, value) -> {
            if (sprite.hasAnimation(key)) {
                validate(key, fighter);
            }
        });
    }

    private static void validate(Animation anim, Fighter fighter) {
        switch (anim) {
        case dead:
            validateDead(fighter);
        }
    }

    private static void validateDead(Fighter fighter) {
        Sprite sprite = fighter.getSprite();
        StatSheet statSheet = fighter.getStatSheet();
        if (sprite.isPlaying(Animation.dead)) {
            if (!statSheet.isLow()) {
                sprite.stopAnimation(Animation.dead);
            }
        } else {
            if (statSheet.isLow()) {
                sprite.playAnimation(Animation.dead);
            }
        }
    }
}
