package com.henez.simple.stats;

import com.henez.simple.enums.StatName;
import com.henez.simple.stats.damage.Damage;
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

    public void resetForBattle(int pos, int fighterCount) {
        atb.reset(pos, fighterCount);
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

    public void applyDamage(Damage damage) {
        stats.get(StatName.HP).deduct(damage.getDmgFinal());
    }

    public boolean isDead() {
        return stats.get(StatName.HP).isEmpty();
    }

    public int getStatCur(StatName statName) {
        return stats.get(statName).get();
    }

    public float getStatPercent(StatName statName) {
        return stats.get(statName).getPercent();
    }
}
