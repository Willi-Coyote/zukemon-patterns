package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoyalRumble extends FightStyle {

    public RoyalRumble(ZukemonFactory zukemonFactory) {
        super(zukemonFactory);
    }

    @Override
    public Zukemon fight() {
        List<Zukemon> fighters = createFighterList();

        while (fighters.size() > 1) {
            Zukemon attacker = fighters.get(new Random().nextInt(fighters.size()));
            Zukemon defender = fighters.get(new Random().nextInt(fighters.size()));

            while (attacker == defender) {
                defender = fighters.get(new Random().nextInt(fighters.size()));
            }

            attack(attacker, defender);
            if (defender.isDead()) {
                updateObserversZukemonDied(defender, "Zukemon '" + getName(defender) + "' is out of the royal rumble.\r\n");
                fighters.remove(defender);
            }
        }

        return fighters.get(0);
    }

    private List<Zukemon> createFighterList() {
        List<Zukemon> fighters = new ArrayList<>();

        fighters.add(zukemonFactory.createRandomZukemon());
        fighters.add(zukemonFactory.createRandomZukemon());
        fighters.add(zukemonFactory.createRandomZukemon());
        fighters.add(zukemonFactory.createRandomZukemon());
        fighters.add(zukemonFactory.createRandomZukemon());

        return fighters;
    }

    @Override
    protected FightMode getFightMode() {
        return FightMode.ROYAL_RUMBLE;
    }
}
