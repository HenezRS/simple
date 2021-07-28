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
    private String clickedOptionName;
    private String selectedButtonName;

    public TacticInventory() {
        tactics = new GameList<>();
        buttonMap = new HashMap<>();
        options = new GameList<>();

    }

    public void update() {

        int tacticToRemove = -1;
        int index = 0;
        for (Tactic tactic : tactics) {
            tactic.update();
            if (tactic.getExitButton().isClicked()) {
                tacticToRemove = index;
            }
            index++;
        }

        options.forEach(TacticOption::update);
        clickedOption = options.stream().filter(option -> option.getButton().isClicked()).findAny().orElse(null);
        if (clickedOption != null) {
            clickedOptionName = clickedOption.getButton().getName();
            refreshAllButtons();
        }

        buttonMap.forEach((key, value) -> {
            for (Button button : value) {
                if (button.isClicked()) {
                    if (button.getName().equals(selectedButtonName)) {
                        unselectButton();
                    } else {
                        selectButton(button);
                    }
                }
            }
        });

        if (tacticToRemove > -1) {
            tactics.remove(tacticToRemove);
            refreshAllButtons();
            //refreshOptions();
        }

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

        selectButtonByName(selectedButtonName);
        refreshOptions();
    }

    private void selectButton(Button button) {
        selectedButtonName = button.getName();
        refreshAllButtons();
        //refreshOptions();
    }

    private void selectButtonByName(String buttonName) {
        selectedButton = null;
        selectedTactic = null;
        buttonMap.forEach((key, value) -> {
            for (Button button : value) {
                if (button.getName().equalsIgnoreCase(buttonName)) {
                    selectedTactic = key;
                    selectedButton = button;
                }
            }
        });

        if (selectedButton == null) {
            selectedButtonName = null;
        }
    }

    private void unselectButton() {
        selectedButtonName = null;
        clickedOptionName = null;
        //refreshOptions();
        refreshAllButtons();
    }

    private void refreshOptions() {
        int x = 271;
        int y = 77;
        int h = 18;
        int i = 0;
        options = new GameList<>();

        if (selectedButton != null) {
            switch (selectedButton.getName2()) {
            case "skill": {
                break;
            }
            case "on": {
                for (TacticOnName onName : TacticOnName.values()) {
                    TacticOption op = new TacticOption(i, x, y + (h * (i++)), onName);
                    options.add(op);
                    if (clickedOptionName == null && selectedTactic.getOnName() == onName) {
                        clickedOption = op;
                        clickedOption.getButton().setActive();
                        selectedTactic.setOption(clickedOption);
                    }
                }
                break;
            }
            case "if": {
                for (TacticIfName ifName : TacticIfName.values()) {
                    TacticOption op = new TacticOption(i, x, y + (h * (i++)), ifName);
                    options.add(op);
                    if (clickedOptionName == null && selectedTactic.getTacticIf().getTacticIfName() == ifName) {
                        clickedOption = op;
                        clickedOption.getButton().setActive();
                        selectedTactic.setOption(clickedOption);
                    }
                }
                break;
            }
            default:
                break;
            }
        }

        if (clickedOptionName != null) {
            options.forEach(option -> {
                if (option.getButton().getName().equalsIgnoreCase(clickedOptionName)) {
                    clickedOption = option;
                    clickedOption.getButton().setActive();
                    selectedTactic.setOption(clickedOption);
                    clickedOptionName = null;
                }
            });
        }
    }

    public void setSelectedSkill(SkillName skill) {
        selectedTactic.setSkillName(skill);
        refreshAllButtons();
    }

    public boolean selectedButtonIsSkill() {
        return selectedButton != null && selectedButton.getName2().equalsIgnoreCase("skill");
    }
}
