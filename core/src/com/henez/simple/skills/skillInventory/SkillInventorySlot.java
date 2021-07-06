package com.henez.simple.skills.skillInventory;

import com.henez.simple.menu.buttons.SkillButton;
import com.henez.simple.skills.SkillName;
import com.henez.simple.utils.SkillInventoryUtils;
import lombok.Getter;

import java.util.Optional;

@Getter
public class SkillInventorySlot {
    private int pos;
    private SkillButton button;

    public SkillInventorySlot(int pos) {
        this.pos = pos;
        button = new SkillButton(SkillInventoryUtils.getSlotX(this), SkillInventoryUtils.getSlotY(this), null);
    }

    public void update() {
        button.update();
    }

    public Optional<SkillName> getSkill() {
        return Optional.ofNullable(button.getSkillName());
    }

    public void setSkill(SkillName skillName) {
        button.updateSkill(skillName);
    }

    public void removeSkill() {
        button.removeSkill();
    }

    public boolean isEmpty() {
        return getSkill().isEmpty();
    }
}
