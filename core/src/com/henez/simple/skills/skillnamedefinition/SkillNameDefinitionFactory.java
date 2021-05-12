package com.henez.simple.skills.skillnamedefinition;

import com.henez.simple.atlas.imgs.ImgIconSkills;

public final class SkillNameDefinitionFactory {

    public static SkillNameDefinition createDoNothing() {
        return new SkillNameDefinition.Builder("do nothing", ImgIconSkills.missing)
                .build();
    }

    public static SkillNameDefinition createAttack() {
        return new SkillNameDefinition.Builder("attack", ImgIconSkills.attack)
                .withPower(1)
                .build();
    }

    public static SkillNameDefinition createFlame() {
        return new SkillNameDefinition.Builder("flame", ImgIconSkills.flame)
                .withPower(2)
                .withCast(2.0f)
                .build();
    }

    public static SkillNameDefinition createIceSpike() {
        return new SkillNameDefinition.Builder("ice spike", ImgIconSkills.ice_spike)
                .withPower(4)
                .withCast(4.0f)
                .build();
    }
}
