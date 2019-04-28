package com.odsinada.icm;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HandBaseServiceBaseTest {

    HandBaseService service = new HandBaseServiceBase();
    HandBase hand1, hand2;

    @Test
    public void shouldIdentifyWinningFirstHandWithHighCard(){
        // arrange
        hand1 = new HandBase("5S");
        hand2 = new HandBase("4D");

        // act
        HandBaseServiceResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

    @Test
    public void shouldIdentifyWinningSecondHandWithHighCard(){
        // arrange
        hand1 = new HandBase("QS");
        hand2 = new HandBase("AD");

        // act
        HandBaseServiceResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getLoser(), equalTo(hand1));
        assertThat(result.getWinner(), equalTo(hand2));

    }

    @Test
    public void shouldIdentifyWinningFirstHandWithPair(){
        // arrange
        hand1 = new HandBase("4S 4C");
        hand2 = new HandBase("5D");

//        4s4c, 5D..... P, H : -1
//        5D, 4s4c..... H, P : 1

        // act
        HandBaseServiceResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

    @Test
    public void shouldIdentifyWinningFirstHandWithHigherPair(){
        // arrange
        hand1 = new HandBase("4S 4C");
        hand2 = new HandBase("3S 3C");

        // act
        HandBaseServiceResult result = service.compare(hand1, hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

}
