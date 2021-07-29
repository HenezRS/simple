package com.henez.simple.skills.tactics;

import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.menu.buttons.BasicButton;
import com.henez.simple.menu.buttons.SkillButton;
import com.henez.simple.menu.buttons.TacticButton;
import com.henez.simple.menu.buttons.TacticWideButton;
import com.henez.simple.skills.SkillName;
import lombok.Getter;

@Getter
public class Tactic {
    private final int skillX = 146;
    private final int skillY = 77;
    private final int w = 24;
    private final int h = 20;

    private int pos;
    private boolean enabled;
    private SkillName skillName;
    private TacticOnName onName;
    private TacticIf tacticIf;
    private TacticIs tacticIs;

    private SkillButton skillButton;
    private TacticButton onButton;
    private TacticButton ifButton;
    private TacticWideButton isButton;
    private BasicButton exitButton;

    public Tactic(SkillName skillName) {
        enabled = true;
        this.tacticIf = new TacticIf();
        setSkillName(skillName);
        onName = TacticOnName.player_any;
        if (skillName.isTargetEnemies()) {
            onName = TacticOnName.enemy_any;
        }
    }

    public void setSkillName(SkillName skillName) {
        this.skillName = skillName;
    }

    public void update() {
        skillButton.update();
        onButton.update();
        ifButton.update();
        exitButton.update();
    }

    public void refreshButtons() {
        skillButton = new SkillButton(0, 0, skillName).withName("skill_" + pos);
        onButton = new TacticButton("on_" + pos, 0, 0, onName.getTex());
        ifButton = new TacticButton("if_" + pos, 0, 0, tacticIf.getTacticIfName().getTex());
        ifButton.setTex2(tacticIf.getTacticIfRegardingName().getTex());
        isButton = new TacticWideButton("is_" + pos, 0, 0, onName.getTex());
        exitButton = new BasicButton(0, 0, ImgUi.exit_10.asTex(), ImgUi.exit_10_hover.asTex());

        skillButton.setName2("skill");
        onButton.setName2("on");
        ifButton.setName2("if");
        isButton.setName2("is");
    }

    public void refreshPos(int pos) {
        this.pos = pos;

        skillButton.setPos(skillX, skillY + (pos * h));
        onButton.setPos(skillX + (1 * w), skillY + (pos * h));
        ifButton.setPos(skillX + (2 * w), skillY + (pos * h));
        isButton.setPos(skillX + (3 * w), skillY + (pos * h));
        exitButton.setPos(skillX + (2 * w) + 60, skillY + 3 + (pos * h));

        skillButton.setName("skill_" + pos);
        onButton.setName("on_" + pos);
        ifButton.setName("if_" + pos);
        isButton.setName("is_" + pos);
    }

    public void setOption(TacticOption option) {
        if (option.getTacticOnName() != null) {
            onName = option.getTacticOnName();
            onButton.setTex(onName.getTex());
        }
        if (option.getTacticIfName() != null) {
            tacticIf.setTacticIfName(option.getTacticIfName());
            ifButton.setTex(tacticIf.getTacticIfName().getTex());
        }
    }

    public void refreshTacticIfTex() {
        ifButton.setTex2(tacticIf.getTacticIfRegardingName().getTex());
    }
}
