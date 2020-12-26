package com.henez.simple.world.mapobjects;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.enums.Animation;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.sprite.animation.AnimationDynamicFactory;

public final class ActorFactory {
    private ActorFactory() {
    }

    public static ControlledPlayer createControlledPlayer(int startGx, int startGy, int depth, ImgSetFighters imgSetFighters) {
        ControlledPlayer player = new ControlledPlayer(startGx, startGy, imgSetFighters, depth);
        buildSprite(player);
        player.setIsPlayer();
        return player;
    }

    public static Fighter createEnemy(int startGx, int startGy, int depth, ImgSetFighters imgSetFighters) {
        Fighter enemy = new Fighter(startGx, startGy, imgSetFighters, depth);
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createPlayer(int startGx, int startGy, int depth, ImgSetFighters imgSetFighters) {
        Fighter player = createEnemy(startGx, startGy, depth, imgSetFighters);
        player.setIsPlayer();
        return player;
    }

    private static void buildSprite(Fighter fighter) {
        fighter.giveAnimation(Animation.idle, new SpriteAnimation(AnimationDynamicFactory.toActorIdle(fighter.getImgSetFighters())));
        fighter.giveAnimation(Animation.move, new SpriteAnimation(AnimationDynamicFactory.toActorMove(fighter.getImgSetFighters())));
        fighter.giveAnimation(Animation.attack, new SpriteAnimation(AnimationDynamicFactory.toActorAttack(fighter.getImgSetFighters())));
        fighter.getSprite().setCurrent(Animation.idle);
    }
}
