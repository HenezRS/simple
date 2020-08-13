package com.henez.simple.enums;

import com.henez.simple.datastructures.GameList;

import java.util.Arrays;

public enum Pal {
    dark(Colors.pal_dark_0, Colors.pal_dark_1, Colors.pal_dark_2, Colors.pal_dark_3);

    public GameList<Colors> colors;

    Pal(Colors... colors) {
        this.colors = (GameList<Colors>) Arrays.asList(colors);
    }
}