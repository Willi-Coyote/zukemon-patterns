package com.zukemon.refactor;

public class FightModeFactory {
    public FightStyle createFight(FightMode fightMode, ZukemonFactory zukemonFactory) {
        switch (fightMode) {
            case DEFEND:
                return new DefendFight(zukemonFactory);
            case NORMAL:
                return new NormalFight(zukemonFactory);
            case ROYAL_RUMBLE:
                return new RoyalRumble(zukemonFactory);
            default:
                throw new IllegalArgumentException("");
        }
    }
}
