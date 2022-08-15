package com.zukemon.refactor.fight;

import com.zukemon.refactor.ZukemonFactory;
import com.zukemon.refactor.zukemons.Zukemon;

public class Defend extends FightMode {
    public Defend(ZukemonFactory zukemonFactory) {
        super(zukemonFactory);
    }

    @Override
    public Zukemon attack() {
        Zukemon attacker = getZukemonFactory().createRandomZukemon();
        Zukemon defender = getZukemonFactory().createRandomZukemon();

        int initialLifePoints = defender.getLifePoints();
        // The defender gets super much life points
        defender.increaseLifePointsBy(5000);
        int numberOfSurvivedRounds = 0;

        while (true) {
           attack(attacker, defender);
            if (defender.isDead()) {
                updateObservers("Zukemon '" + defender.getClass().getSimpleName() + "' has survived " + numberOfSurvivedRounds + " rounds.\r\n");
                return attacker;
            }

            //heal 10% of initial lifepoints
            defender.increaseLifePointsBy(initialLifePoints / 100 * 10);
            numberOfSurvivedRounds++;
        }
    }
}
