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

import java.util.stream.Collectors;

@Getter
public class World {
    private GameList<MapObject> objects;
    private ControlledPlayer player;
    private GameMap currentMap;

    public World() {
        currentMap = new TestMap();
        objects = new GameList<>();

        player = new ControlledPlayer(50, 15, new Sprite());
        player.giveAnimation(Animation.idle, new SpriteAnimation(Global.SEC2, Atlas.toTex(ImgActors.knight_idle_0), Atlas.toTex(ImgActors.knight_idle_1)));
        player.giveAnimation(Animation.move, new SpriteAnimation(Global.SEC2, 3.0f, Atlas.toTex(ImgActors.knight_idle_0), Atlas.toTex(ImgActors.knight_idle_1)));
        player.getSprite().setCurrent(Animation.idle);

        objects.add(player);
    }

    public void update() {
        objects.forEach(MapObject::update);
    }

    public GameList<BatchDrawable> getDrawables() {
        return objects.stream().map(MapObject::getDrawable).collect(Collectors.toCollection(GameList::new));
    }
}
