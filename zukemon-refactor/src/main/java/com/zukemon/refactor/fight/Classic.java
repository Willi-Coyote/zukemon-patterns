package com.zukemon.refactor.fight;

import com.zukemon.refactor.FightType;
import com.zukemon.refactor.zukemons.ZukemonFactory;
import com.zukemon.refactor.zukemons.Zukemon;

public class Classic extends FightMode {

    public Classic(ZukemonFactory zukemonFactory) {
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
    protected FightType getFightType() {
        return FightType.CLASSIC;
    }
}
