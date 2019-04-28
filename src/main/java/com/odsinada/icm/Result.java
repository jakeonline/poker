package com.odsinada.icm;

public interface Result<T extends Hand> {
    T getWinner();
    T getLoser();

    void setWinner(T hand);
    void setLoser(T hand);
}
