package com.study.practice.class09_bit_representation.重听;

public class Tester {
    public static void main(String[] args) {
        BitResetter resetter = new BitResetter();
        int r = resetter.bitResetter(124, 4);
        System.out.println(r);
        int a = 100;
        int b = 100;
        int c = a & b << 1;
        int d = a & (b << 1);
        System.out.println(a);
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(17));
    }
}
