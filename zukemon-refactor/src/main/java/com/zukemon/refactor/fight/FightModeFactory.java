package com.zukemon.refactor.fight;

import com.zukemon.refactor.FightType;
import com.zukemon.refactor.ZukemonFactory;

public class FightModeFactory {

    public FightMode createFightMode(FightType fightType, ZukemonFactory zukemonFactory) {
        switch (fightType) {
            case DEFEND:
                return new Defend(zukemonFactory);
            case ROYAL_RUMBLE:
                return new RoyalRumble(zukemonFactory);
            case NORMAL:
                return new Normal(zukemonFactory);
            default:
                throw new IllegalArgumentException("Invalid fight type " + fightType);
        }
    }
}
