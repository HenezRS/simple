package com.henez.simple.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mouse {
    //XY within the window
    private int x;
    private int y;

    //XY within the game world grid
    private int gx;
    private int gy;

    //XY within the game world
    private int wx;
    private int wy;
    private boolean clicked = false;
    private boolean clicked2 = false;
    private boolean held = false;
    private boolean held2 = false;
    private boolean released = false;
    private boolean released2 = false;

    public Mouse() {

    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosWorld(int wx, int wy) {
        this.wx = wx;
        this.wy = wy;
    }

    public void setPosGrid(int gx, int gy) {
        this.gx = gx;
        this.gy = gy;
    }
}
