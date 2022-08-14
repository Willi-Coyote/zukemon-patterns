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
                addHistoryEntry("Zukemon '" + defender.getClass().getSimpleName() + "' is out of the royal rumble.\r\n");
            }
        }
        return fighters.get(0);
    }

    private List<Zukemon> createFighterList() {
        Zukemon fighter1 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter2 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter3 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter4 = fight.getZukemonFactory().createRandomZukemon();
        Zukemon fighter5 = fight.getZukemonFactory().createRandomZukemon();

        List<Zukemon> fighters = new ArrayList<>();
        fighters.add(fighter1);
        fighters.add(fighter2);
        fighters.add(fighter3);
        fighters.add(fighter4);
        fighters.add(fighter5);

        return fighters;
    }
}
