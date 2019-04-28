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

@RunWith(value= MockitoJUnitRunner.class)
public class FourOfAKindTieBreakerTest {

    @Mock
    FourOfAKindService service;
    @Mock
    HandBase hand1, hand2;
    private FourOfAKindTieBreaker tieBreaker;

    @Before
    public void setup(){
        tieBreaker = new FourOfAKindTieBreaker(service);
    }

    @Test
    public void shouldIdentifyWinningFirstFourOfAKind(){
        // arrange
        List<HandBase> hand1Combo = new ArrayList<>();
        hand1Combo.add(new HandBase("5S 5C 5D 5H"));

        List<HandBase> hand2Combo = new ArrayList<>();
        hand2Combo.add(new HandBase("4S 4C 4D 4H"));

        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new HandBase("5S 5C 5D 5H")));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new HandBase("4S 4C 4D 4H")));

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
        List<HandBase> hand1Combo = new ArrayList<>();
        hand1Combo.add(new HandBase("5S 5C 5D 5H"));

        List<HandBase> hand2Combo = new ArrayList<>();
        hand2Combo.add(new HandBase("6S 6C 6D 6H"));

        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new HandBase("5S 5C 5D 5H")));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new HandBase("6S 6C 6D 5H")));

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
        List<HandBase> hand1Combo = new ArrayList<>();
        hand1Combo.add(new HandBase("5S 5C 5D 5H"));

        List<HandBase> hand2Combo = new ArrayList<>();
        hand2Combo.add(new HandBase("5S 5C 5D 5H"));

        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new HandBase("5S 5C 5D 5H")));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new HandBase("5S 5C 5D 5H")));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(1));

    }

    @Test
    public void shouldIdentifyTiedThreeOfAKindWinningFirstFourOfAKind(){
        // arrange
        List<HandBase> hand1Combo = new ArrayList<>();
        hand1Combo.add(new HandBase("5S 5C 5D 5S 3s"));

        List<HandBase> hand2Combo = new ArrayList<>();
        hand2Combo.add(new HandBase("5S 5C 5D 5S 2S"));

        FourOfAKindGroups hand1Group = new FourOfAKindGroups();
        hand1Group.setCombination(Arrays.asList(new HandBase("5S 5C 5D 5H")));
        hand1Group.setNonCombination(new HandBase("3S"));

        FourOfAKindGroups hand2Group = new FourOfAKindGroups();
        hand2Group.setCombination(Arrays.asList(new HandBase("5S 5C 5D 5H")));
        hand2Group.setNonCombination(new HandBase("2S"));

        when(service.getGroups(hand1)).thenReturn(hand1Group);
        when(service.getGroups(hand2)).thenReturn(hand2Group);

        // act
        int compareResult = tieBreaker.compare(hand1, hand2);

        // assert
        assertThat(compareResult, equalTo(-1));

    }

}
