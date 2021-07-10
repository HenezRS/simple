package com.henez.simple.menu.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.SkillName;
import lombok.Getter;

@Getter
public class SkillButton extends Button {
    private int texAddX = 0;
    private int texAddY = 0;
    private SkillName skillName;
    private TextureRegion tex;
    private ImageButtonDefinitions group;

    public SkillButton(int x, int y, SkillName skillName) {
        super("skill", x, y);
        updateSkill(skillName);
        this.group = ImageButtonDefinitions.inv_slot;
        this.w = group.getBack().getRegionWidth();
        this.h = group.getBack().getRegionHeight();
    }

    public void updateSkill(SkillName skillName) {
        this.skillName = skillName;
        this.tex = skillName != null ? skillName.getTex() : null;
    }

    public void removeSkill() {
        skillName = null;
    }

    @Override
    public void draw(Batcher batch) {
        drawTo(batch, x, y);
    }

    public void drawTo(Batcher batch, int x, int y) {
        batch.drawToCamera(group.getBack(), x, y);
        if (hover) {
            batch.drawToCamera(group.getHover(), x, y);
        }

        if (tex != null) {
            batch.drawToCamera(tex, x + texAddX, y + texAddY);
        }

        if (clicked && group.getClicked() != null) {
            batch.drawToCamera(group.getClicked(), x, y);
        }
    }
}
