package com.study.practice.class09_bit_representation.重听;

public class PowerOfTwo {
    // Determine if a given integer is power of 2.
    //
    // Examples
    //
    //  16 is power of 2 (2 ^ 4)
    //  3 is not
    //  0 is not
    //  -1 is not

    // the attribute of a power of 2 num: only one 1 digit is allowed
    public boolean isPowerOfTwo1(int number) {
        if (number <= 0) {
            return false;
        }
        int tester = (number & (number - 1)); // 如果是2的倍数，删掉了最右侧的1就应该无了。 如果不是2的倍数，删掉了最右侧的1，应该左边还有一个1. （注意，删掉最右侧的1会让更低位充满1）
        if (tester != 0) {
            return false;
        }
        return true;
    }



    // 另外的方法：
    // 手动检查有几个1 --> 1 counter
    public boolean isPowerOfTwo(int number) {
        if (number <= 0) {
            return false;
        }
        if (oneCounter(number) != 1) {
            return false;
        }
        return true;
    }
    private int oneCounter(int number) {
        int num = 0;
        while (number != 0) {
            num += number & 1;
            number = number >>> 1; // 强制移位并补0
        }
        return num;
    }
}
