package com.study.practice.class09_bit_representation.重听;

public class NumberOfDiffBits {
    // Determine the number of bits that are different for two given integers.
    //
    // Examples
    //
    // 5(“0101”) and 8(“1000”) has 3 different bits
    public int diffBits(int a, int b) {
        // XOR -> 不同的才是1
        int x = a ^ b;
        return oneCounter(x);
    }
    private int oneCounter(int number) {
        int num = 0;
        while (number != 0) {       // 注意，不可以是>0, 不然会错过所有负数。
            num += number & 1;
            number = number >>> 1; // 强制移位并补0
        }
        return num;
    }
}
