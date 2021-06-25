package com.study.practice.class09_bit_representation.重听;

public class BitTester {
    // 获知第i位是否是1 (下面这个方法，可以获得是1还是0)
    public int bitTester(int x, int k) {
        return (x >> k) & 1;
    }
}
