package com.interview.bloomberg;

public class Tester {
    public static void main(String[] args) {
//        BrowserHistory bh = new BrowserHistory("esgriv");
//        bh.visit("cgrt");
//        bh.visit("tip");
//        bh.back(9);
//        bh.visit("kttzxgh");
//        bh.forward(7);
//        bh.visit("crqje");
//        bh.visit("iybch");
//        bh.forward(5);
//        bh.visit("uun");
//        bh.back(10);
//        bh.visit("hci");
//        bh.visit("whula");
//        bh.forward(10);
        
        UndergroundSystem s = new UndergroundSystem();
        s.checkIn(45,"Leyton",3);
        s.checkIn(32,"Paradise",8);
        s.checkIn(27,"Leyton",10);

        s.checkOut(45,"Waterloo",15);
        s.checkOut(27,"Waterloo",20);
        s.checkOut(32,"Cambridge",22);
        
        s.getAverageTime("Paradise","Cambridge");
        s.getAverageTime("Leyton","Waterloo");
    }
}
