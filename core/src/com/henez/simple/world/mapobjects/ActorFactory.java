package com.henez.simple.world.mapobjects;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.enums.Animation;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.sprite.animation.AnimationDynamicFactory;

public final class ActorFactory {
    private ActorFactory() {
    }

    public static ControlledPlayer createControlledPlayer(int depth, ImgSetFighters imgSetFighters) {
        ControlledPlayer player = new ControlledPlayer(0, 0, imgSetFighters, depth);
        buildSprite(player);
        player.setIsPlayer();
        player.setIsLeader();
        return player;
    }

    public static Fighter createEnemy(int depth, ImgSetFighters imgSetFighters) {
        Fighter enemy = new Fighter(0, 0, imgSetFighters, depth);
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createEnemyPositioned(int startGx, int startGy, int depth, ImgSetFighters imgSetFighters) {
        Fighter enemy = new Fighter(0, 0, imgSetFighters, depth);
        enemy.setPosition(startGx, startGy);
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createPlayer(int depth, ImgSetFighters imgSetFighters) {
        Fighter player = createEnemy(depth, imgSetFighters);
        player.setIsPlayer();
        return player;
    }

    private static void buildSprite(Fighter fighter) {
        fighter.giveAnimation(Animation.idle, new SpriteAnimation(AnimationDynamicFactory.toActorIdle(fighter.getImgSetFighters())));
        fighter.giveAnimation(Animation.move, new SpriteAnimation(AnimationDynamicFactory.toActorMove(fighter.getImgSetFighters())));
        fighter.giveAnimation(Animation.attack, new SpriteAnimation(AnimationDynamicFactory.toActorAttack(fighter.getImgSetFighters())));
        fighter.giveAnimation(Animation.attack_fast, new SpriteAnimation(AnimationDynamicFactory.toActorAttackFast(fighter.getImgSetFighters())));
        fighter.getSprite().setCurrent(Animation.idle);
    }
}
