package com.henez.simple.world.mapobjects;

import com.henez.simple.enums.Animation;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.sprite.animation.AnimationDynamic;
import com.henez.simple.sprite.animation.AnimationDynamicFactory;
import com.henez.simple.stats.classes.ClassName;

public final class ActorFactory {
    private ActorFactory() {
    }

    public static ControlledPlayer createControlledPlayer(int depth, ClassName className) {
        ControlledPlayer player = new ControlledPlayer(0, 0, className, depth);
        buildSprite(player);
        player.setIsPlayer();
        player.setIsLeader();
        return player;
    }

    public static Fighter createEnemy(int depth, ClassName className) {
        Fighter enemy = new Fighter(0, 0, className, depth);
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createEnemyPositioned(int startGx, int startGy, int depth, ClassName className) {
        Fighter enemy = new Fighter(0, 0, className, depth);
        enemy.setPosition(startGx, startGy);
        enemy.isMinor = className.isMinor();
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createPlayer(int depth, ClassName className) {
        Fighter player = createEnemy(depth, className);
        player.setIsPlayer();
        return player;
    }

    private static void buildSprite(Fighter fighter) {
        giveAnimationIfNotNull(fighter, Animation.idle, AnimationDynamicFactory.toActorIdle(fighter.getImgSetFighters()));
        giveAnimationIfNotNull(fighter, Animation.move, AnimationDynamicFactory.toActorMove(fighter.getImgSetFighters()));
        giveAnimationIfNotNull(fighter, Animation.attack, AnimationDynamicFactory.toActorAttack(fighter.getImgSetFighters()));
        giveAnimationIfNotNull(fighter, Animation.hit, AnimationDynamicFactory.toActorHit(fighter.getImgSetFighters()));
        giveAnimationIfNotNull(fighter, Animation.channel, AnimationDynamicFactory.toActorChannel(fighter.getImgSetFighters()));
        giveAnimationIfNotNull(fighter, Animation.cast, AnimationDynamicFactory.toActorCast(fighter.getImgSetFighters()));
        giveAnimationIfNotNull(fighter, Animation.dead, AnimationDynamicFactory.toActorDead(fighter.getImgSetFighters()));
    }

    private static void giveAnimationIfNotNull(Fighter fighter, Animation animation, AnimationDynamic dynamic) {
        if (dynamic != null) {
            fighter.giveAnimation(animation, new SpriteAnimation(dynamic));
        }
    }
}
