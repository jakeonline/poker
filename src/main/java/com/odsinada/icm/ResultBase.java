package com.odsinada.icm;

public class ResultBase implements Result<PokerHand> {

    private PokerHand winner;
    private PokerHand loser;

    @Override
    public PokerHand getWinner() {
        return this.winner;
    }

    @Override
    public PokerHand getLoser() {
        return this.loser;
    }

    @Override
    public void setWinner(PokerHand hand) {
        this.winner = hand;
    }

    @Override
    public void setLoser(PokerHand hand) {
        this.loser = hand;
    }
}
