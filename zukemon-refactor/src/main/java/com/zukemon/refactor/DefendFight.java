package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DefendFight extends FightStyle {

    public DefendFight(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon attacker = fight.getZukemonFactory().createRandomZukemon();
        Zukemon defender = fight.getZukemonFactory().createRandomZukemon();
        int initialLifePoints = defender.getLifePoints();
        // The defender gets super much life points
        defender.increaseLifePointsBy(5000);

        int numberOfSurvivedRounds = 0;
        while (true) {
            int attackerDamage = attacker.hit();
            defender.reduceLifePointsBy(attackerDamage);
            this.fight.getArenaDisplay().update(attacker, attackerDamage);
            if (attackerDamage > this.fight.getHighScore()) {
                this.fight.setHighScore(attackerDamage);
                System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + this.fight.getHighScore());
            }
            String historyRecord = "Zukemon '" + attacker.getClass().getSimpleName() + "' made " + attackerDamage + " damage at '" + defender.getClass().getSimpleName() + "'\r\n";
            try {
                File historyFile = new File("history.txt");
                if (!historyFile.exists()) {
                    historyFile.createNewFile();
                }
                Files.write(Paths.get("history.txt"), historyRecord.getBytes(), StandardOpenOption.APPEND);
                if (defender.isDead()) {
                    String deadMessage = "Zukemon '" + defender.getClass().getSimpleName() + "' has survived " + numberOfSurvivedRounds + " rounds.\r\n";
                    Files.write(Paths.get("history.txt"), deadMessage.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                e.printStackTrace();
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
