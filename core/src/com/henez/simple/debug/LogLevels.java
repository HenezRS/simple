package com.henez.simple.debug;

public enum LogLevels {
    info(0),
    warn(1),
    error(2),
    none(3),
    ;

    public final int level;

    LogLevels(int level) {
        this.level = level;
    }
}
