package com.henez.simple.stats;

public class StatLevel {
    Stat xp;
    Stat level;

    public StatLevel(int curLevel) {
        xp = new Stat(0, 100);
        level = new Stat(curLevel, 99);
    }

    public void giveXp(int xpAdd) {
        xp.restore(xpAdd);
    }

    public int getLevel() {
        return level.get();
    }

    public float getXpPercent() {
        return xp.getPercent();
    }
}
