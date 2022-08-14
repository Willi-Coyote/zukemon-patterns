package com.zukemon.refactor;

public class FightModeFactory {
    public FightStyle createFight(FightMode fightMode, Fight fight) {
        switch (fightMode) {
            case DEFEND:
                return new DefendFight(fight);
            case NORMAL:
                return new NormalFight(fight);
            case ROYAL_RUMBLE:
                return new RoyalRumble(fight);
            default:
                throw new IllegalArgumentException("");
        }
    }
}
