package com.henez.simple.world.mapobjects;

import com.henez.simple.enums.Animation;
import com.henez.simple.sprite.SpriteAnimation;
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
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createPlayer(int depth, ClassName className) {
        Fighter player = createEnemy(depth, className);
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
