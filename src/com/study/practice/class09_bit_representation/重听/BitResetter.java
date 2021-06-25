package com.study.practice.class09_bit_representation.重听;

public class BitResetter {
    // 与setter相反，设置第k位为0，而不是1.
    public int bitResetter(int x, int k) {
        return x & ~(1 << k);
    }
}