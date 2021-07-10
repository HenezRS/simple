package com.henez.simple.skills.tactics;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.menu.buttons.Button;
import com.henez.simple.skills.SkillName;
import lombok.Getter;

@Getter
public class TacticInventory {
    private GameList<Tactic> tactics;
    private Button selectedButton;
    private GameList<Button> buttons;

    public TacticInventory() {
        tactics = new GameList<>();
        buttons = new GameList<>();

    }

    public void update() {
        tactics.forEach(Tactic::update);
        buttons.stream().filter(Button::isClicked).findAny().ifPresent(b -> selectedButton = b);
    }

    public void addNewTactic(SkillName skillName) {
        tactics.add(new Tactic(skillName));
        refreshPositions();
    }

    private void refreshPositions() {
        buttons = new GameList<>();
        int pos = 0;
        for (Tactic tactic : tactics) {
            tactic.refreshPos(pos++);
            buttons.add(tactic.getSkillButton());
            buttons.add(tactic.getOnButton());
        }
    }
}
