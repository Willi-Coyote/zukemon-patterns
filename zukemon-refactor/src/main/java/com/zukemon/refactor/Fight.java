package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fight {

    ZukemonFactory zukemonFactory = new ZukemonFactory();

    ArenaDisplay arenaDisplay = new ArenaDisplay();
    private int highScore = 0;

    /**
     * Blastoise #9 Water Damage 258
     * Mew #151 Psychic Damage 150 (10% chance of critical hit)
     * Wartortle #8 Water Damage 300
     * Mudkip #258 Water Damage 234
     * Pikachu #25 Electric Damage 135
     * Psyduck #54 Water Damage 127 (20% chance of critical hit)
     * Krookodile #553 Dark No Damage -> It is the team lead, so he can call his team members to arms. Add the
     * damage of all other Zukemons
     * <p>
     * Critical hits make double damage
     *
     * @param fightMode the type of fightMode
     */
    public Zukemon fight(FightMode fightMode) {
        Zukemon winner = null;

        if (fightMode == FightMode.NORMAL) {

            Zukemon attacker = zukemonFactory.createRandomZukemon();
            Zukemon defender = zukemonFactory.createRandomZukemon();
            while (true) {
                int attackerDamage = attacker.hit();
                defender.reduceLifePointsBy(attackerDamage);
                arenaDisplay.update(attacker, attackerDamage);
                if (attackerDamage > highScore) {
                    highScore = attackerDamage;
                    System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
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
                if (defenderDamage > highScore) {
                    highScore = defenderDamage;
                    System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
                }
                arenaDisplay.update(defender, defenderDamage);
                if (attacker.isDead()) {
                    return defender;
                }
            }
        } else if (fightMode == FightMode.DEFEND) {
            Zukemon attacker = zukemonFactory.createRandomZukemon();
            Zukemon defender = zukemonFactory.createRandomZukemon();
            int initialLifePoints = defender.getLifePoints();
            // The defender gets super much life points
            defender.increaseLifePointsBy(5000);
            int numberOfSurvivedRounds = 0;
            while (true) {
                int attackerDamage = attacker.hit();
                defender.reduceLifePointsBy(attackerDamage);
                arenaDisplay.update(attacker, attackerDamage);
                if (attackerDamage > highScore) {
                    highScore = attackerDamage;
                    System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
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

        } else if (fightMode == FightMode.ROYAL_RUMBLE) {
            Zukemon fighter1 = zukemonFactory.createRandomZukemon();
            Zukemon fighter2 = zukemonFactory.createRandomZukemon();
            Zukemon fighter3 = zukemonFactory.createRandomZukemon();
            Zukemon fighter4 = zukemonFactory.createRandomZukemon();
            Zukemon fighter5 = zukemonFactory.createRandomZukemon();

            List<Zukemon> fighters = new ArrayList<>();
            fighters.add(fighter1);
            fighters.add(fighter2);
            fighters.add(fighter3);
            fighters.add(fighter4);
            fighters.add(fighter5);

            while (fighters.size() > 1) {
                Zukemon attacker = fighters.get(new Random().nextInt(fighters.size()));
                Zukemon defender = fighters.get(new Random().nextInt(fighters.size()));
                while (attacker == defender) {
                    defender = fighters.get(new Random().nextInt(fighters.size()));
                }
                int attackerDamage = attacker.hit();
                defender.reduceLifePointsBy(attackerDamage);
                arenaDisplay.update(attacker, attackerDamage);
                if (attackerDamage > highScore) {
                    highScore = attackerDamage;
                    System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
                }
                String historyRecord = "Zukemon '" + attacker.getClass().getSimpleName() + "' made " + attackerDamage + " damage at '" + defender.getClass().getSimpleName() + "'\r\n";
                try {
                    File historyFile = new File("history.txt");
                    if (!historyFile.exists()) {
                        historyFile.createNewFile();
                    }
                    Files.write(Paths.get("history.txt"), historyRecord.getBytes(), StandardOpenOption.APPEND);
                    if (defender.isDead()) {
                        String deadMessage = "Zukemon '" + defender.getClass().getSimpleName() + "' is out of the royal rumble.\r\n";
                        Files.write(Paths.get("history.txt"), deadMessage.getBytes(), StandardOpenOption.APPEND);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (defender.isDead()) {
                    fighters.remove(defender);
                }
            }
            return fighters.get(0);
        } else {
            throw new IllegalArgumentException("FightMode '" + fightMode + "' is not a valid or know fight mode");
        }
    }

    public int getHighScore() {
        return highScore;
    }

}
