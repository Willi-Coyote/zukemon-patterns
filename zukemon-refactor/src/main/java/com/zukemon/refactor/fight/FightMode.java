package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class FightMode {

    public static final String HISTORY_FILENAME = "history.txt";
    private Fight fight;

    public FightMode(Fight fight) {
        this.fight = fight;
    }

    public Fight getFight() {
        return fight;
    }

    public abstract Zukemon attack();

    protected void attack(Zukemon attacker, Zukemon defender) {
        int attackerDamage = attacker.hit();
        defender.reduceLifePointsBy(attackerDamage);

        getFight().getArenaDisplay().update(attacker, attackerDamage);
        updateHighScore( attacker, attackerDamage);
        updateHistory(attacker, defender, attackerDamage);
    }

    protected void updateHistory(Zukemon attacker, Zukemon defender, int damage) {
        String historyRecord = "Zukemon '" + getName(attacker) + "' made " + damage + " damage at '" + getName(defender) + "'\r\n";
        try {
            ensureFileExists();
            Files.write(Paths.get(HISTORY_FILENAME), historyRecord.getBytes(), StandardOpenOption.APPEND);
            if (defender.isDead()) {
                String deadMessage = "Zukemon '" + getName(defender) + "' is out of the royal rumble.\r\n";
                Files.write(Paths.get(HISTORY_FILENAME), deadMessage.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void updateHistory(String message) {
        try {
            ensureFileExists();
            Files.write(Paths.get(HISTORY_FILENAME), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ensureFileExists() throws IOException {
        File historyFile = new File(HISTORY_FILENAME);
        if (!historyFile.exists()) {
            historyFile.createNewFile();
        }
    }

    protected String getName(Zukemon zukemon) {
        return zukemon.getClass().getSimpleName();
    }

    protected void updateHighScore(Zukemon fighter, int damage) {
        if (damage > getFight().getHighScore()) {
            getFight().setHighScore(damage);
            System.out.println("New highscore from " + getName(fighter) + ": " + getFight().getHighScore());
        }
    }
}
