package com.odsinada.icm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(value = MockitoJUnitRunner.class)
public class PairCardTieBreakerTest {

    private PairCardTieBreaker tieBreaker;
    @Mock
    HandBase hand1, hand2;
    @Mock
    PairService pairService;

    @Before
    public void setup(){
        tieBreaker = new PairCardTieBreaker(pairService);
    }

    @Test
    public void shouldIdentifyWinningFirstPair(){
        // arrange
        PairGroups hand1Group = new PairGroups();
        hand1Group.setPairs(Arrays.asList(new HandBase("5S 5C")));

        PairGroups hand2Group = new PairGroups();
        hand2Group.setPairs(Arrays.asList(new HandBase("4S 4C")));

        when(pairService.getGroups(hand1)).thenReturn(hand1Group);
        when(pairService.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

    @Test
    public void shouldIdentifyWinningSecondPair(){
        // arrange
        PairGroups hand1Group = new PairGroups();
        hand1Group.setPairs(Arrays.asList(new HandBase("3S 3C")));

        PairGroups hand2Group = new PairGroups();
        hand2Group.setPairs(Arrays.asList(new HandBase("4S 4C")));

        when(pairService.getGroups(hand1)).thenReturn(hand1Group);
        when(pairService.getGroups(hand2)).thenReturn(hand2Group);


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedPair(){
        // arrange
        PairGroups hand1Group = new PairGroups();
        hand1Group.setPairs(Arrays.asList(new HandBase("3S 3C")));

        PairGroups hand2Group = new PairGroups();
        hand2Group.setPairs(Arrays.asList(new HandBase("3H 3D")));

        when(pairService.getGroups(hand1)).thenReturn(hand1Group);
        when(pairService.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedPairWinningFirstHand(){
        // arrange
        PairGroups hand1Group = new PairGroups();
        hand1Group.setPairs(Arrays.asList(new HandBase("3S 3C")));
        hand1Group.setNonPair(new HandBase("9C"));

        PairGroups hand2Group = new PairGroups();
        hand2Group.setPairs(Arrays.asList(new HandBase("3H 3D")));
        hand2Group.setNonPair(new HandBase("8C"));

        when(pairService.getGroups(hand1)).thenReturn(hand1Group);
        when(pairService.getGroups(hand2)).thenReturn(hand2Group);


        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }


}
