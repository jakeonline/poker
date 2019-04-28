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
public class FullHouseTieBreakerTest {

    @Mock
    FullHouseService service;
    @Mock
    PokerHand hand1, hand2;
    private FullHouseTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new FullHouseTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningFirstFullHouse(){
        // arrange
        FullHouseGroups hand1Group = new FullHouseGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D"), new PokerHand("4H 4H")));

        FullHouseGroups hand2Group = new FullHouseGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("4S 4C 4D"), new PokerHand("3H 3D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningSecondFullHouse(){
        // arrange
        FullHouseGroups hand1Group = new FullHouseGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D"),new PokerHand("4H 4D")));

        FullHouseGroups hand2Group = new FullHouseGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("6S 6C 6D"), new PokerHand("3H 3D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedFullHouse(){
        // arrange
        FullHouseGroups hand1Group = new FullHouseGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D"),new PokerHand("4H 4D")));

        FullHouseGroups hand2Group = new FullHouseGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D"),new PokerHand("4H 4D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(0));

    }

    @Test
    public void shouldIdentifyTiedThreeOfAKindWinningFirstPair(){
        // arrange
        FullHouseGroups hand1Group = new FullHouseGroups();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D"), new PokerHand("3S 3D")));

        FullHouseGroups hand2Group = new FullHouseGroups();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D"), new PokerHand("2S 2D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

}
