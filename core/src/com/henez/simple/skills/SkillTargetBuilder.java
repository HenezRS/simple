package com.henez.simple.skills;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class SkillTargetBuilder {

    private Fighter source;
    private GameList<Fighter> fighters;
    private GameList<Fighter> targets;
    private GameList<Fighter> selectedTargets;
    private boolean targetsAvailable;

    public SkillTargetBuilder(Fighter source, GameList<Fighter> fighters) {
        this.source = source;
        this.fighters = fighters;
        selectedTargets = new GameList<>();
        if (source.isPlayer()) {
            targets = fighters.stream().filter(Fighter::isEnemy).filter(Fighter::canBeTarget).collect(Collectors.toCollection(GameList::new));
        } else {
            targets = fighters.stream().filter(Fighter::isPlayer).filter(Fighter::canBeTarget).collect(Collectors.toCollection(GameList::new));
        }

        targetsAvailable = targets.size() > 0;
    }

    public SkillTarget singleRandomEnemy() {
        addRandomTarget();
        return new SkillTarget(source, selectedTargets);
    }

    private void addRandomTarget() {
        selectedTargets.add(targets.random());
    }
}
