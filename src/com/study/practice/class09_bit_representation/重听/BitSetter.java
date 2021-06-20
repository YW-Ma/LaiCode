package com.study.practice.class09_bit_representation.重听;

public class BitSetter {
    // Given a number x, set x's k-th bit to 1
    public int bitSetter(int x, int k) {
        return x | (1 << k);
    }
}
