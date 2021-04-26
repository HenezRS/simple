package com.henez.simple.menu;

import com.henez.simple.data.NewGameData;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.menu.buttons.ButtonFactory;
import com.henez.simple.menu.buttons.ButtonGroup;
import com.henez.simple.menu.buttons.TextButton;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.stats.classes.ClassName;
import com.henez.simple.text.Text;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class MenuTitle {
    private NewGameData newGameData;
    private ButtonGroup characterButtons;
    private TextButton resetButton;
    private TextButton confirmButton;
    private TextButton defaultButton;
    private GameList<ClassName> classes;
    private boolean done = false;

    public MenuTitle() {
        newGameData = new NewGameData();
        characterButtons = ButtonFactory.createCharacterSelectButtons();
        resetButton = new TextButton("reset", 30, 240);
        confirmButton = new TextButton("confirm", 30 + 50, 240);
        defaultButton = new TextButton("default and confirm", 30, 240 + Text.TEXT_PARAGRAPH_H);
        classes = new GameList<>();
    }

    public void update() {
        characterButtons.update();
        characterButtons.getClickedButton().ifPresent(button -> {
            if (classes.size() < 4) {
                classes.add(ClassName.getByName(button.getName()));
            }
        });

        resetButton.update();
        if (resetButton.isClicked()) {
            classes.clear();
        }
        confirmButton.update();
        if (confirmButton.isClicked() && !classes.isEmpty()) {
            confirm();
        }
        defaultButton.update();
        if (defaultButton.isClicked()) {
            confirmDefault();
        }
    }

    public void draw(Batcher batch, Shaper shape) {
        characterButtons.draw(batch);
        resetButton.draw(batch);
        confirmButton.draw(batch);
        defaultButton.draw(batch);

        AtomicInteger i = new AtomicInteger();
        classes.forEach(className -> {
            batch.draw(className.getImgSet().getIdle().getTex(), 140 - (20 * i.getAndIncrement()), 100);
        });
    }

    private void confirm() {
        newGameData.setClasses(classes);
        done = true;
    }

    private void confirmDefault() {
        classes.clear();
        classes.add(ClassName.kni);
        confirm();
    }
}
