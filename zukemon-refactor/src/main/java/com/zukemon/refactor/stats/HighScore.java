package com.zukemon.refactor.stats;

import com.zukemon.refactor.fight.FightObserver;
import com.zukemon.refactor.zukemons.Zukemon;

public class HighScore implements FightObserver {

    private int highScore;

    @Override
    public void update(Zukemon attacker, Zukemon defender, int damage) {
        if (damage > highScore) {
            highScore = damage;
            System.out.println("New highscore from " + attacker.getClass().getSimpleName() + ": " + highScore);
        }
    }

    @Override
    public void update(String message) {
        // do nothing
    }

    public int getHighScore() {
        return highScore;
    }
}
