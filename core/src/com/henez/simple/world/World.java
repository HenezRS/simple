package com.henez.simple.world;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.input.In;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.world.battle.Battle;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.map.gamemap.impl.TestMap;
import com.henez.simple.world.mapobjects.ActorFactory;
import com.henez.simple.world.mapobjects.ControlledPlayer;
import com.henez.simple.world.mapobjects.Fighter;
import com.henez.simple.world.mapobjects.MapObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class World {
    private WorldState state;
    private Battle battle;
    private EncounterService encounterService;
    private GameList<MapObject> objects;
    private ControlledPlayer player;
    private GameList<Fighter> playerParty;
    private GameList<Fighter> enemyParty;
    private GameMap currentMap;
    private int stepsUntilEncounter;
    private int stepsUntilEncounterMax = 0;

    public World() {
        state = WorldState.MAP;
        encounterService = new EncounterService();
        currentMap = new TestMap();
        objects = new GameList<>();
        stepsUntilEncounter = stepsUntilEncounterMax;

        int depth = 0;
        int startGx = currentMap.getStartGx();
        int startGy = currentMap.getStartGy();

        player = ActorFactory.createControlledPlayer(startGx, startGy, depth++, ImgSetFighters.class_knight);
        playerParty = new GameList<>();
        playerParty.add(player);
        playerParty.add(ActorFactory.createPlayer(startGx, startGy, depth++, ImgSetFighters.class_knight2));
        playerParty.add(ActorFactory.createPlayer(startGx, startGy, depth++, ImgSetFighters.class_knight3));
        playerParty.add(ActorFactory.createPlayer(startGx, startGy, depth, ImgSetFighters.class_knight4));
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

        if (state == WorldState.BATTLE) {
            updateBattle();
        }

        player.beginMoveIfAble(currentMap);

        if (In.mouse.isClicked()) {
            movePartyTo(In.mouse.getGx(), In.mouse.getGy());
        }
    }

    private void updateBattle() {
        battle.update();
        if (battle.isEnded()) {
            endEncounter();
        }
    }

    private void beginEncounter() {
        state = WorldState.BATTLE;
        stepsUntilEncounter = stepsUntilEncounterMax;

        AtomicInteger depth = new AtomicInteger();
        GameList<ImgSetFighters> img = new GameList<>();
        img.addAll(ImgSetFighters.enemy_octo, ImgSetFighters.enemy_octo2, ImgSetFighters.enemy_octo3, ImgSetFighters.enemy_octo4);
        encounterService.getEncounterPositions().forEach(xy -> {
            enemyParty.add(ActorFactory.createEnemy(xy.getX(), xy.getY(), depth.getAndIncrement(), img.get(depth.get() - 1)));
        });
        Collections.reverse(enemyParty);
        addToWorld(enemyParty);

        battle = new Battle(playerParty, enemyParty);
    }

    private void endEncounter() {
        state = WorldState.MAP;
        objects.removeAll(enemyParty);
        enemyParty = new GameList<>();
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

    public void draw(Batcher batch) {
        objects.forEach(o -> o.draw(batch));
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
}
