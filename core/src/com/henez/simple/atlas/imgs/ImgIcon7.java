package com.henez.simple.atlas.imgs;

import lombok.Getter;

@Getter
public enum ImgIcon7 {
    none,
    ;

    private final int x;
    private final int y;

    ImgIcon7() {
        this.x = this.ordinal() % 36;
        this.y = this.ordinal() / 36;
    }
}
