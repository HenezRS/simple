package com.henez.simple.world.enemies;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.stats.classes.ClassName;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum EnemyPartyName {
    octo(ClassName.enemy_octo),
    octos(ClassName.enemy_octo2, ClassName.enemy_octo2, ClassName.enemy_octo2, ClassName.enemy_octo),
    ;

    private GameList<ClassName> names;

    EnemyPartyName(ClassName... names) {
        this.names = Arrays.stream(names).filter(n -> !n.isMinor()).collect(Collectors.toCollection(GameList::new));
        this.names.addAll(Arrays.stream(names).filter(ClassName::isMinor).collect(Collectors.toCollection(GameList::new)));
    }

    public GameList<ClassName> getOrdered() {
        return names;
    }
}
