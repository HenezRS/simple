package com.henez.simple.stats;

import com.henez.simple.enums.StatName;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StatSheet {
    Map<StatName, Stat> stats;
    StatLevel level;
    StatAtb atb;

    public StatSheet() {
        level = new StatLevel(1);
        atb = new StatAtb();
        createAndDefaultStats();
    }

    private void createAndDefaultStats() {
        stats = new HashMap<>();
        Arrays.stream(StatName.values()).forEach(stat -> createStatBasic(stat, StatGrade.getRandom()));
    }

    private void createStatBasic(StatName stat, StatGrade curve) {
        stats.put(stat, new Stat(StatGrade.getMaxAtLevel(curve, level.getLevel(), stat.getMul())));
    }

    public void resetForBattle() {
        atb.reset();
    }

    public void turnEnd() {
        atb.spend();
    }

    public void tickAtb() {
        atb.update();
    }

    public boolean readyToAct() {
        return atb.isReady();
    }
}
