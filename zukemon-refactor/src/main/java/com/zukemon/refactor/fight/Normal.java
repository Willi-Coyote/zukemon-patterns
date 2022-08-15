package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Normal implements FightMode {
    @Override
    public Zukemon fight(Fight fight) {
        Zukemon attacker = fight.getZukemonFactory().createRandomZukemon();
        Zukemon defender = fight.getZukemonFactory().createRandomZukemon();
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
                    String deadMessage = "Zukemon '" + defender.getClass().getSimpleName() + "' is dead looser";
                    Files.write(Paths.get("history.txt"), deadMessage.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (defender.isDead()) {
                return attacker;
            }
            int defenderDamage = defender.hit();
            attacker.reduceLifePointsBy(defenderDamage);
            historyRecord = "Zukemon '" + defender.getClass().getSimpleName() + "' made " + defenderDamage + " damage at '" + attacker.getClass().getSimpleName() + "'\r\n";
            try {
                Files.write(Paths.get("history.txt"), historyRecord.getBytes(), StandardOpenOption.APPEND);
                if (attacker.isDead()) {
                    String deadMessage = "Zukemon '" + attacker.getClass().getSimpleName() + "' is dead looser";
                    Files.write(Paths.get("history.txt"), deadMessage.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(defenderDamage);
            if (defenderDamage > fight.getHighScore()) {
                fight.setHighScore(attackerDamage);
                System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + fight.getHighScore());
            }
            fight.getArenaDisplay().update(defender, defenderDamage);
            if (attacker.isDead()) {
                return defender;
            }
        }
    }
}
