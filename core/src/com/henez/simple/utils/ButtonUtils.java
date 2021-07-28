package com.henez.simple.utils;

import com.henez.simple.menu.buttons.Button;

public final class ButtonUtils {
    public static boolean areSame(Button b, Button bb) {
        return b != null && bb != null && b.getId() == bb.getId();
    }

    public static boolean areSameName(Button b, Button bb) {
        return b != null && bb != null && b.getName().equals(bb.getName());
    }
}
