package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public class HighScore {
    public static int highScore;

    public static void updateHighscore(int newScore, Zukemon zukemon) {
        if (newScore > highScore) {
            highScore = newScore;
            System.out.println("New highscore from " + zukemon.getClass().getSimpleName() + ": " + highScore);
        }
    }
}
