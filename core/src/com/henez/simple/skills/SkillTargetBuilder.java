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
    private SkillTarget preparedTargets;

    public SkillTargetBuilder(Fighter source, GameList<Fighter> fighters) {
        this.source = source;
        this.fighters = fighters;
        selectedTargets = new GameList<>();
        targetsAvailable = false;
    }

    private void determinePotentialTargetList(SkillName skillName) {
        boolean shouldTargetEnemies = ((source.isPlayer() && skillName.isTargetEnemies()) || (source.isEnemy() && !skillName.isTargetEnemies()));

        if (shouldTargetEnemies) {
            targets = fighters.stream().filter(Fighter::isEnemy).filter(Fighter::canBeTarget).collect(Collectors.toCollection(GameList::new));
        } else {
            targets = fighters.stream().filter(Fighter::isPlayer).filter(Fighter::canBeTarget).collect(Collectors.toCollection(GameList::new));
        }

        targetsAvailable = targets.size() > 0;
    }

    public void createTargetIntelligent(SkillName skillName) {
        determinePotentialTargetList(skillName);
        switch (skillName.getTargetType()) {
        case SINGLE:
            singleRandomEnemy();
            break;
        case ALL:
            allEnemies();
            break;
        }
        preparedTargets = new SkillTarget(source, selectedTargets);
    }

    public void createTargetWithPrimary(SkillName skillName, Fighter target) {
        determinePotentialTargetList(skillName);
        switch (skillName.getTargetType()) {
        case SINGLE:
            addTargetOrAddFirstValid(target);
            break;
        case ALL:
            allEnemiesWithPrimary(target);
            break;
        }
        preparedTargets = new SkillTarget(source, selectedTargets);
    }

    private void singleRandomEnemy() {
        addRandomTarget();
    }

    private void allEnemies() {
        selectedTargets.addAll(targets);
    }

    private void allEnemiesWithPrimary(Fighter target) {
        selectedTargets.addAll(targets.stream().filter(t -> t.getId() != target.getId()).collect(Collectors.toCollection(GameList::new)));
        selectedTargets.add(0, target);
    }

    private void addRandomTarget() {
        selectedTargets.add(targets.random());
    }

    private void addTargetOrAddFirstValid(Fighter target) {
        selectedTargets.add(target.canBeTarget() ? target : targets.get(0));
    }
}
