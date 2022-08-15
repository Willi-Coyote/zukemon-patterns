package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

public abstract class FightMode {

    private Fight fight;

    public FightMode(Fight fight) {
        this.fight = fight;
    }

    public Fight getFight() {
        return fight;
    }

    public abstract Zukemon attack();

    protected void attack(Zukemon attacker, Zukemon defender) {
        int attackerDamage = attacker.hit();
        defender.reduceLifePointsBy(attackerDamage);

        getFight().getArenaDisplay().update(attacker, attackerDamage);
        getFight().getHighScore().setHighScore(attacker, attackerDamage);
        fight.getHistoryRecorder().updateHistory(attacker, defender, attackerDamage);
    }
}
