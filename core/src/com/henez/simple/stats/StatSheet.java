package com.henez.simple.stats;

import com.henez.simple.enums.StatName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StatSheet {
    Map<StatName, Stat> stats;
    StatLevel level;

    public StatSheet() {
        stats = new HashMap<>();
        level = new StatLevel(0);
    }
}
