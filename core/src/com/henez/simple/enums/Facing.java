package com.henez.simple.enums;

public enum Facing {
    RIGHT(0, 1, 0),
    UP(1, 0, -1),
    LEFT(2, -1, 0),
    DOWN(3, 0, 1),
    ;

    public final int dir;
    public final int tx;
    public final int ty;

    Facing(int dir, int tx, int ty) {
        this.dir = dir;
        this.tx = tx;
        this.ty = ty;
    }

    public Facing getNextRight() {
        switch (dir) {
        case 0:
            return Facing.DOWN;
        case 1:
            return Facing.RIGHT;
        case 2:
            return Facing.UP;
        default:
            return Facing.LEFT;
        }
    }

    public Facing getNextLeft() {
        switch (dir) {
        case 0:
            return Facing.UP;
        case 1:
            return Facing.LEFT;
        case 2:
            return Facing.DOWN;
        default:
            return Facing.RIGHT;
        }
    }

    public Facing getOpposite() {
        switch (dir) {
        case 0:
            return Facing.LEFT;
        case 1:
            return Facing.DOWN;
        case 2:
            return Facing.RIGHT;
        default:
            return Facing.UP;
        }
    }

    public static Facing byDir2(int x, int xx) {
        return x <= xx ? Facing.RIGHT : Facing.LEFT;
    }

    public static Facing actorDefault() {
        return Facing.RIGHT;
    }
}
