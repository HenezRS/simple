package com.henez.simple.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.enums.Facing;
import com.henez.simple.global.Global;

public class Batcher extends SpriteBatch {
    private int tx;
    private int ty;

    public Batcher() {
        super();
        resetTransform();
    }

    public void addTransform(int x, int y) {
        tx = x;
        ty = y;
    }

    public void resetTransform() {
        tx = 0;
        ty = 0;
    }

    @Override public void draw(Texture region, float x, float y) {
        super.draw(region, x + tx, y + ty);
    }

    @Override public void draw(TextureRegion region, float x, float y) {
        super.draw(region, x + tx, y + ty);
    }

    public void draw(TextureRegion region, float x, float y, float rotation) {
        super.draw(region, x + tx, y + ty,8,8,16,16,1,1,rotation);
    }

    public void draw(TextureRegion region, float x, float y, Facing facing2) {
        if (facing2 == Facing.RIGHT) {
            super.draw(region, x + tx + Global.tilePixelSize, y + ty, -Global.tilePixelSize, Global.tilePixelSize);
        } else {
            super.draw(region, x + tx, y + ty);
        }
    }
}
