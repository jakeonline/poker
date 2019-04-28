package com.odsinada.icm;

public class HandBaseServiceResultBase implements HandBaseServiceResult {
    private HandBase winner;
    private HandBase loser;

    @Override
    public HandBase getWinner() {
        return this.winner;
    }

    @Override
    public HandBase getLoser() {
        return this.loser;
    }

    @Override
    public void setWinner(HandBase hand) {
        this.winner = hand;
    }

    @Override
    public void setLoser(HandBase hand) {
        this.loser = hand;
    }
}
