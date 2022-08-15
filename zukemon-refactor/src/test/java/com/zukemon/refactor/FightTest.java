package com.zukemon.refactor;

import com.zukemon.refactor.zukemons.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FightTest {

    @Mock
    private ZukemonFactory factory;

    private Fight fight;

    @BeforeEach
    public void setUp() {
        fight = new Fight();
        fight.zukemonFactory = factory;
    }

    @Test
    public void wartortleVsBlastoiseNormalMode() {
        Mockito.when(factory.createRandomZukemon()).thenReturn(new Wartortle()).thenReturn(new Blastoise());

        Zukemon winner = fight.fight(FightType.NORMAL);

        assertThat(winner).isInstanceOf(Wartortle.class);
    }

    @Test
    public void wartortleVsBlastoiseDefendMode() {
        Mockito.when(factory.createRandomZukemon()).thenReturn(new Wartortle()).thenReturn(new Blastoise());

        Zukemon winner = fight.fight(FightType.DEFEND);

        assertThat(winner).isInstanceOf(Wartortle.class);
    }

    @Test
    public void royalRumbleMode() {
        // just to make sure he wins
        Zukemon krookodile = new Krookodile();
        krookodile.increaseLifePointsBy(10000);
        Mockito.when(factory.createRandomZukemon()).thenReturn(new Wartortle()).thenReturn(new Blastoise()).thenReturn(krookodile).thenReturn(new Mew()).thenReturn(new Pikachu());

        Zukemon winner = fight.fight(FightType.ROYAL_RUMBLE);

        assertThat(winner).isEqualTo(krookodile);
    }
}
