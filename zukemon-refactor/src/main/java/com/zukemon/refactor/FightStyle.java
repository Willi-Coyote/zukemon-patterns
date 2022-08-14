package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.util.ArrayList;
import java.util.List;

public abstract class FightStyle {
    protected ZukemonFactory zukemonFactory;

    private List<FightObserver> observers = new ArrayList<>();

    public FightStyle(ZukemonFactory zukemonFactory) {
        this.zukemonFactory = zukemonFactory;
    }

    public abstract Zukemon fight();

    public void addObserver(FightObserver observer) {
        this.observers.add(observer);
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

    private void updateObservers(Zukemon attacker, Zukemon defender, int damage) {
        observers.stream().forEach(o -> o.update(attacker, defender, damage, getFightMode()));
    }

    protected void updateObserversZukemonDied(Zukemon deadBody, String message) {
        observers.stream().forEach(o -> o.fighterDied(deadBody, message));
    }

    protected abstract FightMode getFightMode();
}
