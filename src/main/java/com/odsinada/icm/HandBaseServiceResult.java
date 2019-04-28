package com.odsinada.icm;

public interface HandBaseServiceResult {
    HandBase getWinner();

    HandBase getLoser();

    void setWinner(HandBase hand);

    void setLoser(HandBase hand);
}
