package com.henez.simple.datastructures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextureRegionEnhanced {
    private TextureRegion tex;
    private float alpha = 1.0f;

    public TextureRegionEnhanced(TextureRegion tex) {
        this.tex = tex;
    }
}
