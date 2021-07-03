package com.henez.simple.skills;

import com.henez.simple.datastructures.GameList;
import lombok.Getter;

@Getter
public class SkillSheet {
    private GameList<SkillName> skills;

    public SkillSheet() {
        skills = new GameList<>();
        skills.addAll(SkillName.ATTACK, SkillName.FLAME, SkillName.ICE_SPIKE, SkillName.FLAME_4);
    }

    public boolean hasValidSkillInSlotIndex(int i) {
        return skills.getOrNull(i) != null;
    }
}
