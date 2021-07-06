package com.henez.simple.data.playermenu;

import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.state.PlayerMenuState;
import com.henez.simple.input.In;
import com.henez.simple.menu.buttons.*;
import com.henez.simple.world.mapobjects.Fighter;
import lombok.Getter;

@Getter
public class PlayerMenu {

    private boolean showBag = false;
    private boolean showPlayerMenu = false;
    private boolean showEscapeMenu = false;

    private PlayerMenuState playerMenuState = PlayerMenuState.gear;
    private ButtonGroup tabs;
    private ButtonGroup cards;
    private TabButton tabSelected;
    private TabButton tabHover;
    private PlayerCardButton cardSelected;
    private Fighter fighterSelected;
    private BasicButton exit;

    public PlayerMenu() {
        tabs = ButtonFactory.createPlayerMenuTabs();
        cards = new ButtonGroup();
        exit = new BasicButton(435, 9, ImgUi.exit.asTex(), ImgUi.exit_hover.asTex());
    }

    public void update() {
        if (showPlayerMenu) {
            updateTabs();
            updateCards();
            updateExit();

            switch (playerMenuState) {
            case gear:
                updateMenuGear();
                break;
            case skills:
                updateMenuSkills();
                break;
            case tree:
                updateMenuTree();
                break;
            default:
                break;
            }

            if (In.c.isPressed() || In.esc.isPressed()) {
                closePlayerMenu();
            }
        } else {
            if (In.c.isPressed()) {
                openPlayerMenu();
            }
        }
    }

    private void updateMenuGear() {

    }

    private void updateMenuSkills() {
        fighterSelected.getSkillInventory().update();
    }

    private void updateMenuTree() {

    }

    public void updateParty(GameList<Fighter> fighters) {
        cards = ButtonFactory.createPlayerMenuCards(fighters);
    }

    private void updateCards() {
        cards.update();
        cards.getClickedButton().ifPresent(button -> {
            clickCard((PlayerCardButton) button);
        });
    }

    private void updateTabs() {
        tabs.update();
        tabHover = (TabButton) tabs.getFirstHovered().orElse(null);
        tabs.getClickedButton().ifPresent(button -> {
            clickTab((TabButton) button);
        });
    }

    private void clickCard(PlayerCardButton button) {
        cards.getButtons().forEach(Button::setInactive);
        button.setActive();
        cardSelected = button;
        fighterSelected = cardSelected.getFighter();
    }

    private void updateExit() {
        exit.update();
        if (exit.isClicked()) {
            closePlayerMenu();
        }
    }

    private void clickTab(TabButton button) {
        tabs.getButtons().forEach(Button::setInactive);
        button.setActive();
        openTab(button.getName());
        tabSelected = button;
    }

    private void openTab(String name) {
        showBag = false;
        switch (name) {
        case "gear":
            playerMenuState = PlayerMenuState.gear;
            showBag = true;
            break;
        case "skills":
            playerMenuState = PlayerMenuState.skills;
            break;
        case "tree":
            playerMenuState = PlayerMenuState.tree;
            break;
        }
    }

    private void openPlayerMenu() {
        playerMenuState = PlayerMenuState.gear;
        showPlayerMenu = true;
        showBag = true;
        clickCard((PlayerCardButton) cards.getButtons().get(0));
        clickTab((TabButton) tabs.getByName("gear"));
    }

    private void closePlayerMenu() {
        showPlayerMenu = false;
        showBag = false;
    }
}
