package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.Static;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.enums.Colors;
import com.henez.simple.enums.Facing;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public class PlayerCardButton extends Button {
    private Fighter fighter;
    private Rect rect, rectPicture;

    private Color textColorDefault = Colors.text_faded.color;
    private Color textColorHover = Colors.text_default.color;
    private Color textColorActive = Colors.text_default.color;
    private Color borderColorDefault = Colors.ui_frame_darker.color;
    private Color borderColorActive = Colors.ui_frame.color;
    private Color borderColorHover = Colors.ui_bar_front.color;

    public PlayerCardButton(int x, int y, Fighter fighter) {
        super("", x, y);
        this.w = 87;
        this.h = 33;
        this.fighter = fighter;

        rect = new Rect(x,y,w,h);
        rectPicture = new Rect(x+3,y+10,20,20);
    }

    @Override
    public void draw(Batcher batch) {
        drawTo(batch,x,y);
    }

    public void draw(Shaper shape) {
        Color borderColor = borderColorDefault;
        if (hover) {
            borderColor = borderColorHover;
        } else if (isActive){
            borderColor = borderColorActive;
        }

        shape.rectOutlineCornered(rect,borderColor);
        shape.rectOutlineCornered(rectPicture,borderColor);
    }

    public void drawTo(Batcher batch, int x, int y) {
        Color textColor = textColorDefault;
        if (hover) {
            textColor = textColorHover;
        } else if (isActive){
            textColor = textColorActive;
        }
        Static.text.drawToCamera(batch,fighter.getName(), x+3,y+3);
        batch.drawToCamera(fighter.getSprite().getTex().getTex(), x+5, y+12, Facing.RIGHT);
    }
}
