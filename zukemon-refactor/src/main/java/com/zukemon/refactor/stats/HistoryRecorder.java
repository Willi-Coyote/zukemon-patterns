package com.zukemon.refactor.stats;

import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HistoryRecorder {

    public static final String HISTORY_FILENAME = "history.txt";

    public void updateHistory(Zukemon attacker, Zukemon defender, int damage) {
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

    public void updateHistory(String message) {
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
}
