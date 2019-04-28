package com.odsinada.icm;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HighCardTieBreakerTest {

    private HighCardTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new HighCardTieBreaker();
    }

    @Test
    public void shouldIdentifyWInningFirstHand(){
        // arrange
        PokerHand hand1 = new PokerHand("6S");
        PokerHand hand2 = new PokerHand("5S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }

    @Test
    public void shouldIdentifyWinningSecondHand(){
        // arrange
        PokerHand hand1 = new PokerHand("5S");
        PokerHand hand2 = new PokerHand("6S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));
    }

    @Test
    public void shouldIdentifyWinningFirstHandMiddleCard(){
        // arrange
        PokerHand hand1 = new PokerHand("6S 5S 3S");
        PokerHand hand2 = new PokerHand("6S 4S 3S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }

    @Test
    public void shouldIdentifyWinningFirstHandLastCard(){
        // arrange
        PokerHand hand1 = new PokerHand("6S 5S 3S");
        PokerHand hand2 = new PokerHand("6S 5S 2S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }

}
