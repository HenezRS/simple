package com.henez.simple.skills;

import com.henez.simple.renderer.Batcher;
import com.henez.simple.world.Fighter;
import lombok.Getter;

@Getter
public abstract class Skill {
    protected SkillName skillName;
    protected Fighter source;
    protected Fighter target;

    public Skill(SkillName skillName) {
        this.skillName = skillName;
    }

    public abstract boolean update();

    public abstract void draw(Batcher batch);

    public void giveFighters(Fighter source, Fighter target) {
        this.source = source;
        this.target = target;
    }
}
