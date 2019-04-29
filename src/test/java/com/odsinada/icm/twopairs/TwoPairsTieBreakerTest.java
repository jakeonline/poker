package com.odsinada.icm.twopairs;


import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.fullhouse.FullHouseService;
import com.odsinada.icm.fullhouse.FullHouseTieBreaker;
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
public class TwoPairsTieBreakerTest {

    @Mock
    TwoPairsService service;
    @Mock
    PokerHand hand1, hand2;
    private TwoPairsTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new TwoPairsTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningTwoPairsWithHigherFirstPair(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C"), new PokerHand("2H 2H")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("4S 4C"), new PokerHand("3H 3D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningTwoPairsWithHigherSecondPair(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("4H 4D"), new PokerHand("2H 2H")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("4S 4C"), new PokerHand("3H 3D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedTwoPairsWinningFirstWithHighCard(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C"), new PokerHand("3S 3D")));
        hand1Group.setNonCombination(new PokerHand("3H"));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C"), new PokerHand("3S 3D")));
        hand2Group.setNonCombination(new PokerHand("2H"));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

}
