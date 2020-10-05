package com.henez.simple.world;

import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.ImgActors;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Animation;
import com.henez.simple.global.Global;
import com.henez.simple.sprite.BatchDrawable;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.map.gamemap.impl.TestMap;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Getter
public class World {
    private GameList<MapObject> objects;
    private ControlledPlayer player;
    private GameList<Fighter> playerParty;
    private GameMap currentMap;

    public World() {
        currentMap = new TestMap();
        objects = new GameList<>();

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
    }

    public void update() {
        objects.forEach(obj -> obj.update(currentMap));
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
}
