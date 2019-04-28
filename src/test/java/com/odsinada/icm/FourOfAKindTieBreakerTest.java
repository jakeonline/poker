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

@RunWith(value= MockitoJUnitRunner.class)
public class FourOfAKindTieBreakerTest {

    @Mock
    FourOfAKindService service;
    @Mock
    PokerHand hand1, hand2;
    private FourOfAKindTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new FourOfAKindTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningFirstFourOfAKind(){
        // arrange
        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D 5H")));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("4S 4C 4D 4H")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningSecondFourOfAKind(){
        // arrange
        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D 5H")));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("6S 6C 6D 5H")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedFourOfAKind(){
        // arrange
        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D 5H")));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D 5H")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedFourOfAKindWinningFirstFourOfAKind(){
        // arrange
        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D 5H")));
        hand1Group.setNonCombination(new PokerHand("3S"));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D 5H")));
        hand2Group.setNonCombination(new PokerHand("2S"));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

}
