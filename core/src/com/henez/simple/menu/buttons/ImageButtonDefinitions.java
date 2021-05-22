package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.ImgUi;
import lombok.Getter;

import static com.henez.simple.atlas.imgs.ImgUi.inv_slot;

@Getter
public enum ImageButtonDefinitions {
    box20(ImgUi.button.asTex(), ImgUi.button_hover.asTex(), ImgUi.button_active.asTex()),
    inv_slot(ImgUi.inv_slot.asTex(), ImgUi.inv_slot_hover.asTex(), null);

    private TextureRegion back;
    private TextureRegion hover;
    private TextureRegion clicked;

    ImageButtonDefinitions(TextureRegion back, TextureRegion hover, TextureRegion clicked) {
        this.back = back;
        this.hover = hover;
        this.clicked = clicked;
    }
}
