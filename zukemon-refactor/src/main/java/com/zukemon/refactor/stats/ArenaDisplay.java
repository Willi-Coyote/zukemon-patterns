package com.zukemon.refactor.stats;

import com.zukemon.refactor.fight.FightObserver;
import com.zukemon.refactor.zukemons.Zukemon;

public class ArenaDisplay implements FightObserver {

    private int damage;

    @Override
    public void update(Zukemon attacker, Zukemon defender, int damage) {
        this.damage = damage;
        System.out.println(attacker.getClass().getSimpleName() + " made " + damage + " damage");
    }

    @Override
    public void update(String message) {
        // do nothing
    }
}
