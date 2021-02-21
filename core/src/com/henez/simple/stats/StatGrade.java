package com.henez.simple.stats;

import com.henez.simple.datastructures.Numbers;
import lombok.Getter;

@Getter
public enum StatGrade {
    F("F", 3, 0.335f),
    E("E", 4, 0.365f),
    EE("EE", 4, 0.43f),
    D("D", 4, 0.49f),
    DD("DD", 4, 0.56f),
    C("C", 5, 0.63f),
    CC("CC", 5, 0.72f),
    B("B", 5, 0.815f),
    BB("BB", 5, 0.905f),
    A("A", 6, 0.98f),
    AA("AA", 6, 1.085f),
    S("S", 7, 1.165f),
    SS("SS", 7, 1.245f),
    SSS("SSS", 8, 1.285f),
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
        return StatGrade.values()[Numbers.nextIntBetween(0, StatGrade.values().length - 1)];
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
