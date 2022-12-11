package com.example.algorithimproject_1;

public class PlayerSpot {

    private Coin first;
    private Coin second;

    public PlayerSpot(int first, int second) {
        this.first = new Coin(first);
        this.second = new Coin(second);
    }

    public Coin getFirst() {
        return first;
    }

    public Coin getSecond() {
        return second;
    }

}
