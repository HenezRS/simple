package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.ImgTiles;
import com.henez.simple.enums.Animation;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Sprite {
    private Map<Animation, SpriteAnimation> animations;
    private Animation current;

    public Sprite() {
        current = Animation.none;
        animations = new HashMap<>();
        animations.put(current, new SpriteAnimation(0, Atlas.toTex(ImgTiles.none)));
    }

    public Sprite(TextureRegion tex) {
        current = Animation.none;
        animations = new HashMap<>();
        animations.put(current, new SpriteAnimation(0, tex));
    }

    public void update() {
        animations.get(current).update();
    }

    public TextureRegion getTex() {
        return animations.get(current).getCurrent();
    }

    public void setAnimationAndReset(Animation animation) {
        setCurrent(animation);
        animations.get(animation).reset();
    }

    public void setAnimationAndContinue(Animation newAnimation) {
        SpriteAnimation lastAnimation = animations.get(current);
        setCurrent(newAnimation);
        animations.get(current).sync(lastAnimation);
    }
}
