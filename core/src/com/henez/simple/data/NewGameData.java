package com.henez.simple.data;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.stats.classes.ClassName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewGameData {
    private GameList<ClassName> classes;

    public NewGameData() {
    }
}
