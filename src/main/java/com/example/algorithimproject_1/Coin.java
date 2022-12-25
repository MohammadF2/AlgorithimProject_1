package com.example.algorithimproject_1;

import java.util.ArrayList;

public class Coin {

    private ArrayList<Integer> root;
    private int sum;

    public Coin(int sum) {
        this.root = new ArrayList<>();
        this.sum = sum;
    }


    public ArrayList<Integer> getRoot() {
        return root;
    }

    public void setRoot(ArrayList<Integer> root) {
        this.root = root;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


    public void addToRoot(int n){
        root.add(n);

        //System.out.println(n + " was added to root!! Root size now " + root.size());

    }

}
