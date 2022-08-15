package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.ZukemonFactory;
import com.zukemon.refactor.zukemons.Zukemon;

public class Normal extends FightMode {
    public Normal(ZukemonFactory zukemonFactory) {
        super(zukemonFactory);
    }

    @Override
    public Zukemon attack() {
        Zukemon fighter1 = getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = getZukemonFactory().createRandomZukemon();

        while (true) {
            attack(fighter1, fighter2);
            if (fighter2.isDead()) {
                updateObservers("Zukemon '" + fighter2.getClass().getSimpleName() + "' is dead looser");
                return fighter1;
            }

            attack(fighter2, fighter1);
            if (fighter1.isDead()) {
                updateObservers("Zukemon '" + fighter1.getClass().getSimpleName() + "' is dead looser");
                return fighter2;
            }
        }
    }
}
