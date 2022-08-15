package com.zukemon.refactor.stats;

import com.zukemon.refactor.zukemons.Zukemon;

public class ArenaDisplay {

    private int damage;

    public void update(Zukemon zukemon, int damage) {
        this.damage = damage;
        System.out.println(zukemon.getClass().getSimpleName() + " made " + damage + " damage");
    }
}
