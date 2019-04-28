package com.odsinada.icm;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PairServiceImplTest {

    private PairServiceImpl service;

    @Before
    public void setup(){
        service = new PairServiceImpl();
    }

    @Test
    public void shouldGetPair() {
        // arrange
        PokerHand hand1 = new PokerHand("5S 5D 4S 3S 2D");

        // act
        PokerHandGrouping pairGroups = service.getGroups(hand1);


        // assert
        assertThat(pairGroups.getCombination().get(0).getCards().contains(Card.of("5", "S")), equalTo(true));
        assertThat(pairGroups.getCombination().get(0).getCards().contains(Card.of("5", "D")), equalTo(true));
        assertThat(pairGroups.getCombination().get(0).getCards().size(), equalTo(2));

        assertThat(pairGroups.getNonCombination().getCards().contains(Card.of("4", "S")), equalTo(true));
        assertThat(pairGroups.getNonCombination().getCards().contains(Card.of("3", "S")), equalTo(true));
        assertThat(pairGroups.getNonCombination().getCards().contains(Card.of("2", "D")), equalTo(true));
        assertThat(pairGroups.getNonCombination().getCards().size(), equalTo(3));
    }


    @Test
    public void shouldReturnNoPairWhenInexistent() {

        // arrange
        PokerHand hand1 = new PokerHand("6S 5D 4S 3S 2D");


        // act
        PokerHandGrouping pairGroups = service.getGroups(hand1);


        // assert
        assertThat(pairGroups.getCombination().isEmpty(), equalTo(true));
    }
}
