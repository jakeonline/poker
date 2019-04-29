package com.odsinada.icm.straightflush;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.straight.StraightServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StraightFlushServiceImplTest {

    private StraightFlushServiceImpl service;

    @Before
    public void setup(){
        service = new StraightFlushServiceImpl();
    }

    @Test
    public void shouldGetStraightFlushCards() {
        // arrange
        PokerHand hand1 = new PokerHand("7D 6D 5D 4D 3D");

        // act
        PokerHandGrouping groups = service.getGroups(hand1);

        // assert
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("7", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("6", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("5", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("4", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("3", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().size(), equalTo(5));
    }

    @Test
    public void shouldIdentifyStraightFlush() {
        // arrange
        PokerHand hand1 = new PokerHand("7D 6D 5D 4D 3D");
        PokerHand hand2 = new PokerHand("AD 10D QD JD KD");

        // act
        // assert
        assertThat(service.isCombinationPresent(hand1), equalTo(true));
        assertThat(service.isCombinationPresent(hand2), equalTo(true));
    }

    @Test
    public void shouldIdentifyNonStraightFlush() {
        // arrange
        PokerHand hand1 = new PokerHand("9D 8D 5D 4D 3D");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(false));
    }
}
