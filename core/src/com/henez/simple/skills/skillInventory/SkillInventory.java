package com.henez.simple.skills.skillInventory;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.skills.SkillName;
import lombok.Getter;

@Getter
public class SkillInventory {
    private final static int SKILL_SLOTS = 20;
    private GameList<SkillInventorySlot> slots;

    public SkillInventory() {
        slots = new GameList<>();
        for (int i = 0; i < SKILL_SLOTS; ++i) {
            slots.add(new SkillInventorySlot(i));
        }

        addSkill(SkillName.ATTACK);
        addSkill(SkillName.ICE_SPIKE);
    }

    public void update() {
        slots.forEach(SkillInventorySlot::update);
    }

    public void addSkill(SkillName skillName) {
        slots.stream().filter(SkillInventorySlot::isEmpty).findFirst().ifPresent(slot -> slot.setSkill(skillName));
    }
}
