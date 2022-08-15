package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

public interface FightObserver {
    void update(Zukemon attacker, Zukemon defender, int damage, FightType fightType);

    void fighterDied(Zukemon deadBody, String message);
}
