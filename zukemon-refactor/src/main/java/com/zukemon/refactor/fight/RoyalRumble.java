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

public class RoyalRumble implements FightMode {
    @Override
    public Zukemon fight(Fight fight) {
        Zukemon fighter1 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter3 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter4 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter5 = fight.getZukemonFactory().createRandomZukemon();

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
    }
}
