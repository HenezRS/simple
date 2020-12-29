package com.henez.simple.world;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.input.In;
import com.henez.simple.playerdata.PlayerData;
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
    private PlayerData playerData;
    private ControlledPlayer player;
    private GameList<Fighter> enemyParty;
    private GameMap currentMap;

    public World() {
        state = WorldState.MAP;
        encounterService = new EncounterService();
        currentMap = new TestMap();
        objects = new GameList<>();

        playerData = new PlayerData();
        playerData.setPartyPosition(currentMap.getStartGx(), currentMap.getStartGy());
        player = playerData.getControlledPlayer();

        addToWorld(playerData.getPlayerParty());
    }

    public void update() {
        objects.forEach(obj -> obj.update(state, currentMap));
        if (player.isMoveComplete()) {
            playerData.deductEncounterStep();
            encounterService.reset();
            if (player.getTileDetail().isExit()) {
                player.setMoveAble(false);
                nextFloor();
            } else if (playerData.readyForEncounter() && encounterService.canEncounter(player.getGx(), player.getGy(), player.getLastMoveDir(), currentMap)) {
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
            playerData.setPartyPosition(In.mouse.getGx(), In.mouse.getGy());
        }
    }

    private void updateBattle() {
        battle.update();
        if (battle.isEnded()) {
            endEncounter();
        }
    }

    private void beginEncounter() {
        enemyParty = new GameList<>();
        state = WorldState.BATTLE;
        playerData.resetEncounterSteps();

        AtomicInteger depth = new AtomicInteger();
        GameList<ImgSetFighters> img = new GameList<>();
        img.addAll(ImgSetFighters.enemy_octo,
                   ImgSetFighters.enemy_octo2,
                   ImgSetFighters.enemy_octo3,
                   ImgSetFighters.enemy_octo4);
        encounterService.getEncounterPositions().forEach(xy -> {
            enemyParty.add(ActorFactory.createEnemyPositioned(xy.getX(), xy.getY(), depth.getAndIncrement(), img.get(depth.get() - 1)));
        });
        Collections.reverse(enemyParty);
        addToWorld(enemyParty);

        battle = new Battle(playerData.getPlayerParty(), enemyParty);
    }

    private void endEncounter() {
        state = WorldState.MAP;
        objects.removeAll(enemyParty);
        enemyParty = new GameList<>();
    }

    private void nextFloor() {
        clearWorld();
        currentMap = new TestMap();
        playerData.setPartyPosition(currentMap.getStartGx(), currentMap.getStartGy());
        addToWorld(playerData.getPlayerParty());
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
