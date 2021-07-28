package com.henez.simple.skills.tactics;

import com.henez.simple.menu.buttons.TacticOptionButton;
import lombok.Getter;

@Getter
public class TacticOption {
    private int pos;
    private TacticOptionButton button;
    private TacticIfName tacticIfName;
    private TacticOnName tacticOnName;
    private TacticIfRegardingName tacticIfRegardingName;

    public TacticOption(int pos, int x, int y, TacticOnName tacticOnName) {
        this.pos = pos;
        this.tacticOnName = tacticOnName;
        button = new TacticOptionButton(tacticOnName.getText(), x, y, tacticOnName.getTex());
    }

    public TacticOption(int pos, int x, int y, TacticIfName tacticIfName) {
        this.pos = pos;
        this.tacticIfName = tacticIfName;
        button = new TacticOptionButton(tacticIfName.getText(), x, y, tacticIfName.getTex());
        tacticIfRegardingName = tacticIfName.getRegardingDefault();
    }

    public void update() {
        button.update();
    }
}
