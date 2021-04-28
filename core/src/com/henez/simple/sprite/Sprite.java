package com.henez.simple.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.Atlas;
import com.henez.simple.atlas.imgs.ImgTiles;
import com.henez.simple.datastructures.TextureRegionEnhanced;
import com.henez.simple.enums.Animation;
import com.henez.simple.enums.Facing;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.shaders.Shader;
import com.henez.simple.sprite.spriteeffects.SpriteEffectManager;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Sprite {
    private Map<Animation, SpriteAnimation> animations;
    private Animation current;
    private SpriteEffectManager spriteEffectManager;
    private boolean resetWhenDone = false;
    private Animation resetAnimationTarget = Animation.idle;

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

    private void determineCurrent() {
        current = Arrays.stream(Animation.values()).filter(animation -> hasAnimation(animation) && animations.get(animation).isInProgress()).findFirst().orElse(Animation.idle);
    }

    public void update() {
        determineCurrent();
        spriteEffectManager.update();
        animations.get(current).update();
        if (animations.get(current).isDonePlaying()) {
            determineCurrent();
        }
    }

    public void draw(Batcher batch, float x, float y, Facing facing) {
        TextureRegion tex = getTex();
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

    public boolean hasAnimation(Animation animation) {
        return animations.containsKey(animation);
    }

    public void stopAnimation(Animation animation) {
        if (hasAnimation(animation)) {
            animations.get(animation).reset();
        }
    }

    public void playAnimation(Animation animation) {
        if (hasAnimation(animation)) {
            animations.get(animation).resetAndPlay();
        }
    }

    public void playAnimationOnce(Animation animation) {
        if (hasAnimation(animation)) {
            animations.get(animation).resetAndPlayOnce();
        }
    }

    public void playAnimationSync(Animation newAnimation, Animation oldAnimation) {
        if (hasAnimation(newAnimation) && hasAnimation(oldAnimation)) {
            SpriteAnimation lastAnimation = animations.get(oldAnimation);
            animations.get(newAnimation).sync(lastAnimation);
            animations.get(newAnimation).play();
        }
    }

    public void setAnimationSpeedMul(Animation animation, float mul) {
        if (hasAnimation(animation)) {
            animations.get(animation).setSpeedMul(mul);
        }
    }

    public boolean isKeyFrameDoneThisFrame(Animation animation) {
        if (hasAnimation(animation)) {
            return animations.get(animation).isKeyFrameDoneThisFrame();
        }
        return true;
    }
}
