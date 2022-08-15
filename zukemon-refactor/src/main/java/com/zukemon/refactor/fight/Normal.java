package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

public class Normal extends FightMode {
    public Normal(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon fighter1 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = getFight().getZukemonFactory().createRandomZukemon();

        while (true) {
            int attackerDamage = fighter1.hit();
            fighter2.reduceLifePointsBy(attackerDamage);

            getFight().getArenaDisplay().update(fighter1, attackerDamage);
            updateHighScore( fighter1, attackerDamage);
            updateHistory(fighter1, fighter2, attackerDamage);
            if (fighter1.isDead()) {
                updateHistory( "Zukemon '" + fighter1.getClass().getSimpleName() + "' is dead looser");
            }

            if (fighter2.isDead()) {
                return fighter1;
            }

            int defenderDamage = fighter2.hit();
            fighter1.reduceLifePointsBy(defenderDamage);

            updateHistory(fighter2, fighter1, defenderDamage);
            updateHighScore(fighter2, defenderDamage);
            if (fighter2.isDead()) {
                updateHistory( "Zukemon '" + fighter2.getClass().getSimpleName() + "' is dead looser");
            }
            getFight().getArenaDisplay().update(fighter2, defenderDamage);

            if (fighter1.isDead()) {
                return fighter2;
            }
        }
    }
}
