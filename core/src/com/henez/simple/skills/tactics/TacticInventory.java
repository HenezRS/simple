package com.henez.simple.skills.tactics;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.menu.buttons.Button;
import com.henez.simple.skills.SkillName;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TacticInventory {
    private GameList<Tactic> tactics;
    private Button selectedButton;
    private Tactic selectedTactic;
    private Map<Tactic, GameList<Button>> buttonMap;
    private GameList<TacticOption> options;
    private TacticOption clickedOption;

    public TacticInventory() {
        tactics = new GameList<>();
        buttonMap = new HashMap<>();
        options = new GameList<>();

    }

    public void update() {
        tactics.forEach(Tactic::update);

        options.forEach(TacticOption::update);
        clickedOption = options.stream().filter(option -> option.getButton().isClicked()).findAny().orElse(null);
        if (clickedOption != null) {
            options.forEach(option -> option.getButton().setInactive());
            clickedOption.getButton().setActive();
            selectedTactic.setOption(clickedOption);
            refreshAllButtons();
        }

        buttonMap.forEach((key, value) -> {
            for (Button button : value) {
                if (button.isClicked()) {
                    selectButton(key, button);
                }
            }
        });

    }

    public void addNewTactic(SkillName skillName) {
        tactics.add(new Tactic(skillName));
        refreshAllButtons();
    }

    private void refreshAllButtons() {
        buttonMap = new HashMap<>();
        int pos = 0;
        for (Tactic tactic : tactics) {
            tactic.refreshButtons();
            tactic.refreshPos(pos++);
            GameList<Button> list = new GameList<>();
            list.add(tactic.getSkillButton());
            list.add(tactic.getOnButton());
            list.add(tactic.getIfButton());
            buttonMap.put(tactic, list);
        }
    }

    private void selectButton(Tactic tactic, Button button) {
        selectedTactic = tactic;
        selectedButton = button;
        refreshOptions();
        refreshAllButtons();
    }

    private void refreshOptions() {
        int x = 271;
        int y = 77;
        int h = 18;
        int i = 0;
        options = new GameList<>();

        switch (selectedButton.getName()) {
        case "skill": {
            break;
        }
        case "on": {
            for (TacticOnName onName : TacticOnName.values()) {
                options.add(new TacticOption(x, y + (h * (i++)), onName));
            }
            break;
        }
        case "if": {
            for (TacticIfName ifName : TacticIfName.values()) {
                options.add(new TacticOption(x, y + (h * (i++)), ifName));
            }
            break;
        }
        default:
            break;
        }
    }
}
