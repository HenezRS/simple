package com.henez.simple.world.mapobjects;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.enums.state.WorldState;
import com.henez.simple.input.In;
import com.henez.simple.stats.classes.ClassName;
import com.henez.simple.world.map.gamemap.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class ControlledPlayer extends Fighter {
    private boolean moveAble;
    private GameList<Fighter> party;

    public ControlledPlayer(int gx, int gy, ClassName className, int depth) {
        super(gx, gy, className, depth);
    }

    @Override
    public void update(WorldState state, GameMap map) {
        super.update(state, map);
        moveAble = !movement.isMoving() && state == WorldState.MAP;
    }

    public void beginMoveIfAble(GameMap map) {
        if (moveAble) {
            pollInputMovement().ifPresent(facing -> {
                if (canMove(facing, map)) {
                    beginMoveParty(facing, map);
                }
            });
        }
    }

    public boolean canEncounter() {
        return partyMembersAreSpread();
    }

    private boolean partyMembersAreSpread() {
        return party.stream().noneMatch(p -> party.stream().filter(pp -> p.getGx() == pp.getGx() && p.getGy() == pp.getGy()).collect(Collectors.toCollection(GameList::new)).size() > 1);
    }

    private void beginMoveParty(Facing dir, GameMap map) {
        GameList<Facing> dirNext = party.stream().map(Actor::getLastMoveDir).collect(Collectors.toCollection(GameList::new));
        beginMove(dir, map);
        for (int i = 1; i < party.size(); ++i) {
            party.get(i).beginMove(dirNext.get(i - 1), map);
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
