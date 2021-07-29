package com.henez.simple.skills.tactics;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum TacticIsName {
    always,
    selectValues(true, false, false),
    selectValuesPercent(false, true, false),
    selectBuffs(false, false, true),
    selectBuffsValues(true, false, true);

    private boolean pickValues;
    private boolean pickValuesPercent;
    private boolean pickBuffs;

    public static List<String> numbers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    public static List<String> numbersPercent = Arrays.asList("10", "20", "30", "40", "50", "60", "70", "80", "90", "100");
    public static List<String> symbols = Arrays.asList("=", ">", ">=", "<", "<=");
    public static List<String> buffs = Arrays.asList("buff 1", "buff 2", "buff 3", "buff 4", "buff 5");

    TacticIsName() {
        pickValues = false;
        pickValuesPercent = false;
        pickBuffs = false;
    }

    TacticIsName(boolean pickValues, boolean pickValuesPercent, boolean pickBuffs) {
        this.pickValues = pickValues;
        this.pickValuesPercent = pickValuesPercent;
        this.pickBuffs = pickBuffs;
    }
}
