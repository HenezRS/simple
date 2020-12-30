package com.henez.simple.enums;

public enum Animation {
    none("none"),
    _default("default"),
    idle("idle"),
    move("move"),
    attack("attack"),
    attack_fast("attack fast"),
    ;

    public final String name;

    Animation(String name) {
        this.name = name;
    }
}
