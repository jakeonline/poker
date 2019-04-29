package com.odsinada.icm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;

import static com.odsinada.icm.Card.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(value= MockitoJUnitRunner.class)
public class PokerHandTest {

    PokerHand hand1, hand2;

    @Mock
    PokerService service;
    @Mock
    PokerResult serviceResult;

    @Before
    public void setup(){
        hand1 = new PokerHand("myHand1");
        hand1.setPokerService(service);
        hand2 = new PokerHand("myHand2");
        hand2.setPokerService(service);
    }

    @Test
    public void shouldReturnFirstHandAsWinner(){
        // arrange
        when(service.compare(hand1, hand2)).thenReturn(serviceResult);
        when(serviceResult.getWinner()).thenReturn(hand1);
        when(serviceResult.getLoser()).thenReturn(hand2);

        // act
        Result<PokerHand> result = hand1.versus(hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand1));
        assertThat(result.getLoser(), equalTo(hand2));

    }

    @Test
    public void shouldReturnSecondHandAsWinner(){
        // arrange
        when(service.compare(hand1, hand2)).thenReturn(serviceResult);
        when(serviceResult.getWinner()).thenReturn(hand2);
        when(serviceResult.getLoser()).thenReturn(hand1);

        // act
        Result<PokerHand> result = hand1.versus(hand2);

        // assert
        assertThat(result.getWinner(), equalTo(hand2));
        assertThat(result.getLoser(), equalTo(hand1));

    }

    @Test
    public void shouldGetCards(){
        // arrange
        hand1 = new PokerHand("5D 8C 9S JS AC");

        // act
        List<Card> cards = hand1.getCards();

        // assert
        assertThat(cards.contains(Card.of("5", "D")), equalTo(true));
        assertThat(cards.contains(Card.of("8", "C")), equalTo(true));
        assertThat(cards.contains(Card.of("9", "S")), equalTo(true));
        assertThat(cards.contains(Card.of("J", "S")), equalTo(true));
        assertThat(cards.contains(Card.of("A", "C")), equalTo(true));
        assertThat(cards.size(), equalTo(5));
    }


    @Test
    public void shouldGetHighCard(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("5D 8C 9S JS AC").getHighCard(), equalTo(CA_C));
        assertThat(new PokerHand("5D 8C 9S JS QC").getHighCard(), equalTo(CQ_C));
        assertThat(new PokerHand("5D 8C 9S JS 2C").getHighCard(), equalTo(CJ_S));
        assertThat(new PokerHand("5D 8C 9S 3S 2C").getHighCard(), equalTo(C9_S));
        assertThat(new PokerHand("5D 8C 4S 3S 2C").getHighCard(), equalTo(C8_C));
    }


    @Test
    public void shouldIdentifyCombinationHighCard(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("5D 8C 9S JS AC").getCombinations().contains(Combination.HIGH_CARD), equalTo(true));
        assertThat(new PokerHand("5D someCard").getCombinations().contains(Combination.HIGH_CARD), equalTo(true));
        assertThat(new PokerHand("someCard someOtherCard").getCombinations().contains(Combination.HIGH_CARD), equalTo(false));
        assertThat(new PokerHand("someCard").getCombinations().contains(Combination.HIGH_CARD), equalTo(false));

    }

    @Test
    public void shouldIdentifyCombinationPair(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("5D 5S 6D").getCombinations().contains(Combination.PAIR), equalTo(true));
        assertThat(new PokerHand("5D 5S 5C 6D 6S 7D 7S 7C 7H").getCombinations().contains(Combination.PAIR), equalTo(true));
        assertThat(new PokerHand("5D 6D 7D").getCombinations().contains(Combination.PAIR), equalTo(false));

    }

    @Test
    public void shouldIdentifyCombinationTwoPairs(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("5D 5S 6D 6S 7D").getCombinations().contains(Combination.TWO_PAIRS), equalTo(true));
        assertThat(new PokerHand("5D 5S 6D 7S 8D").getCombinations().contains(Combination.TWO_PAIRS), equalTo(false));
        assertThat(new PokerHand("5D 6D 7D").getCombinations().contains(Combination.TWO_PAIRS), equalTo(false));

    }

    @Test
    public void shouldIdentifyCombinationThreeOfAKind(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("7D 7S 7C 6H 5H").getCombinations().contains(Combination.THREE_OF_A_KIND), equalTo(true));

    }

    @Test
    public void shouldIdentifyCombinationStraight(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("7D 6S 5C 4H 3H").getCombinations().contains(Combination.STRAIGHT), equalTo(true));

    }

    @Test
    public void shouldIdentifyCombinationFourOfAKind(){
        // arrange

        // act
        // assert
        assertThat(new PokerHand("7D 7S 7C 7H 5H").getCombinations().contains(Combination.FOUR_OF_A_KIND), equalTo(true));

    }

    @Test
    public void shouldIdentifyCombinationFullHouse() {
        // arrange

        // act
        // assert
        assertThat(new PokerHand("7D 5D 7S 7C 5H").getCombinations().contains(Combination.FULL_HOUSE), equalTo(true));
        assertThat(new PokerHand("7D 5D 7S 7C 4H").getCombinations().contains(Combination.FULL_HOUSE), equalTo(false));
        assertThat(new PokerHand("7D 5D 7S 5C 4H").getCombinations().contains(Combination.FULL_HOUSE), equalTo(false));

    }

    @Test
    public void shouldIdentifyHighestCombinationHighCard(){
        // arrange
        // act
        // assert
        assertThat(new PokerHand("5D 6D 7D").getHighestCombination(), equalTo(Combination.HIGH_CARD));
    }

    @Test
    public void shouldIdentifyHighestCombinationPair(){
        // arrange
        // act
        // assert
        assertThat(new PokerHand("5D 6D 7D 5C").getHighestCombination(), equalTo(Combination.PAIR));
    }

    @Test
    public void shouldIdentifyHighestCombinationTwoPairs(){
        // arrange
        // act
        // assert
        assertThat(new PokerHand("5D 6D 6C 5C 4S").getHighestCombination(), equalTo(Combination.TWO_PAIRS));
    }

    @Test
    public void shouldIdentifyHighestCombinationThreeOfAKind(){
        // arrange
        // act
        // assert
        assertThat(new PokerHand("5D 6D 5S 5C").getHighestCombination(), equalTo(Combination.THREE_OF_A_KIND));
    }

    @Test
    public void shouldIdentifyHighestCombinationFullHouse(){
        // arrange
        // act
        // assert
        assertThat(new PokerHand("5D 6D 5S 5C 6S").getHighestCombination(), equalTo(Combination.FULL_HOUSE));
    }

    @Test
    public void shouldIdentifyCombinationFullHouseThreeOfAKindPairHighCard(){
        // arrange
        // act
        // assert
        Set<Combination> combinations = new PokerHand("5D 6D 5S 5C 6S").getCombinations();
        assertThat(combinations.contains(Combination.FULL_HOUSE), equalTo(true));
        assertThat(combinations.contains(Combination.THREE_OF_A_KIND), equalTo(true));
        assertThat(combinations.contains(Combination.PAIR), equalTo(true));
        assertThat(combinations.contains(Combination.HIGH_CARD), equalTo(true));
    }

}
