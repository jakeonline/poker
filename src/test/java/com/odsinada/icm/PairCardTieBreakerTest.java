package com.odsinada.icm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(value = MockitoJUnitRunner.class)
public class PairCardTieBreakerTest {

    private PairCardTieBreaker tieBreaker;
    @Mock
    PokerHand hand1, hand2;
    @Mock
    PairService service;

    @Before
    public void setup(){
        tieBreaker = new PairCardTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningFirstPair(){
        // arrange
        PokerHandGrouping hand1Groups = new PokerHandGrouping();
        hand1Groups.setCombination(Arrays.asList(new PokerHand("5S 5C")));

        PokerHandGrouping hand2Groups = new PokerHandGrouping();
        hand2Groups.setCombination(Arrays.asList(new PokerHand("4S 4C")));

        when(service.getGroups(hand1)).thenReturn(hand1Groups);
        when(service.getGroups(hand2)).thenReturn(hand2Groups);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningSecondPair(){
        // arrange
        PokerHandGrouping hand1Groups = new PokerHandGrouping();
        hand1Groups.setCombination(Arrays.asList(new PokerHand("3S 3C")));

        PokerHandGrouping hand2Groups = new PokerHandGrouping();
        hand2Groups.setCombination(Arrays.asList(new PokerHand("4S 4C")));

        when(service.getGroups(hand1)).thenReturn(hand1Groups);
        when(service.getGroups(hand2)).thenReturn(hand2Groups);


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedPair(){
        // arrange
        PokerHandGrouping hand1Groups = new PokerHandGrouping();
        hand1Groups.setCombination(Arrays.asList(new PokerHand("3S 3C")));

        PokerHandGrouping hand2Groups = new PokerHandGrouping();
        hand2Groups.setCombination(Arrays.asList(new PokerHand("3H 3D")));

        when(service.getGroups(hand1)).thenReturn(hand1Groups);
        when(service.getGroups(hand2)).thenReturn(hand2Groups);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedPairWinningFirstHighCard(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("3S 3C")));
        hand1Group.setNonCombination(new PokerHand("9C"));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("3H 3D")));
        hand2Group.setNonCombination(new PokerHand("8C"));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));
    }
}
