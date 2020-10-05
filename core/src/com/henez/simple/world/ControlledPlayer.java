package com.henez.simple.world;

import com.henez.simple.enums.Facing;
import com.henez.simple.input.In;
import com.henez.simple.sprite.Sprite;

import java.util.Optional;

public class ControlledPlayer extends Fighter {
    public ControlledPlayer(int gx, int gy, Sprite sprite) {
        super(gx, gy, sprite);
    }

    @Override public void update() {
        super.update();

        if (!movement.isMoving()) {
            pollInputMovement().ifPresent(facing -> {
                if (canMove(facing)) {
                    beginMove(facing);
                }
            });
        }
    }

    private Optional<Facing> pollInputMovement() {
        if (In.right.isHeld()) {
            return Optional.of(Facing.RIGHT);
        } else if (In.left.isHeld()) {
            return Optional.of(Facing.LEFT);
        } else if (In.up.isHeld()) {
            return Optional.of(Facing.UP);
        } else if (In.down.isHeld()) {
            return Optional.of(Facing.DOWN);
        }

        return Optional.empty();
    }
}
