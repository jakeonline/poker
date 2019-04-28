package com.odsinada.icm.threeofakind;


import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.threeofakind.ThreeOfAKindService;
import com.odsinada.icm.threeofakind.ThreeOfAKindTieBreaker;
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
public class ThreeOfAKindTieBreakerTest {

    @Mock
    ThreeOfAKindService service;
    @Mock
    PokerHand hand1, hand2;
    private ThreeOfAKindTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new ThreeOfAKindTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningFirstThreeOfAKind(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("4S 4C 4D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningSecondThreeOfAKind(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("6S 6C 6D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedThreeOfAKind(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D")));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedThreeOfAKindWinningFirstThreeOfAKind(){
        // arrange
        PokerHandGrouping hand1Group = new PokerHandGrouping();
        hand1Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D")));
        hand1Group.setNonCombination(new PokerHand("4S 3S"));

        PokerHandGrouping hand2Group = new PokerHandGrouping();
        hand2Group.setCombination(Arrays.asList(new PokerHand("5S 5C 5D")));
        hand2Group.setNonCombination(new PokerHand("4S 2S"));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

}
