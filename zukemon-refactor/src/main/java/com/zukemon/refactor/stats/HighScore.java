package com.zukemon.refactor.stats;

import com.zukemon.refactor.zukemons.Zukemon;

public class HighScore {

    private int highScore;

    public void setHighScore(Zukemon fighter, int damage) {
        if (damage > highScore) {
            highScore = damage;
            System.out.println("New highscore from " + fighter.getClass().getSimpleName() + ": " + highScore);
        }
    }

    public int getHighScore() {
        return highScore;
    }
}
