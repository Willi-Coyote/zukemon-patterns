package com.zukemon.refactor.fight;

import com.zukemon.refactor.Fight;
import com.zukemon.refactor.ZukemonFactory;
import com.zukemon.refactor.zukemons.Zukemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoyalRumble extends FightMode {


    public RoyalRumble(ZukemonFactory zukemonFactory) {
        super(zukemonFactory);
    }

    @Override
    public Zukemon attack() {
        List<Zukemon> fighters = new ArrayList<>();
        
        fighters.add(getZukemonFactory().createRandomZukemon());
        fighters.add(getZukemonFactory().createRandomZukemon());
        fighters.add(getZukemonFactory().createRandomZukemon());
        fighters.add(getZukemonFactory().createRandomZukemon());
        fighters.add(getZukemonFactory().createRandomZukemon());

        while (fighters.size() > 1) {
            Zukemon attacker = fighters.get(new Random().nextInt(fighters.size()));
            Zukemon defender = fighters.get(new Random().nextInt(fighters.size()));
            while (attacker == defender) {
                defender = fighters.get(new Random().nextInt(fighters.size()));
            }

            attack(attacker, defender);
            if (defender.isDead()) {
                updateObservers("Zukemon '" + defender.getClass().getSimpleName() + "' is out of the royal rumble.\r\n");
                fighters.remove(defender);
            }
        }
        return fighters.get(0);
    }
}
