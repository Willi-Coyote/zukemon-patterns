package com.zukemon.refactor;

import com.zukemon.refactor.fight.FightMode;
import com.zukemon.refactor.fight.FightModeFactory;
import com.zukemon.refactor.stats.ArenaDisplay;
import com.zukemon.refactor.stats.HighScore;
import com.zukemon.refactor.stats.HistoryRecorder;
import com.zukemon.refactor.zukemons.Zukemon;

public class Fight {

    ZukemonFactory zukemonFactory = new ZukemonFactory();

    private final ArenaDisplay arenaDisplay = new ArenaDisplay();
    private final HighScore highScore = new HighScore();
    private final HistoryRecorder historyRecorder = new HistoryRecorder();

    public Zukemon fight(FightType fightType) {
        FightModeFactory factory = new FightModeFactory();
        FightMode fightMode = factory.createFightMode(fightType, zukemonFactory);

        addObservers(fightMode);

        return fightMode.attack();
    }

    private void addObservers(FightMode fightMode) {
        fightMode.addObsever(arenaDisplay);
        fightMode.addObsever(highScore);
        fightMode.addObsever(historyRecorder);
    }
}
