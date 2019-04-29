package com.odsinada.icm.royal;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RoyalFlushServiceImplTest {

    private RoyalFlushServiceImpl service;

    @Before
    public void setup(){
        service = new RoyalFlushServiceImpl();
    }

    @Test
    public void shouldGetRoyalStraightFlushCards() {
        // arrange
        PokerHand hand1 = new PokerHand("AD KD QD JD TD");

        // act
        PokerHandGrouping groups = service.getGroups(hand1);

        // assert
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("A", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("K", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("Q", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("J", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("T", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().size(), equalTo(5));
    }

    @Test
    public void shouldIdentifyRoyalStraightFlush() {
        // arrange
        PokerHand hand1 = new PokerHand("AD KD QD JD TD");

        // act
        // assert
        assertThat(service.isCombinationPresent(hand1), equalTo(true));
    }

    @Test
    public void shouldIdentifyFlushNotRoyal() {
        // arrange
        PokerHand hand1 = new PokerHand("AD KD QD JD 5D");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(false));
    }

    @Test
    public void shouldIdentifyRoyalNotFlush() {
        // arrange
        PokerHand hand1 = new PokerHand("AD KD QD JD 10S");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(false));
    }
}
