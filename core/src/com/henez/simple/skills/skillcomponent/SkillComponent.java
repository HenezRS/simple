package com.henez.simple.skills.skillcomponent;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.effect.EffectFactory;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.skills.SkillName;
import com.henez.simple.skills.SkillTarget;
import com.henez.simple.skills.skillstep.SkillStep;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public abstract class SkillComponent {
    protected SkillName skillName;
    protected Fighter source;
    protected Fighter target;
    protected GameList<SkillStep> steps;
    protected boolean done = false;
    protected boolean firstUpdate = true;
    protected int state = 0;

    public SkillComponent() {
    }

    public void createSteps(SkillName skillName, Fighter source, Fighter target) {
        this.skillName = skillName;
        this.source = source;
        this.target = target;
        steps = new GameList<>();
        buildSteps();
    }

    public SkillComponent with(SkillName skillName, Fighter source, Fighter target) {
        this.skillName = skillName;
        this.source = source;
        this.target = target;
        steps = new GameList<>();
        buildSteps();
        return this;
    }

    public abstract void buildSteps();

    public boolean update() {
        if (firstUpdate) {
            if (steps.size() <= 0) {
                finish();
                //targets.forEach(f -> EffectFactory.createCancel(f.getGx(), f.getGy()));
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

    public boolean targetNoLongerValid() {
        return !target.canBeTarget();
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
