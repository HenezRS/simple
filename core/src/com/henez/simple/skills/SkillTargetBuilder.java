package com.henez.simple.skills;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.Fighter;

import java.util.stream.Collectors;

public class SkillTargetBuilder {

    Fighter source;
    GameList<Fighter> fighters;
    GameList<Fighter> targets;
    GameList<Fighter> selectedTargets;

    public SkillTargetBuilder(Fighter source, GameList<Fighter> fighters) {
        this.source = source;
        this.fighters = fighters;
        selectedTargets = new GameList<>();
        if (source.isPlayer()) {
            targets = fighters.stream().filter(Fighter::isEnemy).collect(Collectors.toCollection(GameList::new));
        } else {
            targets = fighters.stream().filter(Fighter::isPlayer).collect(Collectors.toCollection(GameList::new));
        }
    }

    public SkillTarget singleRandomEnemy() {
        addRandomTarget();
        return new SkillTarget(source, selectedTargets);
    }

    private void addRandomTarget() {
        selectedTargets.add(targets.random());
    }
}
