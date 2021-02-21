package com.henez.simple.menu.buttons;

import com.henez.simple.input.In;
import com.henez.simple.renderer.Batcher;
import lombok.Getter;

@Getter
public abstract class Button {
    protected String name;
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected boolean hover;
    protected boolean clicked;
    protected boolean held;

    public Button(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void update() {
        setState();
    }

    abstract void draw(Batcher batch);

    private void setState() {
        hover = In.mouse.isMouseWithinInclusive(x, y, w - 1, h - 1);
        clicked = hover && In.mouse.isClicked();
        held = hover && In.mouse.isHeld();
    }
}
