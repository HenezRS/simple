package com.henez.simple.datastructures;

import java.util.ArrayList;

public class GameList<T> extends ArrayList<T> {

    public T random() {
        if (size() == 0)
            return null;
        return get(Numbers.nextIntBetween(0, size() - 1));
    }

    public T getOrNull(int i) {
        try {
            return get(i);
        } catch (IndexOutOfBoundsException ignored) {
            return null;
        }
    }

    public T first() {
        return get(0);
    }

    public T last() {
        return get(size() - 1);
    }
}
