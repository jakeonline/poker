package com.odsinada.icm;

import org.junit.Test;

import static com.odsinada.icm.Card.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CardTest {

    @Test
    public void shouldIdentifyCard(){
        // arrange

        // act
        // assert
        assertThat(Card.of("10", "H"), equalTo(C10_H));
        assertThat(Card.of("J", "D"), equalTo(CJ_D));
        assertThat(Card.of("3", "C"), equalTo(C3_C));
        assertThat(Card.of("A", "S"), equalTo(CA_S));
    }

    @Test
    public void shouldDefaultToJokerUnidentifiableCard(){
        // arrange

        // act
        // assert
        assertThat(Card.of("null", "unknown"), equalTo(C_JOKER));
    }

}
