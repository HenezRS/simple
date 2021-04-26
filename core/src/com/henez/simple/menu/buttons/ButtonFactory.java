package com.henez.simple.menu.buttons;

import com.henez.simple.stats.classes.ClassName;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public final class ButtonFactory {
    private ButtonFactory() {
    }

    public static ButtonGroup createCharacterSelectButtons() {
        final int parentX = 30;
        final int parentY = 30;

        ButtonGroup buttonGroup = new ButtonGroup();
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        Arrays.stream(ClassName.values()).forEach(job -> {
            buttonGroup.addButton(new ImageButton(job.getName(), parentX + (x.getAndIncrement() * 22), parentY + (y.get() * 22), job.getImgSet().getIdle().getTex(), ImageButtonDefinitions.box20));
            if (x.get() == 5) {
                y.getAndIncrement();
                x.set(0);
            }
        });
        return buttonGroup;
    }
}
