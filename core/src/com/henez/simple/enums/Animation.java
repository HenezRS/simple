package com.henez.simple.enums;

public enum Animation {
    none("none"),
    _default("default"),
    idle("idle"),
    move("move"),
    attack("attack"),
    ;

    public final String name;

    Animation(String name) {
        this.name = name;
    }
}
