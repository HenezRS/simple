package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.ImgUi;
import lombok.Getter;

@Getter
public enum ImageButtonDefinitions {
    box20(ImgUi.button.asTex(), ImgUi.button_hover.asTex(), ImgUi.button_active.asTex());

    private TextureRegion back;
    private TextureRegion hover;
    private TextureRegion clicked;

    ImageButtonDefinitions(TextureRegion back, TextureRegion hover, TextureRegion clicked) {
        this.back = back;
        this.hover = hover;
        this.clicked = clicked;
    }
}
