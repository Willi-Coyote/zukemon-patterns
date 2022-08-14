package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public class DefendFight extends FightStyle {

    public DefendFight(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon attacker = fight.getZukemonFactory().createRandomZukemon();
        Zukemon defender = fight.getZukemonFactory().createRandomZukemon();
        int initialLifePoints = defender.getLifePoints();
        defender.increaseLifePointsBy(5000);

        int numberOfSurvivedRounds = 0;
        while (true) {
            attack(attacker, defender);
            if (defender.isDead()) {
                addHistoryEntry("Zukemon '" + defender.getClass().getSimpleName() + "' has survived " + numberOfSurvivedRounds + " rounds.\r\n");
                return attacker;
            }

            heal10Percent(defender, initialLifePoints / 100 * 10);
            numberOfSurvivedRounds++;
        }
    }

    private void heal10Percent(Zukemon defender, int lifePointsToHeal) {
        defender.increaseLifePointsBy(lifePointsToHeal);
    }
}
