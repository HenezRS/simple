package com.henez.simple.input;

import lombok.Getter;

@Getter
public class Key {
    private String name;
    private boolean pressed;
    private boolean held;
    private boolean released;

    public Key(String name) {

        this.name = name;
    }

    public void set(boolean pressed, boolean held, boolean released) {
        this.pressed = pressed;
        this.held = held;
        this.released = released;
    }
}
