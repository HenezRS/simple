package com.henez.simple.enums;

public enum StatName {
    HP("HP", 12.258f),
    MP("MP", 3.84f),
    STR("STR", 1),
    DEF("DEF", 1),
    MAG("MAG", 1),
    RES("RES", 1),
    SPD("SPD", 1),
    ;

    private String name;
    private float mul;

    StatName(String name, float mul) {
        this.name = name;
        this.mul = mul;
    }

    public String getName() {
        return name;
    }

    public float getMul() {
        return mul;
    }
}
