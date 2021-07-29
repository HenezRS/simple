package com.henez.simple.atlas.imgs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import lombok.Getter;

@Getter
public enum ImgUi {
    button(0, 0, 20, 20),
    button_active(20, 0, 20, 20),
    button_hover(40, 0, 20, 20),
    arrow_right(60, 0, 15, 8),
    arrow_right_grey(60, 8, 15, 8),
    bag(75, 0, 139, 54),
    tab_inactive(0, 20, 49, 11),
    tab_inactive_hover(0, 31, 49, 11),
    tab_active(0, 42, 51, 16),
    tab_active_hover(0, 58, 51, 16),
    exit(49, 20, 11, 11),
    exit_hover(60, 20, 11, 11),
    inv_slot(51, 32, 16, 16),
    inv_slot_hover(51, 48, 16, 16),
    use_on_if_is(67, 54, 82, 5),
    tactic(67, 59, 118, 16),
    but_tactic_option(0, 75, 168, 16),
    but_tactic_option_select(0, 91, 168, 16),
    but_tactic_option_hover(0, 107, 168, 16),
    exit_10(0, 123, 10, 10),
    exit_10_hover(10, 123, 10, 10),
    but_radio(20, 123, 10, 10),
    but_radio_on(30, 123, 10, 10),
    wide_slot(214, 0, 32, 16),
    wide_slot_hover(214, 16, 32, 16),
    ;

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    ImgUi(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
