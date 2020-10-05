package com.henez.simple.world;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.input.In;
import com.henez.simple.sprite.Sprite;
import com.henez.simple.world.map.gamemap.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class ControlledPlayer extends Fighter {
    private GameList<Fighter> party;

    public ControlledPlayer(int gx, int gy, Sprite sprite, int depth) {
        super(gx, gy, sprite, depth);
    }

    @Override
    public void update(GameMap map) {
        super.update(map);

        if (!movement.isMoving()) {
            pollInputMovement().ifPresent(facing -> {
                if (canMove(facing, map)) {
                    beginMoveParty(facing);
                }
            });
        }
    }

    private void beginMoveParty(Facing dir) {
        GameList<Facing> dirNext = party.stream().map(Fighter::getLastMoveDir).collect(Collectors.toCollection(GameList::new));
        beginMove(dir);
        for (int i = 1; i < party.size(); ++i) {
            party.get(i).beginMove(dirNext.get(i - 1));
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
