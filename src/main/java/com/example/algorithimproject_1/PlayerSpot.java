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

    public void setFirst(Coin first) {
        this.first = first;
    }

    public void setSecond(Coin second) {
        this.second = second;
    }

    public Coin getSecond() {
        return second;
    }

}
