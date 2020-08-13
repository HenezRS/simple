package com.henez.simple.debug;

import lombok.Setter;

@Setter
public class Log {
    private LogLevels logLevel;

    public Log(LogLevels logLevel) {
        this.logLevel = logLevel;
    }

    public void info(String message) {
        if (logLevel.level >= LogLevels.info.level) {
            System.out.println(message);
        }
    }

    public void warn(String message) {
        if (logLevel.level >= LogLevels.warn.level) {
            System.out.println(message);
        }
    }

    public void error(String message) {
        if (logLevel.level >= LogLevels.error.level) {
            System.out.println(message);
        }
    }
}
