package com.henez.simple.world;

import com.henez.simple.enums.Animation;
import com.henez.simple.global.Global;
import com.henez.simple.sprite.BatchDrawable;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.sprite.SpriteAnimation;
import lombok.Getter;

@Getter
public class MapObject {
    protected float x;
    protected float y;
    protected int gx;
    protected int gy;
    protected Sprite sprite;

    public MapObject(int gx, int gy, Sprite sprite) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Global.tilePixelSize;
        this.y = gy * Global.tilePixelSize;
        this.sprite = sprite;
    }

    public void update() {
        sprite.update();
    }

    public BatchDrawable getDrawable() {
        return new BatchDrawable(x, y, sprite.getTex());
    }

    public void giveAnimation(Animation animation, SpriteAnimation spriteAnimation) {
        sprite.getAnimations().put(animation, spriteAnimation);
    }
}
