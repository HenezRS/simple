package com.henez.simple.stats.damage;

import com.henez.simple.datastructures.Numbers;
import com.henez.simple.enums.StatName;
import com.henez.simple.skills.Skill;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public class Damage {
    private Fighter source;
    private Fighter target;
    private Skill skill;
    private float dmg;
    private int dmgFinal;

    public Damage(Fighter source, Fighter target, Skill skill) {
        this.source = source;
        this.target = target;
        this.skill = skill;
        calc();
    }

    private void calc() {
        dmg = skill.getSkillName().getPower();
        dmg *= 6; //wep
        dmg *= 1 + ((float) this.source.getStatSheet().getStatCur(StatName.STR) / 10.0f);
        //dmg *= 1 - ((float) this.target.getStatSheet().getStatCur(StatName.DEF) / 10.0f);
        rollRange();
        dmgFinal = (int) dmg;
    }

    private void rollRange() {
        dmg *= Numbers.nextFloatBetween(0.85f, 1.15f);
    }

    public int getDmgFinal() {
        return dmgFinal;
    }
}