package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Normal extends FightMode {
    public Normal(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon fighter1 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = getFight().getZukemonFactory().createRandomZukemon();

        while (true) {
            int attackerDamage = fighter1.hit();
            fighter2.reduceLifePointsBy(attackerDamage);

            getFight().getArenaDisplay().update(fighter1, attackerDamage);
            updateHighScore( fighter1, attackerDamage);
            updateHistory(fighter1, fighter2, attackerDamage);

            if (fighter2.isDead()) {
                return fighter1;
            }

            int defenderDamage = fighter2.hit();
            fighter1.reduceLifePointsBy(defenderDamage);

            updateHistory(fighter2, fighter1, defenderDamage);
            updateHighScore(fighter2, defenderDamage);
            getFight().getArenaDisplay().update(fighter2, defenderDamage);

            if (fighter1.isDead()) {
                return fighter2;
            }
        }
    }

    private void updateHistory(Zukemon attacker, Zukemon defender, int damage) {
        String historyRecord = "Zukemon '" + attacker.getClass().getSimpleName() + "' made " + damage + " damage at '" + defender.getClass().getSimpleName() + "'\r\n";
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
    }

    private void updateHighScore( Zukemon fighter, int damage) {
        if (damage > getFight().getHighScore()) {
            getFight().setHighScore(damage);
            System.out.println("New highscore from " + fighter.getClass().getSimpleName() + ": " + getFight().getHighScore());
        }
    }
}
