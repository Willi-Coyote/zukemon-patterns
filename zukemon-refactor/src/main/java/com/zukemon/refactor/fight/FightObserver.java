package com.zukemon.refactor.fight;

import com.zukemon.refactor.zukemons.Zukemon;

public interface FightObserver {

    void update(Zukemon attacker, Zukemon defender, int damage);

    void update(String message);
}
