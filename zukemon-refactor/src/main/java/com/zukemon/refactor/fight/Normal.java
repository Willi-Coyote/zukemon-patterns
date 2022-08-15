package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

public class Normal extends FightMode {
    public Normal(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon attack() {
        Zukemon fighter1 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = getFight().getZukemonFactory().createRandomZukemon();

        while (true) {
            attack(fighter1, fighter2);
            if (fighter2.isDead()) {
                updateHistory("Zukemon '" + fighter2.getClass().getSimpleName() + "' is dead looser");
                return fighter1;
            }

            attack(fighter2, fighter1);
            if (fighter1.isDead()) {
                updateHistory("Zukemon '" + fighter1.getClass().getSimpleName() + "' is dead looser");
                return fighter2;
            }
        }
    }
}
