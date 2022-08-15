package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

public class Defend extends FightMode {
    public Defend(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon attack() {
        Zukemon attacker = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon defender = getFight().getZukemonFactory().createRandomZukemon();

        int initialLifePoints = defender.getLifePoints();
        // The defender gets super much life points
        defender.increaseLifePointsBy(5000);
        int numberOfSurvivedRounds = 0;

        while (true) {
           attack(attacker, defender);
            if (defender.isDead()) {
                getFight().getHistoryRecorder().updateHistory("Zukemon '" + defender.getClass().getSimpleName() + "' has survived " + numberOfSurvivedRounds + " rounds.\r\n");
                return attacker;
            }

            //heal 10% of initial lifepoints
            defender.increaseLifePointsBy(initialLifePoints / 100 * 10);
            numberOfSurvivedRounds++;
        }
    }
}
