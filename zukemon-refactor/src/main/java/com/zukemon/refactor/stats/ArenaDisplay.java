package com.zukemon.refactor.stats;

import com.zukemon.refactor.FightObserver;
import com.zukemon.refactor.FightType;
import com.zukemon.refactor.zukemons.Zukemon;

public class ArenaDisplay implements FightObserver {

    private int damage;

    @Override
    public void update(Zukemon attacker, Zukemon defender, int damage, FightType fightType) {
        this.damage = damage;
        System.out.println(attacker.getClass().getSimpleName() + " made " + damage + " damage");
    }

    @Override
    public void fighterDied(Zukemon deadBody, String message) {
        // Nothing to do
    }
}
