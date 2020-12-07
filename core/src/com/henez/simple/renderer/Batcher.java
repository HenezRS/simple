package com.henez.simple.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.enums.Facing;
import com.henez.simple.global.Global;
import com.henez.simple.sprite.BatchDrawable;

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

    @Override
    public void draw(Texture region, float x, float y) {
        super.draw(region, x + tx, y + ty);
    }

    @Override
    public void draw(TextureRegion region, float x, float y) {
        super.draw(region, x + tx, y + ty);
    }

    public void draw(TextureRegion region, float x, float y, Facing facing) {
        if (facing == Facing.LEFT) {
            super.draw(region, x + tx + Global.tilePixelSize, y + ty, -Global.tilePixelSize, Global.tilePixelSize);
        } else {
            super.draw(region, x + tx, y + ty);
        }
    }

    public void draw(TextureRegion region, float x, float y, float rotation) {
        super.draw(region, x + tx, y + ty, 8, 8, 16, 16, 1, 1, rotation);
    }

    public void draw(BatchDrawable drawable) {
        if (drawable.getFacing() == Facing.LEFT) {
            super.draw(drawable.getTex(), drawable.getX() + tx + Global.tilePixelSize, drawable.getY() + ty, -Global.tilePixelSize, Global.tilePixelSize);
        } else {
            super.draw(drawable.getTex(), drawable.getX() + tx, drawable.getY() + ty);
        }
    }
}
