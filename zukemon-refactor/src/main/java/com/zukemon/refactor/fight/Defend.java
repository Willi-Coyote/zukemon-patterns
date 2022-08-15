package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Defend implements FightMode {
    @Override
    public Zukemon fight(Fight fight) {
        Zukemon attacker = fight.getZukemonFactory().createRandomZukemon();
        Zukemon defender = fight.getZukemonFactory().createRandomZukemon();
        int initialLifePoints = defender.getLifePoints();
        // The defender gets super much life points
        defender.increaseLifePointsBy(5000);
        int numberOfSurvivedRounds = 0;
        while (true) {
            int attackerDamage = attacker.hit();
            defender.reduceLifePointsBy(attackerDamage);
            fight.getArenaDisplay().update(attacker, attackerDamage);
            if (attackerDamage > fight.getHighScore()) {
                fight.setHighScore(attackerDamage);
                System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + fight.getHighScore());
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
