package com.henez.simple.menu.buttons;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.stats.classes.ClassName;
import com.henez.simple.world.mapobjects.Fighter;

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

    public static ButtonGroup createPlayerMenuTabs() {
        final int parentX = 35;
        final int parentY = 47;
        final int parentWW = 48;
        int i = 0;
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.addButton(new TabButton("gear", parentX + (i++ * parentWW), parentY));
        buttonGroup.addButton(new TabButton("skills", parentX + (i++ * parentWW), parentY));
        buttonGroup.addButton(new TabButton("tree", parentX + (i++ * parentWW), parentY));
        return buttonGroup;
    }

    public static ButtonGroup createPlayerMenuCards(GameList<Fighter> fighters) {
        final int parentX = 36;
        final int parentY = 11;
        final int parentWW = 90;
        AtomicInteger i = new AtomicInteger();

        ButtonGroup buttonGroup = new ButtonGroup();
        fighters.forEach(fighter -> {
            buttonGroup.addButton(new PlayerCardButton(parentX + (i.getAndIncrement() * parentWW), parentY, fighter));
        });
        return buttonGroup;
    }
}
