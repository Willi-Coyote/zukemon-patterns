package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.zukemons.Zukemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoyalRumble extends FightMode {


    public RoyalRumble(Fight fight) {
        super(fight);
    }

    @Override
    public Zukemon attack() {
        Zukemon fighter1 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter3 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter4 = getFight().getZukemonFactory().createRandomZukemon();
        Zukemon fighter5 = getFight().getZukemonFactory().createRandomZukemon();

        List<Zukemon> fighters = new ArrayList<>();
        fighters.add(fighter1);
        fighters.add(fighter2);
        fighters.add(fighter3);
        fighters.add(fighter4);
        fighters.add(fighter5);

        while (fighters.size() > 1) {
            Zukemon attacker = fighters.get(new Random().nextInt(fighters.size()));
            Zukemon defender = fighters.get(new Random().nextInt(fighters.size()));
            while (attacker == defender) {
                defender = fighters.get(new Random().nextInt(fighters.size()));
            }

            attack(attacker, defender);
            if (defender.isDead()) {
                updateHistory("Zukemon '" + defender.getClass().getSimpleName() + "' is out of the royal rumble.\r\n");
                fighters.remove(defender);
            }
        }
        return fighters.get(0);
    }
}
