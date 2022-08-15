package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoyalRumble extends FightMode {


    public RoyalRumble(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon fight() {
        Zukemon fighter1 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter3 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter4 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter5 = getFight().getZukemonFactory().createRandomZukemon();

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

            getFight().getArenaDisplay().update(attacker, attackerDamage);
            updateHighScore(attacker, attackerDamage);
            updateHistory(attacker, defender, attackerDamage);

            if (defender.isDead()) {
                fighters.remove(defender);
            }
        }
        return fighters.get(0);
    }

    private void updateHistory(Zukemon attacker, Zukemon defender, int attackerDamage) {
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
    }

    private void updateHighScore(Zukemon attacker, int attackerDamage) {
        if (attackerDamage > getFight().getHighScore()) {
            getFight().setHighScore(attackerDamage);
            System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + getFight().getHighScore());
        }
    }
}
