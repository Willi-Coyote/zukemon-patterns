package com.zukemon.refactor.fight;

import com.zukemon.refactor.FightType;

public class FightModeFactory {

    public FightMode createFightMode(FightType fightType) {
        switch (fightType) {
            case DEFEND:
                return new Defend();
            case ROYAL_RUMBLE:
                return new RoyalRumble();
            case NORMAL:
                return new Normal();
            default:
                throw new IllegalArgumentException("Invalid fight type " + fightType);
        }
    }
}
