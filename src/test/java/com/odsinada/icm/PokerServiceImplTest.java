package com.odsinada.icm;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PokerServiceImplTest {

    PokerService service = new PokerServiceImpl();
    PokerHand hand1, hand2;

    @Test
    public void shouldIdentifyWinningFirstHandWithHighCard(){
        // arrange
        hand1 = new PokerHand("5S");
        hand2 = new PokerHand("4D");

        // act
        PokerResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

    @Test
    public void shouldIdentifyWinningSecondHandWithHighCard(){
        // arrange
        hand1 = new PokerHand("QS");
        hand2 = new PokerHand("AD");

        // act
        PokerResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getLoser(), equalTo(hand1));
        assertThat(result.getWinner(), equalTo(hand2));

    }

    @Test
    public void shouldIdentifyWinningFirstHandWithPair(){
        // arrange
        hand1 = new PokerHand("4S 4C");
        hand2 = new PokerHand("5D");

        // act
        PokerResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

    @Test
    public void shouldIdentifyWinningFirstHandWithHigherPair(){
        // arrange
        hand1 = new PokerHand("4S 4C");
        hand2 = new PokerHand("3S 3C");

        // act
        PokerResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

}
