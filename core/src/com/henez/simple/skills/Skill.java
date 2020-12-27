package com.henez.simple.skills;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.effect.EffectFactory;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public abstract class Skill {
    protected SkillName skillName;
    protected SkillTarget skillTarget;
    protected Fighter source;
    protected Fighter target;
    protected GameList<Fighter> targets;
    protected GameList<SkillStep> steps;
    protected boolean done = false;
    protected boolean firstUpdate = true;
    protected int state = 0;

    public Skill(SkillName skillName, SkillTarget skillTarget) {
        this.skillName = skillName;
        this.skillTarget = skillTarget;
        steps = new GameList<>();

        source = skillTarget.getSource();
        target = skillTarget.getTarget();
        targets = skillTarget.getTargets();
    }

    public boolean update() {
        if (firstUpdate) {
            if (steps.size() <= 0) {
                finish();
                targets.forEach(f -> EffectFactory.createCancel(f.getGx(), f.getGy()));
                return done;
            }
        }

        if (targetNoLongerValid()) {
            finish();
            return done;
        }

        SkillStep current = steps.get(state);

        if (firstUpdate) {
            current.init();
        }

        current.update();
        if (current.isDone()) {
            nextState();
        }
        firstUpdate = false;
        return done;
    }

    private boolean targetNoLongerValid() {
        return targets.stream().filter(Fighter::canBeTarget).count() < 1;
    }

    public void draw(Batcher batch) {
        if (!done) {
            steps.get(state).draw(batch);
        }
    }

    protected void nextState() {
        state++;
        if (state >= steps.size()) {
            finish();
        }
    }

    protected void finish() {
        done = true;
        firstUpdate = true;
    }
}
