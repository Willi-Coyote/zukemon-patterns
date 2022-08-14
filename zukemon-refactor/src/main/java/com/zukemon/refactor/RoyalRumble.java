package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.Zukemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoyalRumble extends FightStyle {

    public RoyalRumble(Fight fight) {
        super(fight);
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
                fighters.remove(defender);
                addHistoryEntry("Zukemon '" + getName(defender) + "' is out of the royal rumble.\r\n");
            }
        }

        return fighters.get(0);
    }

    private List<Zukemon> createFighterList() {
        List<Zukemon> fighters = new ArrayList<>();

        fighters.add(fight.getZukemonFactory().createRandomZukemon());
        fighters.add(fight.getZukemonFactory().createRandomZukemon());
        fighters.add(fight.getZukemonFactory().createRandomZukemon());
        fighters.add(fight.getZukemonFactory().createRandomZukemon());
        fighters.add(fight.getZukemonFactory().createRandomZukemon());

        return fighters;
    }
}
