package com.henez.simple.skills.tactics;

import com.henez.simple.menu.buttons.SkillButton;
import com.henez.simple.menu.buttons.TacticButton;
import com.henez.simple.skills.SkillName;
import lombok.Getter;

@Getter
public class Tactic {
    private final int skillX = 154;
    private final int skillY = 77;
    private final int w = 24;
    private final int h = 20;

    private int pos;
    private boolean enabled;
    private SkillName skillName;
    private TacticOnName onName;
    private TacticIf tacticIf;

    private SkillButton skillButton;
    private TacticButton onButton;

    public Tactic(SkillName skillName) {
        enabled = true;
        this.skillName = skillName;
        this.tacticIf = new TacticIf();

        onName = TacticOnName.player_any;
        if (skillName.isTargetEnemies()) {
            onName = TacticOnName.enemy_any;
        }

        refreshButtons();
    }

    public void update() {
        skillButton.update();
        onButton.update();
    }

    private void refreshButtons() {
        skillButton = new SkillButton(0, 0, skillName);
        onButton = new TacticButton(0, 0, onName.getTex());
    }

    public void refreshPos(int pos) {
        this.pos = pos;

        skillButton.setPos(skillX, skillY + (pos * h));
        onButton.setPos(skillX + (1 * w), skillY + (pos * h));
    }
}
