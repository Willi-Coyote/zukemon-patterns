package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public class NormalFight extends FightStyle {

    public NormalFight(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon fighter1 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = fight.getZukemonFactory().createRandomZukemon();

        while (true) {
            Zukemon winner = attack(fighter1, fighter2);
            if (winner != null) return winner;

            winner = attack(fighter2, fighter1);
            if (winner != null) return winner;
        }
    }
}
