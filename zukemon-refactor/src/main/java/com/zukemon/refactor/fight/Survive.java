package com.zukemon.refactor.fight;

import com.zukemon.refactor.FightType;
import com.zukemon.refactor.zukemons.ZukemonFactory;
import com.zukemon.refactor.zukemons.Zukemon;

public class Survive extends FightMode {

    public Survive(ZukemonFactory zukemonFactory) {
        super(zukemonFactory);
    }

    @Override
    public Zukemon fight() {
        Zukemon attacker = zukemonFactory.createRandomZukemon();
        Zukemon defender = zukemonFactory.createRandomZukemon();
        int initialLifePoints = defender.getLifePoints();
        defender.increaseLifePointsBy(5000);

        int numberOfSurvivedRounds = 0;
        while (true) {
            attack(attacker, defender);
            if (defender.isDead()) {
                updateObserversZukemonDied(defender, "Zukemon '" + getName(defender) + "' has survived " + numberOfSurvivedRounds + " rounds.\r\n");
                return attacker;
            }

            heal10Percent(defender, initialLifePoints / 100 * 10);
            numberOfSurvivedRounds++;
        }
    }

    private void heal10Percent(Zukemon defender, int lifePointsToHeal) {
        defender.increaseLifePointsBy(lifePointsToHeal);
    }

    @Override
    protected FightType getFightType() {
        return FightType.SURVIVE;
    }
}
