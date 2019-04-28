package com.odsinada.icm.fullhouse;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.fullhouse.FullHouseServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FullHouseServiceImplTest {

    private FullHouseServiceImpl service;

    @Before
    public void setup(){
        service = new FullHouseServiceImpl();
    }

    @Test
    public void shouldGetCombination() {
        // arrange
        PokerHand hand1 = new PokerHand("5S 5D 5C 2H 2D");

        // act
        PokerHandGrouping groups = service.getGroups(hand1);


        // assert
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("5", "S")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("5", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("5", "C")), equalTo(true));
        assertThat(groups.getCombination().get(1).getCards().contains(Card.of("2", "H")), equalTo(true));
        assertThat(groups.getCombination().get(1).getCards().contains(Card.of("2", "D")), equalTo(true));

        assertThat(groups.getCombination().get(0).getCards().size(), equalTo(3));
        assertThat(groups.getCombination().get(1).getCards().size(), equalTo(2));

        assertThat(groups.getNonCombination().getCards().size(), equalTo(0));
    }


    @Test
    public void shouldReturnNoCombinationWhenInexistent() {

        // arrange
        PokerHand hand1 = new PokerHand("6S 5D 4S 3S 2D");


        // act
        PokerHandGrouping groups = service.getGroups(hand1);


        // assert
        assertThat(groups.getCombination().isEmpty(), equalTo(true));
    }
}
