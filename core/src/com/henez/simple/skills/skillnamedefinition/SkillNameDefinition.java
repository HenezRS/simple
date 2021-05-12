package com.henez.simple.skills.skillnamedefinition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.henez.simple.atlas.imgs.ImgIconSkills;
import com.henez.simple.global.Global;
import com.henez.simple.skills.SkillTargetName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillNameDefinition {

    public static class Builder {
        private String name;
        private TextureRegion tex;
        private float power = 0;
        private int cost = 0;
        private int castDelay = 0;
        private int channelDelay = 0;
        private int channelExecutionCount = 0;
        private SkillTargetName targetType = SkillTargetName.SINGLE;
        private boolean targetEnemies = true;

        public Builder(String name, ImgIconSkills icon) {
            this.name = name;
            this.tex = icon.asTex().getTex();
        }

        public Builder withPower(float power) {
            this.power = power;
            return this;
        }

        public Builder withCost(int cost) {
            this.cost = cost;
            return this;
        }

        public Builder withCast(float seconds) {
            this.castDelay = (int) (seconds * Global.SEC);
            return this;
        }

        public Builder withChannel(int channelDelay, int channelExecutionCount) {
            this.channelDelay = channelDelay;
            this.channelExecutionCount = channelExecutionCount;
            return this;
        }

        public Builder withTarget(SkillTargetName skillTargetName, boolean targetEnemies) {
            this.targetType = skillTargetName;
            this.targetEnemies = targetEnemies;
            return this;
        }

        public SkillNameDefinition build() {
            SkillNameDefinition skillNameDefinition = new SkillNameDefinition();
            skillNameDefinition.setName(name);
            skillNameDefinition.setTex(tex);
            skillNameDefinition.setPower(power);
            skillNameDefinition.setCost(cost);
            skillNameDefinition.setCastDelay(castDelay);
            skillNameDefinition.setChannelDelay(channelDelay);
            skillNameDefinition.setChannelExecutionCount(channelExecutionCount);
            skillNameDefinition.setTargetType(targetType);
            skillNameDefinition.setTargetEnemies(targetEnemies);
            return skillNameDefinition;
        }

    }

    private String name;
    private TextureRegion tex;
    private float power;
    private int cost;
    private int castDelay;
    private int channelDelay;
    private int channelExecutionCount;
    private SkillTargetName targetType;
    private boolean targetEnemies;

    private SkillNameDefinition() {
    }
}
