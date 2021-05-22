package com.henez.simple.data;

import com.henez.simple.atlas.imgs.ImgUi;
import com.henez.simple.enums.state.PlayerMenuState;
import com.henez.simple.input.In;
import com.henez.simple.menu.buttons.BasicButton;
import com.henez.simple.menu.buttons.ButtonFactory;
import com.henez.simple.menu.buttons.ButtonGroup;
import com.henez.simple.menu.buttons.ImageButton;
import com.henez.simple.menu.buttons.TabButton;
import com.henez.simple.stats.classes.ClassName;
import lombok.Getter;

@Getter
public class PlayerMenu {

    private boolean showBag = false;
    private boolean showPlayerMenu = false;
    private boolean showEscapeMenu = false;

    private PlayerMenuState playerMenuState = PlayerMenuState.gear;
    private ButtonGroup tabs;
    private TabButton tabSelected;
    private TabButton tabHover;
    private BasicButton exit;

    public PlayerMenu() {
        tabs = ButtonFactory.createPlayerMenuTabs();
        exit = new BasicButton(435,9, ImgUi.exit.asTex(),ImgUi.exit_hover.asTex());
    }

    public void update() {
        if(showPlayerMenu) {
            updateTabs();
            updateExit();
            if(In.c.isPressed() || In.esc.isPressed()) {
                closePlayerMenu();
            }
        } else {
            if(In.c.isPressed()) {
                openPlayerMenu();
            }
        }
    }

    private void updateTabs() {
        tabs.update();
        tabHover = (TabButton) tabs.getFirstHovered().orElse(null);
        tabs.getClickedButton().ifPresent(button -> {
            clickTab((TabButton)button);
        });
    }

    private void updateExit() {
        exit.update();
        if(exit.isClicked()) {
            closePlayerMenu();
        }
    }

    private void clickTab(TabButton button) {
        tabs.getButtons().forEach(b -> {
            ((TabButton)b).setInactive();
        });
        button.setActive();
        openTab(button.getName());
        tabSelected = button;
    }

    private void openTab(String name) {
        showBag = false;
        switch(name) {
        case "gear": playerMenuState = PlayerMenuState.gear; showBag = true; break;
        case "skills": playerMenuState = PlayerMenuState.skills; break;
        case "tree": playerMenuState = PlayerMenuState.tree; break;
        }
    }

    private void openPlayerMenu() {
        playerMenuState = PlayerMenuState.gear;
        showPlayerMenu = true;
        showBag = true;
        clickTab((TabButton) tabs.getByName("gear"));
    }

    private void closePlayerMenu() {
        showPlayerMenu = false;
        showBag = false;
    }
}
