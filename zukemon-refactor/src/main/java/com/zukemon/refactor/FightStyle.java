package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class FightStyle {
    public static final String HISTORY_FILENAME = "history.txt";

    protected Fight fight;

    public FightStyle(Fight fight) {
        this.fight = fight;
    }

    public abstract Zukemon fight();

    protected Zukemon attack(Zukemon attacker, Zukemon defender) {
        int damage = attacker.hit();
        defender.reduceLifePointsBy(damage);

        HighScore.updateHighscore(damage, attacker);
        this.fight.getArenaDisplay().update(attacker, damage);
        addHistoryRecord(attacker, defender, damage);

        return defender.isDead() ? attacker : null;
    }

    protected void addHistoryRecord(Zukemon attacker, Zukemon defender, int damage) {
        String historyRecord = "Zukemon '" + getName(attacker) + "' made " + damage + " damage at '" + getName(defender) + "'\r\n";
        try {
            ensureFileExists();
            Files.write(Paths.get(HISTORY_FILENAME), historyRecord.getBytes(), StandardOpenOption.APPEND);

            if (defender.isDead()) {
                String deadMessage = "Zukemon '" + getName(defender) + "' is dead looser";
                Files.write(Paths.get(HISTORY_FILENAME), deadMessage.getBytes(), StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void ensureFileExists() throws IOException {
        File historyFile = new File(HISTORY_FILENAME);
        if (!historyFile.exists()) {
            historyFile.createNewFile();
        }
    }

    protected void addHistoryEntry(String message) {
        try {
            ensureFileExists();
            Files.write(Paths.get(HISTORY_FILENAME), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getName(Zukemon zukemon) {
        return zukemon.getClass().getSimpleName();
    }
}
