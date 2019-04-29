package com.odsinada.icm.straight;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.fourofakind.FourOfAKindServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StraightServiceImplTest {

    private StraightServiceImpl service;

    @Before
    public void setup(){
        service = new StraightServiceImpl();
    }

    @Test
    public void shouldGetStraightCards() {
        // arrange
        PokerHand hand1 = new PokerHand("7D 6D 5C 4C 3S");

        // act
        PokerHandGrouping groups = service.getGroups(hand1);

        // assert
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("7", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("6", "D")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("5", "C")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("4", "C")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().contains(Card.of("3", "S")), equalTo(true));
        assertThat(groups.getCombination().get(0).getCards().size(), equalTo(5));
    }

    @Test
    public void shouldIdentifyStraight() {
        // arrange
        PokerHand hand1 = new PokerHand("7D 6D 5C 4C 3S");
        PokerHand hand2 = new PokerHand("AD 10D QC JC KS");

        // act
        // assert
        assertThat(service.isCombinationPresent(hand1), equalTo(true));
        assertThat(service.isCombinationPresent(hand2), equalTo(true));
    }

    @Test
    public void shouldIdentifyNonStraight() {
        // arrange
        PokerHand hand1 = new PokerHand("7D 6D JC 4C 3S");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(false));
    }
}
