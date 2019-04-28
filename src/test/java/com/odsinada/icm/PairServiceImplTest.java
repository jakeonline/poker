package com.odsinada.icm;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        HandBase hand1 = new HandBase("5S 5D 4S 3S 2D");

        // act
        PairGroups pairGroups = service.getGroups(hand1);


        // assert
        assertThat(pairGroups.getPairs().get(0).getCards().contains(Card.of("5", "S")), equalTo(true));
        assertThat(pairGroups.getPairs().get(0).getCards().contains(Card.of("5", "D")), equalTo(true));
        assertThat(pairGroups.getPairs().get(0).getCards().size(), equalTo(2));

        assertThat(pairGroups.getNonPair().getCards().contains(Card.of("4", "S")), equalTo(true));
        assertThat(pairGroups.getNonPair().getCards().contains(Card.of("3", "S")), equalTo(true));
        assertThat(pairGroups.getNonPair().getCards().contains(Card.of("2", "D")), equalTo(true));
        assertThat(pairGroups.getNonPair().getCards().size(), equalTo(3));
    }


    @Test
    public void shouldReturnNoPairWhenInexistent() {

        // arrange
        HandBase hand1 = new HandBase("6S 5D 4S 3S 2D");


        // act
        PairGroups pairGroups = service.getGroups(hand1);


        // assert
        assertThat(pairGroups.getPairs().isEmpty(), equalTo(true));
    }
}
