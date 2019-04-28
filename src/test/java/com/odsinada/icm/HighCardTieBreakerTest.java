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
        HandBase hand1 = new HandBase("6S");
        HandBase hand2 = new HandBase("5S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }

    @Test
    public void shouldIdentifyWinningSecondHand(){
        // arrange
        HandBase hand1 = new HandBase("5S");
        HandBase hand2 = new HandBase("6S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));
    }

    @Test
    public void shouldIdentifyWinningFirstHandMiddleCard(){
        // arrange
        HandBase hand1 = new HandBase("6S 5S 3S");
        HandBase hand2 = new HandBase("6S 4S 3S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }

    @Test
    public void shouldIdentifyWinningFirstHandLastCard(){
        // arrange
        HandBase hand1 = new HandBase("6S 5S 3S");
        HandBase hand2 = new HandBase("6S 5S 2S");


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }

}
