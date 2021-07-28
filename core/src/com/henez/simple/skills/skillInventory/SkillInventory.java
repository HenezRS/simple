package com.henez.simple.skills.skillInventory;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.tactics.TacticInventory;
import lombok.Getter;

@Getter
public class SkillInventory {
    private final static int SKILL_SLOTS = 20;
    private GameList<SkillInventorySlot> slots;
    private TacticInventory tacticInventory;

    public SkillInventory() {
        tacticInventory = new TacticInventory();

        slots = new GameList<>();
        for (int i = 0; i < SKILL_SLOTS; ++i) {
            slots.add(new SkillInventorySlot(i));
        }

        addSkill(SkillName.ATTACK);
        addSkill(SkillName.ICE_SPIKE);
    }

    public void update() {
        slots.forEach(SkillInventorySlot::update);
        slots.stream().filter(slot -> slot.getButton().isClicked() && slot.getSkill().isPresent()).findFirst().ifPresent(slot -> {
            if (tacticInventory.selectedButtonIsSkill()) {
                tacticInventory.setSelectedSkill(slot.getSkill().get());
            } else {
                tacticInventory.addNewTactic(slot.getSkill().get());
            }
        });
        tacticInventory.update();
    }

    public void addSkill(SkillName skillName) {
        slots.stream().filter(SkillInventorySlot::isEmpty).findFirst().ifPresent(slot -> slot.setSkill(skillName));
    }
}
