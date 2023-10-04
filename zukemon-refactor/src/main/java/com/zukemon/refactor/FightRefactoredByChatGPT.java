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

public class FightRefactoredByChatGPT {

    ZukemonFactory zukemonFactory = new ZukemonFactory();

    private final ArenaDisplay arenaDisplay = new ArenaDisplay();
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
            while (true) {
                Zukemon attacker = zukemonFactory.createRandomZukemon();
                Zukemon defender = zukemonFactory.createRandomZukemon();

                while (true) {
                    int attackerDamage = attacker.hit();
                    defender.reduceLifePointsBy(attackerDamage);
                    arenaDisplay.update(attacker, attackerDamage);

                    updateHighScore(attackerDamage, attacker);

                    logDamageHistory(attacker, defender, attackerDamage);

                    if (defender.isDead()) {
                        return attacker;
                    }

                    int defenderDamage = defender.hit();
                    attacker.reduceLifePointsBy(defenderDamage);

                    logDamageHistory(defender, attacker, defenderDamage);

                    System.out.println(defenderDamage);

                    updateHighScore(defenderDamage, attacker);

                    arenaDisplay.update(defender, defenderDamage);

                    if (attacker.isDead()) {
                        return defender;
                    }
                }
            }
        } else if (fightMode == FightMode.DEFEND) {
            while (true) {
                Zukemon attacker = zukemonFactory.createRandomZukemon();
                Zukemon defender = zukemonFactory.createRandomZukemon();
                int initialLifePoints = defender.getLifePoints();

                defender.increaseLifePointsBy(5000);
                int numberOfSurvivedRounds = 0;

                while (true) {
                    int attackerDamage = attacker.hit();
                    defender.reduceLifePointsBy(attackerDamage);
                    arenaDisplay.update(attacker, attackerDamage);

                    updateHighScore(attackerDamage, attacker);

                    logDamageHistory(attacker, defender, attackerDamage);

                    if (defender.isDead()) {
                        return attacker;
                    }

                    defender.increaseLifePointsBy(initialLifePoints / 100 * 10);
                    numberOfSurvivedRounds++;
                }
            }
        } else if (fightMode == FightMode.ROYAL_RUMBLE) {
            List<Zukemon> fighters = initializeRoyalRumble();

            while (fighters.size() > 1) {
                Zukemon attacker = selectRandomFighter(fighters);
                Zukemon defender = selectRandomFighter(fighters);

                while (attacker == defender) {
                    defender = selectRandomFighter(fighters);
                }

                int attackerDamage = attacker.hit();
                defender.reduceLifePointsBy(attackerDamage);
                arenaDisplay.update(attacker, attackerDamage);

                updateHighScore(attackerDamage, attacker);

                logDamageHistory(attacker, defender, attackerDamage);

                if (defender.isDead()) {
                    fighters.remove(defender);
                }
            }

            return fighters.get(0);
        } else {
            throw new IllegalArgumentException("FightMode '" + fightMode + "' is not a valid or known fight mode");
        }
    }

    private void updateHighScore(int damage, Zukemon attacker) {
        if (damage > highScore) {
            highScore = damage;
            System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
        }
    }

    private void logDamageHistory(Zukemon attacker, Zukemon defender, int damage) {
        String historyRecord = "Zukemon '" + attacker.getClass().getSimpleName() + "' made " + damage + " damage at '" + defender.getClass().getSimpleName() + "'\r\n";

        try {
            File historyFile = new File("history.txt");
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }
            Files.write(Paths.get("history.txt"), historyRecord.getBytes(), StandardOpenOption.APPEND);

            if (defender.isDead()) {
                String deadMessage = "Zukemon '" + defender.getClass().getSimpleName() + "' is ";
                if (attacker == defender) {
                    deadMessage += "out of the fight.\r\n";
                } else {
                    deadMessage += "dead looser\r\n";
                }
                Files.write(Paths.get("history.txt"), deadMessage.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Zukemon> initializeRoyalRumble() {
        List<Zukemon> fighters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fighters.add(zukemonFactory.createRandomZukemon());
        }
        return fighters;
    }

    private Zukemon selectRandomFighter(List<Zukemon> fighters) {
        return fighters.get(new Random().nextInt(fighters.size()));
    }

    public int getHighScore() {
        return highScore;
    }

}
