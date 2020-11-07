package com.henez.simple.skills;

import lombok.Getter;

@Getter
public enum SkillName {
    ATTACK("attack", 1, 0),
    ;

    private String name;
    private int power;
    private int cost;

    SkillName(String name, int power, int cost) {
        this.name = name;
        this.power = power;
        this.cost = cost;
    }
}
