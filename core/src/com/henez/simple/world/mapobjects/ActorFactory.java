package com.henez.simple.world.mapobjects;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.animation.Animation;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.sprite.animation.AnimationDynamic;
import com.henez.simple.sprite.animation.AnimationDynamicFactory;
import com.henez.simple.stats.classes.ClassName;

import java.util.Collections;

public final class ActorFactory {

    private static int nextId = 0;
    private static GameList<String> generatedNames = new GameList<String>().addAllAndReturn("kuponut", "dislife", "huntsman", "magorical", "chillman", "beanboi", "mariko", "wafflyhawk",
            "zurence", "king of hats", "poog", "marrjak", "detro au", "merincholy","mars tender","pepe", "wojak", "soyjak","chad","ash", "gary","zelda","link",
            "fishsticks","ryze","jax","fiora","kaisa","vayne","kalista","jhin","ezreal","lucian","tristana","squall","rinoa","zell","selphie","irvine","quistis","cloud",
            "aerith","cait sith","red XIII", "barret","tifa","cid","zidane","garnet","dagger","vivi","steiner","eiko","quina","freya");

    private ActorFactory() {
    }

    public static ControlledPlayer createControlledPlayer(int depth, ClassName className) {
        ControlledPlayer player = new ControlledPlayer(0, 0, className, depth);
        buildSprite(player);
        player.setIsPlayer();
        player.setName("henez");
        player.setIsLeader();
        return player;
    }

    public static Fighter createEnemyPositioned(int startGx, int startGy, int depth, ClassName className) {
        Fighter enemy = new Fighter(0, 0, className, depth);
        enemy.setPosition(startGx, startGy);
        buildSprite(enemy);
        return enemy;
    }

    public static Fighter createPlayer(int depth, ClassName className) {
        Fighter player = new Fighter(0, 0, className, depth);
        buildSprite(player);
        player.setIsPlayer();
        player.setName(generateName());
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

    private static String generateName() {
        Collections.shuffle(generatedNames);
        return generatedNames.stream().findAny().map(name -> {
            generatedNames.remove(name);
            return name.length()>12 ? name.substring(0, 12) : name;
        }).orElse("no name");
    }

    public static int getNextIdAndIncrement() {
        return nextId++;
    }
}
