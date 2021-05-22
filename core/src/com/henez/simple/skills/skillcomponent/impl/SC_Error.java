package com.henez.simple.skills.skillcomponent.impl;

import com.henez.simple.skills.skillcomponent.SkillComponent;

public class SC_Error extends SkillComponent {

    @Override
    public void buildSteps() {
        System.out.println("Error: skill missing group. Check SkillName::createGroup");
    }
}
