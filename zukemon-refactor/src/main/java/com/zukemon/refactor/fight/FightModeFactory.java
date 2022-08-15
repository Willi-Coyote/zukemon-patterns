package com.zukemon.refactor.fight;

import com.zukemon.refactor.FightType;
import com.zukemon.refactor.zukemons.ZukemonFactory;

public class FightModeFactory {
    public FightMode createFight(FightType fightType, ZukemonFactory zukemonFactory) {
        switch (fightType) {
            case SURVIVE:
                return new Survive(zukemonFactory);
            case CLASSIC:
                return new Classic(zukemonFactory);
            case ROYAL_RUMBLE:
                return new RoyalRumble(zukemonFactory);
            default:
                throw new IllegalArgumentException("");
        }
    }
}
