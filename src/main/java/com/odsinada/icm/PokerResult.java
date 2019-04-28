package com.odsinada.icm;

public interface PokerResult {
    PokerHand getWinner();

    PokerHand getLoser();

    void setWinner(PokerHand hand);

    void setLoser(PokerHand hand);
}
