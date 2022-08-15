package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.FightType;

public class FightModeFactory {

    public FightMode createFightMode(FightType fightType, Fight fight) {
        switch (fightType) {
            case DEFEND:
                return new Defend(fight);
            case ROYAL_RUMBLE:
                return new RoyalRumble(fight);
            case NORMAL:
                return new Normal(fight);
            default:
                throw new IllegalArgumentException("Invalid fight type " + fightType);
        }
    }
}
