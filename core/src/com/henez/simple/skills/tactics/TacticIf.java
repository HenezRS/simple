package com.henez.simple.skills.tactics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TacticIf {
    private TacticIfName tacticIfName;
    private TacticIfRegardingName tacticIfRegardingName;

    public TacticIf() {
        tacticIfName = TacticIfName.always;
        tacticIfRegardingName = TacticIfRegardingName.they;
    }
}
