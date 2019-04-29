package com.odsinada.icm.flush;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.twopairs.TwoPairsServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FlushServiceImplTest {

    private FlushServiceImpl service;

    @Before
    public void setup(){
        service = new FlushServiceImpl();
    }

    @Test
    public void shouldGetFlush() {
        // arrange
        PokerHand hand1 = new PokerHand("5D 6D 7D JD AD");

        // act
        PokerHandGrouping flushGroups = service.getGroups(hand1);


        // assert
        assertThat(flushGroups.getCombination().get(0).getCards().contains(Card.of("5", "D")), equalTo(true));
        assertThat(flushGroups.getCombination().get(0).getCards().contains(Card.of("6", "D")), equalTo(true));
        assertThat(flushGroups.getCombination().get(0).getCards().contains(Card.of("7", "D")), equalTo(true));
        assertThat(flushGroups.getCombination().get(0).getCards().contains(Card.of("J", "D")), equalTo(true));
        assertThat(flushGroups.getCombination().get(0).getCards().contains(Card.of("A", "D")), equalTo(true));
        assertThat(flushGroups.getCombination().get(0).getCards().size(), equalTo(5));

    }

    @Test
    public void shouldIdentifyFlush() {
        // arrange
        PokerHand hand1 = new PokerHand("5D 6D 6D 5D 4D");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(true));
    }


    @Test
    public void shouldIdentifyInexistentFlush() {

        // arrange
        PokerHand hand1 = new PokerHand("5D 6D 6H 5C 4D");

        // act
        boolean isCombinationPresent = service.isCombinationPresent(hand1);

        // assert
        assertThat(isCombinationPresent, equalTo(false));
    }
}
