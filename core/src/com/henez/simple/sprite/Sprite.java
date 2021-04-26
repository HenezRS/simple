package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgTiles;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import com.henez.simple.debug.Log;
import com.henez.simple.enums.Animation;
import com.henez.simple.enums.Facing;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.shaders.Shader;
import com.henez.simple.sprite.spriteeffects.SpriteEffectManager;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Sprite {
    private Map<Animation, SpriteAnimation> animations;
    private Animation current;
    private SpriteEffectManager spriteEffectManager;
    private boolean resetWhenDone = false;

    public Sprite() {
        current = Animation.none;
        animations = new HashMap<>();
        animations.put(current, new SpriteAnimation(0, Atlas.toTex(ImgTiles.none)));
        spriteEffectManager = new SpriteEffectManager();
    }

    public Sprite(TextureRegionEnhanced tex) {
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
        spriteEffectManager.update();
        animations.get(current).update();
        if (resetWhenDone && isDone()) {
            resetWhenDone = false;
            setAnimationAndReset(Animation.idle);
        }
    }

    public void draw(Batcher batch, float x, float y, Facing facing, boolean dead) {
        TextureRegion tex = getTex();
        if (dead) {
            tex = ImgTiles.grave.asTex().getTex();
        }

        Shader.sprite.shader.begin();
        Shader.sprite.shader.setUniformf("blinkAlpha", 0.0f);
        spriteEffectManager.applyShaderUniforms();
        Shader.sprite.shader.end();
        batch.begin();
        batch.draw(tex, x, y, facing);
        batch.end();
    }

    public TextureRegion getTex() {
        return animations.get(current).getCurrent().getTex();
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

    public void setAnimationSpeedMul(float mul) {
        animations.get(current).setSpeedMul(mul);
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
