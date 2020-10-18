package com.henez.simple.world;

import com.henez.simple.enums.Animation;
import com.henez.simple.enums.Facing;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.global.Global;
import com.henez.simple.sprite.BatchDrawable;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.world.actions.Movement;
import com.henez.simple.world.map.gamemap.GameMap;
import com.henez.simple.world.map.tiles.TileDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fighter extends MapObject {
    protected Movement movement;
    protected Facing facing2;
    protected Facing lastMoveDir;
    protected boolean moveComplete;
    protected TileDetail tileDetail;

    public Fighter(int gx, int gy, Sprite sprite, int depth) {
        super(gx, gy, sprite, depth);
        facing2 = Facing.RIGHT;
        movement = new Movement();
    }

    @Override
    public void resetPosition(int gx, int gy, int depth) {
        super.resetPosition(gx, gy, depth);
        lastMoveDir = null;
    }

    @Override
    public void update(WorldState state, GameMap map) {
        moveComplete = false;
        if (movement.update()) {
            move(movement.addMoveX(), movement.addMoveY());
            if (!movement.isMoving()) {
                completeMove();
            }
        }
        sprite.update();
    }

    @Override
    public BatchDrawable getDrawable() {
        return new BatchDrawable(x, y, sprite.getTex(), facing2);
    }

    public boolean canMove(Facing facing, GameMap map) {
        return map.getTileDetail(gx + facing.tx, gy + facing.ty).isWalkable();
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    protected void snapToGrid() {
        gx = (int) x / Global.tilePixelSize;
        gy = (int) y / Global.tilePixelSize;
    }

    public void beginMove(Facing facing, GameMap map) {
        if (facing == null) {
            return;
        }
        lastMoveDir = facing;
        sprite.setAnimationAndContinue(Animation.move);
        movement.begin(facing);
        tileDetail = map.getTileDetail(gx + facing.tx, gy + facing.ty);
        if (facing == Facing.LEFT || facing == Facing.RIGHT) {
            facing2 = facing;
        }
    }

    public void completeMove() {
        moveComplete = true;
        sprite.setAnimationAndContinue(Animation.idle);
        snapToGrid();
    }
}
