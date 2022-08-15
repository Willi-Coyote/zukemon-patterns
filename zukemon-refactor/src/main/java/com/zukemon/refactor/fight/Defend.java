package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Defend extends FightMode {
    public Defend(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon attacker = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon defender = getFight().getZukemonFactory().createRandomZukemon();

        int initialLifePoints = defender.getLifePoints();
        // The defender gets super much life points
        defender.increaseLifePointsBy(5000);
        int numberOfSurvivedRounds = 0;

        while (true) {
            int attackerDamage = attacker.hit();
            defender.reduceLifePointsBy(attackerDamage);

            getFight().getArenaDisplay().update(attacker, attackerDamage);
            updateHighScore(attacker, attackerDamage);
            updateHistory(attacker, defender, attackerDamage);
            if (defender.isDead()) {
                updateHistory("Zukemon '" + defender.getClass().getSimpleName() + "' has survived " + numberOfSurvivedRounds + " rounds.\r\n");
            }

            if (defender.isDead()) {
                return attacker;
            }

            //heal 10% of initial lifepoints
            defender.increaseLifePointsBy(initialLifePoints / 100 * 10);
            numberOfSurvivedRounds++;
        }
    }
}
