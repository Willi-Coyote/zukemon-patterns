package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public class DefendFight extends FightStyle {

    public DefendFight(ZukemonFactory zukemonFactory) {
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
    protected FightMode getFightMode() {
        return FightMode.DEFEND;
    }
}
