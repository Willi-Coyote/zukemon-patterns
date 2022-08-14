package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public class NormalFight extends FightStyle {

    public NormalFight(ZukemonFactory zukemonFactory) {
        super(zukemonFactory);
    }

    @Override
    public Zukemon fight() {
        Zukemon fighter1 = zukemonFactory.createRandomZukemon();
        Zukemon fighter2 = zukemonFactory.createRandomZukemon();

        while (true) {
            Zukemon winner = attack(fighter1, fighter2);
            if (winner != null) return winner;

            winner = attack(fighter2, fighter1);
            if (winner != null) return winner;
        }
    }

    @Override
    protected FightMode getFightMode() {
        return FightMode.NORMAL;
    }
}
