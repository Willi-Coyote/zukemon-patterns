package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.ZukemonFactory;
import com.zukemon.refactor.zukemons.Zukemon;

import java.util.ArrayList;
import java.util.List;

public abstract class FightMode {

    private List<FightObserver> observers = new ArrayList<>();

    private ZukemonFactory zukemonFactory;

    public FightMode(ZukemonFactory zukemonFactory) {
        this.zukemonFactory = zukemonFactory;
    }

    public ZukemonFactory getZukemonFactory() {
        return zukemonFactory;
    }

    public void addObsever(FightObserver observer) {
        this.observers.add(observer);
    }

    protected void updateObservers(Zukemon attacker, Zukemon defender, int damage) {
        this.observers.forEach(o -> o.update(attacker, defender, damage));
    }

    protected void updateObservers(String message) {
        this.observers.forEach(o -> o.update(message));
    }

    public abstract Zukemon attack();

    protected void attack(Zukemon attacker, Zukemon defender) {
        int attackerDamage = attacker.hit();
        defender.reduceLifePointsBy(attackerDamage);

        updateObservers(attacker, defender, attackerDamage);
    }
}
