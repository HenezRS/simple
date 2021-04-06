package com.henez.simple.skills.skillgroup.impl;

import com.henez.simple.misc.timer.Timer;
import com.henez.simple.skills.SkillComponentName;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillcomponent.SkillComponent;
import com.henez.simple.skills.skillgroup.SkillGroup;

public class SG_All extends SkillGroup {

    private Timer timer;
    private int executionCount = 0;

    public SG_All(SkillName skillName, SkillTarget skillTarget, SkillComponentName skillComponentName, int delay) {
        super(skillName, skillTarget);
        init(skillComponentName, delay);
    }

    public SG_All(SkillName skillName, SkillTarget skillTarget, SkillComponentName skillComponentName, int delay, float speedMulAnimation, float speedMulEffect) {
        super(skillName, skillTarget);
        this.speedMulAnimation = speedMulAnimation;
        this.speedMulEffect = speedMulEffect;
        init(skillComponentName, delay);
    }

    private void init(SkillComponentName skillComponentName, int delay) {
        targets.forEach(target -> {
            skillComponentsWaiting.add(skillComponentName.create().with(skillName, source, target,speedMulAnimation,speedMulEffect));
        });
        executionCount = skillComponentsWaiting.size();
        timer = new Timer(delay);
    }

    @Override
    public boolean update() {
        skillComponents.forEach(SkillComponent::update);
        done = executionCount == skillComponents.size() && skillComponents.stream().allMatch(SkillComponent::isDone);
        if (!skillComponentsWaiting.isEmpty() && timer.update()) {
            timer.reset();
            skillComponents.add(skillComponentsWaiting.first());
            skillComponentsWaiting.removeFirst();

        }
        return done;
    }
}
