package com.henez.simple.menu.buttons;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.renderer.Batcher;

import java.util.Optional;

public class ButtonGroup {
    private GameList<Button> buttons;

    public ButtonGroup() {
        this.buttons = new GameList<>();
    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }

    public void update() {
        buttons.forEach(Button::update);
    }

    public Optional<Button> getClickedButton() {
        return buttons.stream().filter(Button::isClicked).findFirst();
    }

    public void draw(Batcher batch) {
        buttons.forEach(button -> button.draw(batch));
    }
}
