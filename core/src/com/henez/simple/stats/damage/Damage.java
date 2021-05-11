package com.henez.simple.stats.damage;

import com.henez.simple.datastructures.Numbers;
import com.henez.simple.debug.DebugFlags;
import com.henez.simple.enums.StatName;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public class Damage {
    private Fighter source;
    private Fighter target;
    private SkillComponent skillComponent;
    private float dmg;
    private int dmgFinal;

    public Damage(Fighter source, Fighter target, SkillComponent skillComponent) {
        this.source = source;
        this.target = target;
        this.skillComponent = skillComponent;
        calc();
    }

    private void calc() {
        dmg = skillComponent.getSkillName().getPower();
        dmg *= source.isPlayer() ? DebugFlags.mulSkillNamePowerPlayer : DebugFlags.mulSkillNamePowerEnemy;
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