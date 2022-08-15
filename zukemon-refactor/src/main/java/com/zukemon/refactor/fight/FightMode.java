package com.zukemon.refactor.fight;

import com.zukemon.refactor.FightObserver;
import com.zukemon.refactor.FightType;
import com.zukemon.refactor.zukemons.Zukemon;
import com.zukemon.refactor.zukemons.ZukemonFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class FightMode {
    protected ZukemonFactory zukemonFactory;

    private List<FightObserver> observers = new ArrayList<>();

    public FightMode(ZukemonFactory zukemonFactory) {
        this.zukemonFactory = zukemonFactory;
    }

    protected Zukemon attack(Zukemon attacker, Zukemon defender) {
        int damage = attacker.hit();
        defender.reduceLifePointsBy(damage);

        updateObservers(attacker, defender, damage);

        return defender.isDead() ? attacker : null;
    }

    protected String getName(Zukemon zukemon) {
        return zukemon.getClass().getSimpleName();
    }

    public void addObserver(FightObserver observer) {
        this.observers.add(observer);
    }

    private void updateObservers(Zukemon attacker, Zukemon defender, int damage) {
        observers.forEach(o -> o.update(attacker, defender, damage, getFightType()));
    }

    protected void updateObserversZukemonDied(Zukemon deadBody, String message) {
        observers.forEach(o -> o.fighterDied(deadBody, message));
    }

    protected abstract FightType getFightType();

    public abstract Zukemon fight();
}
