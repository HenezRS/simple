package com.henez.simple.skills.tactics;

import lombok.Getter;

@Getter
public class TacticIf {
    private TacticIfName tacticIfName;
    private TacticIfRegardingName tacticIfRegardingName;

    public TacticIf() {
        tacticIfName = TacticIfName.always;
        tacticIfRegardingName = TacticIfRegardingName.none;
    }

    public void setTacticIfName(TacticIfName name) {
        this.tacticIfName = name;
    }

    public void setTacticIfRegardingName(TacticIfRegardingName name) {
        this.tacticIfRegardingName = name;
    }

    public void defaultTacticIfRegardingName() {
        this.tacticIfRegardingName = this.tacticIfName.getRegardingDefault();
    }
}
