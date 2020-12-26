package com.henez.simple.skills.impl;

import com.henez.simple.skills.Skill;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;

public class S_Error extends Skill {

    public S_Error(SkillName skillName, SkillTarget skillTarget) {
        super(skillName, skillTarget);
        System.out.println("Error: skill missing cast");
    }
}
