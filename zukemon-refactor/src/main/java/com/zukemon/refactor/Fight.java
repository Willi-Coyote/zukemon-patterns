package com.zukemon.refactor;

import com.zukemon.refactor.fight.FightModeFactory;
import com.zukemon.refactor.fight.FightMode;
import com.zukemon.refactor.stats.ArenaDisplay;
import com.zukemon.refactor.stats.HighScore;
import com.zukemon.refactor.stats.HistoryRecorder;
import com.zukemon.refactor.zukemons.Zukemon;
import com.zukemon.refactor.zukemons.ZukemonFactory;

public class Fight {

    ZukemonFactory zukemonFactory = new ZukemonFactory();

    /**
     * This method executes a fight based on the given {@link FightType}.
     *
     * @param fightType the type of fightMode
     */
    public Zukemon fight(FightType fightType) {
        FightModeFactory fightModeFactory = new FightModeFactory();

        FightMode fight = fightModeFactory.createFight(fightType, zukemonFactory);
        addObservers(fight);

        return fight.fight();
    }

    private void addObservers(FightMode fight) {
        fight.addObserver(new HighScore());
        fight.addObserver(new ArenaDisplay());
        fight.addObserver(new HistoryRecorder());
    }
}
