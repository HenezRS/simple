package com.henez.simple.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.Static;
import com.henez.simple.datastructures.TextureRegionEnhanced;
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

    public void addTransformCamera() {
        tx = Static.renderer.getX();
        ty = Static.renderer.getY();
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

    public void draw(TextureRegion region, float x, float y, Facing facing, int size) {
        if (facing == Facing.LEFT) {
            super.draw(region, x + tx + Global.tilePixelSize, y + ty, -size, size);
        } else {
            super.draw(region, x + tx, y + ty);
        }
    }

    public void draw(TextureRegionEnhanced region, float x, float y, Facing facing, int size) {
        if (facing == Facing.LEFT) {
            super.draw(region.getTex(), x + tx + Global.tilePixelSize + (-1 * region.getAddX()), y + ty, -size + region.getAddY(), size);
        } else {
            super.draw(region.getTex(), x + tx + region.getAddX(), y + ty + region.getAddY());
        }
    }

    public void drawToCamera(TextureRegion region, float x, float y) {
        draw(region, x + Static.renderer.getX(), y + Static.renderer.getY());
    }

    public void drawToCamera(TextureRegion region, float x, float y, Facing facing, int size) {
        draw(region, x + Static.renderer.getX(), y + Static.renderer.getY(), facing, size);
    }

    public void drawToCamera(TextureRegionEnhanced region, float x, float y, Facing facing, int size) {
        draw(region, x + Static.renderer.getX(), y + Static.renderer.getY(), facing, size);
    }

    public void draw(TextureRegion region, float x, float y, float rotation) {
        super.draw(region, x + tx, y + ty, 8, 8, 16, 16, 1, 1, rotation);
    }

    public void setAlpha(float a) {
        setColor(1f, 1f, 1f, a);
    }

    public void resetAlpha() {
        setColor(1f, 1f, 1f, 1f);
    }
}
