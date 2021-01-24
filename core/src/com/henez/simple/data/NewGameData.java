package com.henez.simple.data;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.stats.classes.ClassName;

public class NewGameData {
    private GameList<ClassName> classNames;

    public NewGameData() {
        classNames = new GameList<>();
    }

    public void addClassName(ClassName className) {
        classNames.add(className);
    }

    public void clear() {
        classNames = new GameList<>();
    }

    public boolean isValid() {
        return classNames.size() > 0;
    }
}
