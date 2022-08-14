package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HistoryRecorder implements FightObserver {
    public static final String HISTORY_FILENAME = "history.txt";

    @Override
    public void update(Zukemon attacker, Zukemon defender, int damage, FightMode fightMode) {
        addHistoryRecord(attacker, defender, damage);
    }

    @Override
    public void fighterDied(Zukemon deadBody, String message) {
        addHistoryEntry(message);
    }

    private void addHistoryRecord(Zukemon attacker, Zukemon defender, int damage) {
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

    private void ensureFileExists() throws IOException {
        File historyFile = new File(HISTORY_FILENAME);
        if (!historyFile.exists()) {
            historyFile.createNewFile();
        }
    }

    private void addHistoryEntry(String message) {
        try {
            ensureFileExists();
            Files.write(Paths.get(HISTORY_FILENAME), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getName(Zukemon zukemon) {
        return zukemon.getClass().getSimpleName();
    }
}
