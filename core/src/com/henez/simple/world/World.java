package com.henez.simple.world;

import com.henez.simple.atlas.imgs.ImgIcon7;
import com.henez.simple.data.PlayerData;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.debug.DebugFlags;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.input.In;
import com.henez.simple.misc.XY;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.world.battle.Battle;
import com.henez.simple.world.enemies.EnemyPartyName;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.map.gamemap.impl.TestMap;
import com.henez.simple.world.mapobjects.ActorFactory;
import com.henez.simple.world.mapobjects.ControlledPlayer;
import com.henez.simple.world.mapobjects.Fighter;
import com.henez.simple.world.mapobjects.MapObject;
import lombok.Getter;

import java.util.Arrays;
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
        playerData = new PlayerData();
    }

    public void beginNewWorld(GameMap gameMap) {
        currentMap = gameMap;
        clearWorld();
        playerData.setPartyPosition(currentMap.getStartGx(), currentMap.getStartGy());
        player = playerData.getControlledPlayer();
        addToWorld(playerData.getPlayerParty());
    }

    public void update() {
        if (!battleIsPaused()) {
            objects.forEach(obj -> obj.update(state, currentMap));
            if (player.isMoveComplete()) {
                playerData.deductEncounterStep();
                encounterService.reset();
                if (player.getTileDetail().isExit()) {
                    player.setMoveAble(false);
                    nextFloor();
                } else if (playerData.readyForEncounter() && encounterService.canEncounter(player.getGx(), player.getGy(), player.getLastMoveDir(), currentMap)) {
                    if (encounterService.setEncounterPositionsAndReturnValid(currentMap, objects)) {
                        player.setMoveAble(false);
                        beginEncounter();
                    }
                }
            }

            if (state == WorldState.BATTLE) {
                updateBattle();
            }

            player.beginMoveIfAble(currentMap);

            if (DebugFlags.canLeftClickToTeleport && In.mouse.isClicked()) {
                playerData.setPartyPosition(In.mouse.getGx(), In.mouse.getGy());
            }
        }

        if (state == WorldState.BATTLE) {
            battle.getBattleControl().capturePausingInput();
        }

        playerData.update();
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
        EnemyPartyName.octos.getOrdered().forEach(enemyClass -> {
            XY xy = encounterService.getEncounterPositions().get(depth.get());
            enemyParty.add(ActorFactory.createEnemyPositioned(xy.getX(), xy.getY(), depth.getAndIncrement(), enemyClass));
        });
        enemyParty.get(0).setIsLeader();
        addToWorld(enemyParty);

        battle = new Battle(playerData.getPlayerParty(), enemyParty);
    }

    private void endEncounter() {
        state = WorldState.MAP;
        objects.removeAll(enemyParty);
        enemyParty = new GameList<>();
    }

    private void nextFloor() {
        beginNewWorld(new TestMap());
    }

    public void draw(Batcher batch) {
        objects.forEach(o -> o.draw(batch));
    }

    //todo: make world drawer
    public void drawIcons(Batcher batch) {
        if (state == WorldState.BATTLE && In.tab.isHeld()) {
            batch.begin();
            enemyParty.forEach(f -> {
                if (f.isEnemy() && f.isMajor()) {
                    batch.draw(ImgIcon7.crown.asTex(), f.getX() + 5, f.getY() - 3);
                }
                if (f.isEnemy() && f.isMinor()) {
                    batch.draw(f.getClassName().getEnemyMinorType().getImg().asTex(), f.getX() + 16 - 7, f.getY());
                }
            });
            batch.end();
        }
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

    public boolean battleIsPaused() {
        return state == WorldState.BATTLE && battle.getBattleControl().isPaused() && !battle.isEnded();
    }
}
