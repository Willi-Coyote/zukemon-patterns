package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public interface FightObserver {
    void update(Zukemon attacker, Zukemon defender, int damage, FightMode fightMode);

    void fighterDied(Zukemon deadBody, String message);
}
