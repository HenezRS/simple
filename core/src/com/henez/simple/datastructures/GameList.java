package com.henez.simple.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    public void addAll(T... item) {
        this.addAll(Arrays.asList(item));
    }

    public T first() {
        return get(0);
    }

    public T last() {
        return get(size() - 1);
    }

    public void reverse() {
        Collections.reverse(this);
    }
}
