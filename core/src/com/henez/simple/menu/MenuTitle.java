package com.henez.simple.menu;

import com.henez.simple.data.NewGameData;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.menu.buttons.ButtonFactory;
import com.henez.simple.menu.buttons.ButtonGroup;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.stats.classes.ClassName;

import java.util.concurrent.atomic.AtomicInteger;

public class MenuTitle {
    private NewGameData newGameData;
    private ButtonGroup characterButtons;
    private GameList<ClassName> classes;

    public MenuTitle() {
        newGameData = new NewGameData();
        characterButtons = ButtonFactory.createCharacterSelectButtons();
        classes = new GameList<>();
    }

    public void update() {
        characterButtons.update();
        characterButtons.getClickedButton().ifPresent(button -> {
            if (classes.size() < 4) {
                classes.add(ClassName.getByName(button.getName()));
            }
        });
    }

    public void draw(Batcher batch, Shaper shape) {
        characterButtons.draw(batch);

        AtomicInteger i = new AtomicInteger();
        classes.forEach(className -> {
            batch.draw(className.getImgSet().getIdle(), 40 + (20 * i.getAndIncrement()), 100);
        });
    }

    public void resetClasses() {
        classes = new GameList<>();
    }
}
