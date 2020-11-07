package com.henez.simple.stats;

import com.henez.simple.datastructures.Numbers;
import lombok.Getter;

@Getter
public enum StatGrade {
    F("F", 3, 0.3f),
    E("E", 4, 0.43f),
    D("D", 4, 0.56f),
    C("C", 5, 0.7f),
    B("B", 5, 0.835f),
    A("A", 6, 0.98f),
    AA("AA", 6, 1.135f),
    AAA("AAA", 7, 1.245f),
    S("S", 8, 1.325f),
    XP("XP", 10, 3.85f),
    ;

    private final String name;
    private final float base;
    private final float add;

    public static float endAdd = 0;

    StatGrade(String name, float base, float add) {
        this.name = name;
        this.base = base;
        this.add = add;
    }

    public static StatGrade getRandom() {
        return StatGrade.values()[Numbers.nextIntBetween(0, 8)];
    }

    public static int getMaxAtLevel(StatGrade grade, int level, float multiplyOutcome) {
        float base = grade.getBase();
        float add = grade.getAdd();

        float max = base;
        for (int i = 1; i < level; ++i) {
            max += add;
            add *= 1.006f;
        }
        endAdd = add;
        return (int) (max * multiplyOutcome);
    }

    public static String getMaxAtLevelFormatted(StatGrade grade, int level, float multiplyOutcome) {
        int max = getMaxAtLevel(grade, level, multiplyOutcome);
        return String.format("[%s] - Lv %s: %s --- (add: %.2f) --- (mul: %s)", grade.getName(), level, max, endAdd, multiplyOutcome);
    }

}
