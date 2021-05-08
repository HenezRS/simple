package com.henez.simple.world.mapobjects;

import com.henez.simple.enums.Facing;
import com.henez.simple.enums.animation.Animation;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.global.Global;
import com.henez.simple.input.In;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.sprite.SpriteAnimation;
import com.henez.simple.world.map.gamemap.GameMap;
import lombok.Getter;

@Getter
public class MapObject {
    protected float x;
    protected float y;
    protected int gx;
    protected int gy;
    protected Sprite sprite;
    protected int depth;

    public MapObject(int gx, int gy, Sprite sprite, int depth) {
        resetPosition(gx, gy, depth);
        this.sprite = sprite;
    }

    public void resetPosition(int gx, int gy, int depth) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Global.tilePixelSize;
        this.y = gy * Global.tilePixelSize;
        this.depth = depth;
    }

    public void setPosition(int gx, int gy) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Global.tilePixelSize;
        this.y = gy * Global.tilePixelSize;
    }

    public void update(WorldState state, GameMap map) {
        sprite.update();
    }

    public void draw(Batcher batch) {
        sprite.draw(batch, x, y, Facing.actorDefault());
    }

    public void giveAnimation(Animation animation, SpriteAnimation spriteAnimation) {
        sprite.getAnimations().put(animation, spriteAnimation);
    }

    public boolean isMouseOver() {
        return In.mouse.isMouseWithinGrid(gx, gy);
    }

    public boolean isClickedOn() {
        return In.mouse.isClicked() && In.mouse.isMouseWithinGrid(gx, gy);
    }
}
