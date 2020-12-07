package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgTiles;
import com.henez.simple.debug.Log;
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
    private boolean resetWhenDone = false;

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

    public void setCurrent(Animation animation) {
        if (!animations.containsKey(animation)) {
            Log.log("Missing animation " + animation.name + ". idle will be set instead.");
            animation = Animation.idle;
        }
        current = animation;
    }

    public void update() {
        animations.get(current).update();
        if (resetWhenDone && isDone()) {
            resetWhenDone = false;
            setAnimationAndReset(Animation.idle);
        }
    }

    public TextureRegion getTex() {
        return animations.get(current).getCurrent();
    }

    public void setAnimationAndReset(Animation animation) {
        setCurrent(animation);
        animations.get(current).reset();
    }

    public void setAnimationAndResetPlayOnce(Animation animation) {
        setCurrent(animation);
        resetWhenDone = true;
        animations.get(current).reset();
    }

    public void setAnimationAndContinue(Animation newAnimation) {
        SpriteAnimation lastAnimation = animations.get(current);
        setCurrent(newAnimation);
        animations.get(current).sync(lastAnimation);
    }

    public boolean isDone() {
        return animations.get(current).isDone();
    }

    public boolean isKeyFrameDone() {
        return animations.get(current).isKeyFrameDone();
    }

    public boolean isKeyFrameDoneThisFrame() {
        return animations.get(current).isKeyFrameDoneThisFrame();
    }
}
