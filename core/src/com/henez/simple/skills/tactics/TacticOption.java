package com.henez.simple.skills.tactics;

import com.henez.simple.menu.buttons.TacticOptionButton;
import lombok.Getter;

@Getter
public class TacticOption {
    private TacticOptionButton button;
    private TacticIfName tacticIfName;
    private TacticOnName tacticOnName;

    public TacticOption(int x, int y, TacticOnName tacticOnName) {
        this.tacticOnName = tacticOnName;
        button = new TacticOptionButton(tacticOnName.getText(), x, y, tacticOnName.getTex());
    }

    public TacticOption(int x, int y, TacticIfName tacticIfName) {
        this.tacticIfName = tacticIfName;
        button = new TacticOptionButton(tacticIfName.getText(), x, y, tacticIfName.getTex());
    }

    public void update() {
        button.update();
    }
}
