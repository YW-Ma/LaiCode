package com.interview.moveworks;

public class Tester {
    public static void main(String[] args) {
//        BasicCalculator bc1 = new BasicCalculator();
//        System.out.println(bc1.calculate("3 + 2 * 2"));
//
//        BasicCalculatorIII bc2 = new BasicCalculatorIII();
//        System.out.println(bc2.calculate("2*(5+5*2)/3+(6/2+8)"));

        BasicCalculatorIII_Iteration bc3 = new BasicCalculatorIII_Iteration();
        System.out.println(bc3.calculate("2*(5+5*2)/3+(6/2+8)"));
    }
}
