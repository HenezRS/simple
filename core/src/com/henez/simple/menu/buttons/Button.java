package com.henez.simple.menu.buttons;

import com.henez.simple.input.In;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
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
    protected boolean isActive = false;

    public Button(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void update() {
        setState();
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Batcher batch);

    public void draw(Shaper shape) {

    }

    private void setState() {
        hover = In.mouse.isMouseWithinInclusive(x, y, w - 1, h - 1);
        clicked = hover && In.mouse.isClicked();
        held = hover && In.mouse.isHeld();
    }

    public void setActive() {
        isActive = true;
    }

    public void setInactive() {
        isActive = false;
    }
}
