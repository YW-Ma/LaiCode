package com.interview.bloomberg;

public class Tester {
    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory("esgriv");
        bh.visit("cgrt");
        bh.visit("tip");
        bh.back(9);
        bh.visit("kttzxgh");
        bh.forward(7);
        bh.visit("crqje");
        bh.visit("iybch");
        bh.forward(5);
        bh.visit("uun");
        bh.back(10);
        bh.visit("hci");
        bh.visit("whula");
        bh.forward(10);
    }
}
