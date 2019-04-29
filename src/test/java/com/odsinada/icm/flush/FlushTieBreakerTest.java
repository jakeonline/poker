package com.odsinada.icm.flush;


import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(value= MockitoJUnitRunner.class)
public class FlushTieBreakerTest {

    @Mock
    FlushService service;
    @Mock
    PokerHand hand1, hand2;
    private FlushTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new FlushTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningFirstFlushHighCard(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 6S 7S JS AS")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 6S 7S JS QS")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningSecondFlushHighCard(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 6S 7S JS QS")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 6S 7S JS AS")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedFlush(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 6S 7S JS AS")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 6S 7S JS AS")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(0));

    }

}
