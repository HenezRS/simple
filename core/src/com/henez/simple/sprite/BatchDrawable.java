package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.datastructures.Numbers;
import com.henez.simple.enums.Facing;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchDrawable {
    private int x;
    private int y;
    private TextureRegion tex;
    private Facing facing = Facing.RIGHT;

    public BatchDrawable(float x, float y, TextureRegion tex) {
        this.x = Numbers.round(x);
        this.y = Numbers.round(y);
        this.tex = tex;
    }

    public BatchDrawable(float x, float y, TextureRegion tex, Facing facing) {
        this.x = Numbers.round(x);
        this.y = Numbers.round(y);
        this.tex = tex;
        this.facing = facing;
    }
}
