package com.henez.simple.world;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.ImgActors;
import com.henez.simple.atlas.ImgEnemies;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Animation;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.global.Global;
import com.henez.simple.input.In;
import com.henez.simple.sprite.BatchDrawable;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.map.gamemap.impl.TestMap;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public class World {
    private WorldState state;
    private EncounterService encounterService;
    private GameList<MapObject> objects;
    private ControlledPlayer player;
    private GameList<Fighter> playerParty;
    private GameList<Fighter> enemyParty;
    private GameMap currentMap;
    private int stepsUntilEncounter;
    private int stepsUntilEncounterMax = 10;

    public World() {
        state = WorldState.MAP;
        encounterService = new EncounterService();
        currentMap = new TestMap();
        objects = new GameList<>();
        stepsUntilEncounter = stepsUntilEncounterMax;

        int depth = 0;
        int startGx = currentMap.getStartGx();
        int startGy = currentMap.getStartGy();

        player = defaultPlayerController(startGx, startGy, depth++);
        playerParty = new GameList<>();
        playerParty.add(player);
        playerParty.add(defaultPlayer(startGx, startGy, depth++));
        playerParty.add(defaultPlayer(startGx, startGy, depth++));
        playerParty.add(defaultPlayer(startGx, startGy, depth));
        player.setParty(playerParty);

        addToWorld(playerParty);

        enemyParty = new GameList<>();
    }

    public void update() {
        objects.forEach(obj -> obj.update(state, currentMap));
        if (player.isMoveComplete()) {
            stepsUntilEncounter--;
            encounterService.reset();
            if (player.getTileDetail().isExit()) {
                player.setMoveAble(false);
                nextFloor();
            } else if (stepsUntilEncounter < 0 && player.canEncounter() && encounterService.canEncounter(player.getGx(), player.getGy(), player.getLastMoveDir(), currentMap)) {
                if (encounterService.setEncounterPositionsAndReturnValid(currentMap)) {
                    player.setMoveAble(false);
                    beginEncounter();
                }
            }
        }

        player.beginMoveIfAble(currentMap);

        if (In.mouse.isClicked()) {
            movePartyTo(In.mouse.getGx(), In.mouse.getGy());
        }
    }

    private void beginEncounter() {
        state = WorldState.BATTLE;
        stepsUntilEncounter = stepsUntilEncounterMax;

        AtomicInteger depth = new AtomicInteger();
        encounterService.getEncounterPositions().forEach(xy -> {
            enemyParty.add(defaultEnemy(xy.getX(), xy.getY(), depth.getAndIncrement()));
        });
        addToWorld(enemyParty);
    }

    private void endEncounter() {
        state = WorldState.MAP;
    }

    private void nextFloor() {
        clearWorld();
        currentMap = new TestMap();
        movePartyTo(currentMap.getStartGx(), currentMap.getStartGy());
        addToWorld(playerParty);
    }

    private void movePartyTo(int gx, int gy) {
        AtomicInteger depth = new AtomicInteger();
        playerParty.forEach(player -> player.resetPosition(gx, gy, depth.getAndIncrement()));
    }

    public GameList<BatchDrawable> getDrawables() {
        return objects.stream().map(MapObject::getDrawable).collect(Collectors.toCollection(GameList::new));
    }

    public void addToWorld(MapObject... objectsToAdd) {
        objects.addAll(Arrays.asList(objectsToAdd));
        orderWorldByDepth();
    }

    public void addToWorld(GameList<Fighter> objectsToAdd) {
        objects.addAll(objectsToAdd);
        orderWorldByDepth();
    }

    public void clearWorld() {
        objects = new GameList<>();
    }

    private void orderWorldByDepth() {
        objects.sort(Comparator.comparing(MapObject::getDepth).reversed());
    }

    private ControlledPlayer defaultPlayerController(int startGx, int startGy, int depth) {
        ControlledPlayer player = new ControlledPlayer(startGx, startGy, new Sprite(), depth);
        player.giveAnimation(Animation.idle, new SpriteAnimation(Global.SEC2, Atlas.toTex(ImgActors.knight_idle_0), Atlas.toTex(ImgActors.knight_idle_1)));
        player.giveAnimation(Animation.move, new SpriteAnimation(Global.SEC2, 3.0f, Atlas.toTex(ImgActors.knight_idle_0), Atlas.toTex(ImgActors.knight_idle_1)));
        player.getSprite().setCurrent(Animation.idle);
        return player;
    }

    private Fighter defaultPlayer(int startGx, int startGy, int depth) {
        Fighter player = new Fighter(startGx, startGy, new Sprite(), depth);
        player.giveAnimation(Animation.idle, new SpriteAnimation(Global.SEC2, Atlas.toTex(ImgActors.knight_idle_0), Atlas.toTex(ImgActors.knight_idle_1)));
        player.giveAnimation(Animation.move, new SpriteAnimation(Global.SEC2, 3.0f, Atlas.toTex(ImgActors.knight_idle_0), Atlas.toTex(ImgActors.knight_idle_1)));
        player.getSprite().setCurrent(Animation.idle);
        return player;
    }

    private Fighter defaultEnemy(int startGx, int startGy, int depth) {
        Fighter enemy = new Fighter(startGx, startGy, new Sprite(), depth);
        enemy.giveAnimation(Animation.idle, new SpriteAnimation(Global.SEC2, Atlas.toTex(ImgEnemies.octo_idle_0), Atlas.toTex(ImgEnemies.octo_idle_1)));
        enemy.getSprite().setCurrent(Animation.idle);
        return enemy;
    }
}
