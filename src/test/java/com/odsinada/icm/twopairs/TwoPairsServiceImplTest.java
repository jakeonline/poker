package com.odsinada.icm.twopairs;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.pair.PairServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TwoPairsServiceImplTest {

    private TwoPairsServiceImpl service;

    @Before
    public void setup(){
        service = new TwoPairsServiceImpl();
    }

    @Test
    public void shouldGetTwoPairs() {
        // arrange
        PokerHand hand1 = new PokerHand("5D 6D 6C 5C 4S");

        // act
        PokerHandGrouping twoPairsGroups = service.getGroups(hand1);


        // assert
        assertThat(twoPairsGroups.getCombination().get(0).getCards().contains(Card.of("5", "C")), equalTo(true));
        assertThat(twoPairsGroups.getCombination().get(0).getCards().contains(Card.of("5", "D")), equalTo(true));
        assertThat(twoPairsGroups.getCombination().get(0).getCards().size(), equalTo(2));

        assertThat(twoPairsGroups.getCombination().get(1).getCards().contains(Card.of("6", "C")), equalTo(true));
        assertThat(twoPairsGroups.getCombination().get(1).getCards().contains(Card.of("6", "D")), equalTo(true));
        assertThat(twoPairsGroups.getCombination().get(1).getCards().size(), equalTo(2));

        assertThat(twoPairsGroups.getNonCombination().getCards().contains(Card.of("4", "S")), equalTo(true));
        assertThat(twoPairsGroups.getNonCombination().getCards().size(), equalTo(1));
    }

    @Test
    public void shouldIdentifyTwoPairs() {
        // arrange
        PokerHand hand1 = new PokerHand("5D 6D 6C 5C 4S");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(true));
    }


    @Test
    public void shouldIdentifyInexistentTwoPairs() {

        // arrange
        PokerHand hand1 = new PokerHand("6S 6D 4S 3S 2D");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(false));
    }
}
