package com.zukemon.refactor.stats;

import com.zukemon.refactor.FightObserver;
import com.zukemon.refactor.FightType;
import com.zukemon.refactor.zukemons.Zukemon;

public class HighScore implements FightObserver {
    public static int highScore;

    @Override
    public void update(Zukemon attacker, Zukemon defender, int damage, FightType fightType) {
        if (damage > highScore) {
            highScore = damage;
            System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
        }
    }

    @Override
    public void fighterDied(Zukemon deadBody, String message) {
        // Nothing to do here
    }
}
