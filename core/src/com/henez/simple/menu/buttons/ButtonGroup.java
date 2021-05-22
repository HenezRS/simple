package com.henez.simple.menu.buttons;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.renderer.Batcher;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ButtonGroup {
    private GameList<Button> buttons;
    private Button firstHovered;

    public ButtonGroup() {
        this.buttons = new GameList<>();
    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }

    public void update() {
        buttons.forEach(Button::update);
        firstHovered = buttons.stream().filter(Button::isHover).findFirst().orElse(null);
    }

    public Optional<Button> getClickedButton() {
        return buttons.stream().filter(Button::isClicked).findFirst();
    }

    public void draw(Batcher batch) {
        buttons.forEach(button -> button.draw(batch));
    }

    public Button getByName(String name) {
        return buttons.stream().filter(b -> b.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Optional<Button> getFirstHovered() {
        return Optional.ofNullable(firstHovered);
    }
}
