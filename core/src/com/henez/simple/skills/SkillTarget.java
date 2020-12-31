package com.henez.simple.skills;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public class SkillTarget {

    Fighter source;
    Fighter target;
    GameList<Fighter> targets;

    public SkillTarget(Fighter source, GameList<Fighter> targets) {
        this.source = source;
        this.targets = targets;

        target = targets.get(0);
    }

    public boolean targetsNoLongerValid() {
        return targets.stream().filter(Fighter::canBeTarget).count() < 1;
    }
}
