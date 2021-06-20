package com.study.practice.class09_bit_representation.重听;

public class OneCounter {
    private int oneCounter(int number) {
        int num = 0;
        while (number != 0) {       // 注意，不可以是>0, 不然会错过所有负数。
            num += number & 1;
            number = number >>> 1; // 强制移位并补0
        }
        return num;
    }
}
